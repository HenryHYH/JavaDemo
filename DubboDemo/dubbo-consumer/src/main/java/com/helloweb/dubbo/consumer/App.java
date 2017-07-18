package com.helloweb.dubbo.consumer;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.helloweb.dubbo.api.ITestService;

public class App {

	public static void main(String[] args) throws Exception {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		context.start();

		ITestService service = (ITestService) context.getBean("testService");
		String message = service.hello("Henry");
		System.out.println(message);

		System.in.read();
	}
}
