package com.helloweb.service.impl;

import org.springframework.stereotype.Service;

import com.helloweb.service.ITestService;

@Service
public class TestService implements ITestService {

	public String get() {
		return "A";
	}

}
