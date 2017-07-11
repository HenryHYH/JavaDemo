package com.helloweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.helloweb.service.IAopService;

@Controller
@RequestMapping("/aop")
public class AopController {

	@Autowired
	private IAopService aopService;

	@RequestMapping("{name}")
	public ModelAndView index(@PathVariable String name) {

		aopService.toList(name);
		aopService.doNothing(name);

		ModelAndView result = new ModelAndView("/aop/index");

		result.addObject("name", aopService.toLower(name));

		return result;
	}
}
