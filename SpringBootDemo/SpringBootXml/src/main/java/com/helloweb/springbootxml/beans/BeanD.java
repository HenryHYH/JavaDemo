package com.helloweb.springbootxml.beans;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BeanD implements IBean {

	@Override
	public void show() {
		System.out.println("BeanD");
	}

	@Override
	public String get() {
		return "BeanD";
	}

}
