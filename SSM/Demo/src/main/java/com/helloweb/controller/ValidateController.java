package com.helloweb.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.helloweb.model.ValidateDto;
import com.helloweb.service.IValidateService;
import com.helloweb.validate.CustomValidatorException;
import com.helloweb.validate.ValidateDtoValidator;

@Controller
@RequestMapping("/validate")
public class ValidateController {

	@InitBinder
	public void initBinder(DataBinder binder) {
		binder.addValidators(new ValidateDtoValidator());
	}

	@RequestMapping("/index")
	public ModelAndView index(@Valid ValidateDto dto, BindingResult validResult) {

		ModelAndView result = new ModelAndView("/validate/index");

		if (validResult.hasErrors()) {
			List<ObjectError> errors = validResult.getAllErrors();
			for (ObjectError item : errors) {
				System.out.println(item.getDefaultMessage());
			}
		} else {
			System.out.println("Valid Success");
		}

		return result;
	}

	@Autowired
	private ValidateDtoValidator validator;

	@RequestMapping("/manual")
	@ResponseBody
	public String manual(ValidateDto dto, Errors errors) {
		String result = "SUCCESS";

		validator.validate(dto, errors);

		if (errors.hasErrors()) {
			result = "";

			List<ObjectError> list = errors.getAllErrors();
			for (ObjectError item : list) {
				result += item.getDefaultMessage();
			}
		}

		return result;
	}

	@Autowired
	private IValidateService validateService;

	@RequestMapping("/aop")
	@ResponseBody
	public String aop(ValidateDto dto) {
		String result = "SUCCESS";

		try {
			boolean isValid = validateService.validate(dto);
			result += Boolean.toString(isValid);
		} catch (CustomValidatorException e) {
			BindingResult bindingResult = e.getBindingResult();
			if (null != bindingResult && bindingResult.hasErrors()) {
				List<ObjectError> errors = bindingResult.getAllErrors();
				for (ObjectError item : errors) {
					result += item.getDefaultMessage();
				}
			}
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		result += format.format(new Date());

		return result;
	}
}
