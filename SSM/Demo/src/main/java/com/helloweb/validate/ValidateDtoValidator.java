package com.helloweb.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.helloweb.model.ValidateDto;

@Component
public class ValidateDtoValidator implements Validator {

	public boolean supports(Class<?> supportClass) {
		return ValidateDto.class.equals(supportClass);
	}

	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", "name not null");
		ValidateDto item = (ValidateDto) obj;
		if (0 > item.getAge() || item.getAge() > 150)
			errors.rejectValue("age", "age.error", "age incorrect");
	}

}
