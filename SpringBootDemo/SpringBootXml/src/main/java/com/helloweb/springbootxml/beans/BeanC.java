package com.helloweb.springbootxml.beans;

public class BeanC implements IBean {

	@Override
	public void show() {
		System.out.println("BeanC");
	}

	@Override
	public String get() {
		return "BeanC";
	}

}
