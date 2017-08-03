package com.helloweb.springbootxml.beans;

import org.springframework.stereotype.Component;

@Component
public class TestA implements ITest {

	@Override
	public String get() {
		return "TestA";
	}

}
