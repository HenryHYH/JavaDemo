package com.helloweb.springbootweb;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public void defaultErrorHandle(HttpServletRequest req, Exception e) {
		System.out.println("Error start");
		e.printStackTrace();
		System.out.println("Error finish");
	}
}
