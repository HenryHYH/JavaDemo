package com.helloweb.springbootvalidate.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Person {

	@NotNull
	@Size(min = 2, max = 30, message = "长度在[2,30]之间")
	private String name;

	@NotNull
	@Min(value = 18, message = "必须大于等于18")
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person(Name: " + this.name + ", Age: " + this.age + ")";
	}

}
