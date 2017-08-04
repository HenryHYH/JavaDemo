package com.helloweb.springbootaop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(
			@RequestParam(required = false, defaultValue = "Henry") String name,
			@RequestParam(required = false, defaultValue = "1") int number) {

		return String.format("Hello %s:%d", name, number);
	}

}
