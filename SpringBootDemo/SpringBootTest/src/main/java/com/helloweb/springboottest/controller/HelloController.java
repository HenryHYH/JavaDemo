package com.helloweb.springboottest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String index() {
		return "Hello";
	}

	@RequestMapping("/test")
	public String test() {
		return "test";
	}
}
