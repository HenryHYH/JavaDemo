package com.helloweb.validate;

import org.springframework.validation.BindingResult;

public class CustomValidatorException extends RuntimeException {

	private static final long serialVersionUID = 2271366742977978423L;
	private BindingResult bindingResult;

	public CustomValidatorException(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}

	public BindingResult getBindingResult() {
		return bindingResult;
	}
}
