package com.helloweb.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.helloweb.aspect.CustomValidation;
import com.helloweb.model.ValidateDto;
import com.helloweb.service.IValidateService;

@Service
public class ValidateService implements IValidateService {

	@CustomValidation
	public boolean validate(@Validated ValidateDto dto) {
		return true;
	}

}
