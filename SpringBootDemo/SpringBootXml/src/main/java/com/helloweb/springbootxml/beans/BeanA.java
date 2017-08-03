package com.helloweb.springbootxml.beans;

public class BeanA implements IBean {

	@Override
	public void show() {
		System.out.println("BeanA");
	}

	@Override
	public String get() {
		return "BeanA";
	}

}
