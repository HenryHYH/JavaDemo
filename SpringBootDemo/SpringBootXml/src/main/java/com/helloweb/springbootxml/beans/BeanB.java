package com.helloweb.springbootxml.beans;

public class BeanB implements IBean {

	@Override
	public void show() {
		System.out.println("BeanB");
	}

	@Override
	public String get() {
		return "BeanB";
	}

}
