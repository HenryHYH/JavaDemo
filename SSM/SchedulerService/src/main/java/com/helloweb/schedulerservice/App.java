package com.helloweb.schedulerservice;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	public static void main(String args[]) {
		AbstractApplicationContext context = new GenericXmlApplicationContext("quartz-context.xml");
	}
}
