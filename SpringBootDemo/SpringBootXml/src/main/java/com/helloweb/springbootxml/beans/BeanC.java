package com.helloweb.springbootxml.beans;

public class BeanC implements IBean {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void show() {
		System.out.println("BeanC");
	}

	@Override
	public String get() {
		return "BeanC" + getName();
	}

}
