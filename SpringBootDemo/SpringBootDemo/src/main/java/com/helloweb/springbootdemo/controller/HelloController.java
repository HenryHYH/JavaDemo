package com.helloweb.springbootdemo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String index() {
		return "Hello world!!!";
	}

	@RequestMapping("/hello/{name}")
	public String hello(@PathVariable String name) {
		return "Hello " + name;
	}

	@RequestMapping("/hello1")
	public String hello1() {
		return "Hello 1";
	}

	@RequestMapping("/hello2")
	public List<String> hello2() {
		return Arrays.asList("A", "B", "C");
	}
}
