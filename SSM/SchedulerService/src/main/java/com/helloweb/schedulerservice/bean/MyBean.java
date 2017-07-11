package com.helloweb.schedulerservice.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class MyBean {

	public void printMessage() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		System.out.println(format.format(new Date()) + " MyBean.printMessage");
	}
}
