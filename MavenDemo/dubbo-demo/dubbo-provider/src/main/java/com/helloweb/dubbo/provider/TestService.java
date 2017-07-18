package com.helloweb.dubbo.provider;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.helloweb.dubbo.api.ITestService;

public class TestService implements ITestService {

	public String hello(String name) {
		if (null == name)
			name = "Unknown";

		return String.format("Hello %s !!!", name);
	}

	public String now() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		return df.format(new Date());
	}
}
