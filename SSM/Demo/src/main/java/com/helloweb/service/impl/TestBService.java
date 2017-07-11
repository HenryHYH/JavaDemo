package com.helloweb.service.impl;

import org.springframework.stereotype.Service;

import com.helloweb.service.ITestService;

@Service
public class TestBService implements ITestService {

	public String get() {
		return "B";
	}

}
