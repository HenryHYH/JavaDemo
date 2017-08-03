package com.helloweb.springbootweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

	@RequestMapping("")
	public int index() {
		return 100 / 0;
	}
}
