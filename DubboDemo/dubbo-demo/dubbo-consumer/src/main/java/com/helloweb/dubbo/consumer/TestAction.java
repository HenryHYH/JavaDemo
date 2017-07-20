package com.helloweb.dubbo.consumer;

import com.helloweb.dubbo.api.ITestService;

public class TestAction {
	private ITestService testService;

	public void setTestService(ITestService testService) {
		this.testService = testService;
	}

	public void start() {
		String message = testService.hello("Henry");
		System.out.println(message);
	}
}
