package com.helloweb.schedulerservice.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class AnotherBean {

	public void printAnotherMessage() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		System.out.println(format.format(new Date()) + " AnotherBean.printAnotherMessage");
	}
}
