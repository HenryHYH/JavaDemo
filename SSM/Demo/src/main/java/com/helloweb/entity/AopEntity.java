package com.helloweb.entity;

public class AopEntity {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("AopEntity = { Name: %s }", name);
	}

}
