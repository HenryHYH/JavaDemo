package com.helloweb.validate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.Validator;

public class CustomValidatorRegistry {

	private List<Validator> validatorList = new ArrayList<Validator>();

	public void setValidatorList(List<Validator> validatorList) {
		this.validatorList = validatorList;
	}

	public void addValidator(Validator validator) {
		validatorList.add(validator);
	}

	public List<Validator> getValidatorsForObject(Object o) {
		List<Validator> result = new ArrayList<Validator>();
		for (Validator validator : validatorList) {
			if (validator.supports(o.getClass())) {
				result.add(validator);
			}
		}
		return result;
	}

	public void init() {
		// init-method
	}
}
