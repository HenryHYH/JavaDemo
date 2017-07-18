package com.helloweb.dubbo.provider;

import com.helloweb.dubbo.api.ITestService;

public class TestService implements ITestService {

	public String hello(String name) {
		if (null == name)
			name = "unknown";

		return "Hello " + name;
	}

}
