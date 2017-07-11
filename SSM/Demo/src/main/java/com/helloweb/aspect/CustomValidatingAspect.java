package com.helloweb.aspect;

import java.lang.annotation.Annotation;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import com.helloweb.validate.CustomValidatorException;
import com.helloweb.validate.CustomValidatorRegistry;

@Component
@Aspect
public class CustomValidatingAspect {

	@Autowired
	private CustomValidatorRegistry registry;

	@Pointcut("@annotation(CustomValidation)")
	public void pointcut() {
	}

	@Before("pointcut()")
	public void doBefore(JoinPoint point) {
		Annotation[][] paramAnnotations = ((MethodSignature) point.getSignature()).getMethod()
				.getParameterAnnotations();
		for (int i = 0, iMax = paramAnnotations.length; i < iMax; i++) {
			for (Annotation annotation : paramAnnotations[i]) {
				if (annotation.annotationType() == Validated.class) {
					Object arg = point.getArgs()[i];
					if (arg == null)
						continue;
					validate(arg);
				}
			}
		}
	}

	private void validate(Object arg) {
		List<Validator> validatorList = registry.getValidatorsForObject(arg);
		if (null == validatorList || validatorList.isEmpty())
			return;

		for (Validator validator : validatorList) {
			BindingResult errors = new BeanPropertyBindingResult(arg, arg.getClass().getSimpleName());
			validator.validate(arg, errors);
			if (errors.hasErrors()) {
				throw new CustomValidatorException(errors);
			}
		}
	}
}
