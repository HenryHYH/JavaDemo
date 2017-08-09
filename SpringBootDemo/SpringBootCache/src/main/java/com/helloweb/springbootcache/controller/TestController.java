package com.helloweb.springbootcache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helloweb.springbootcache.service.TestService;

@RestController
public class TestController {

	@Autowired
	private TestService service;

	@GetMapping("/")
	public String test() {
		return service.test();
	}

}
