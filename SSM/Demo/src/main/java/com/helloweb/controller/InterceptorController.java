package com.helloweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/interceptor")
public class InterceptorController {

	@RequestMapping("")
	@ResponseBody
	public String index() {
		return "interceptor";
	}
}
