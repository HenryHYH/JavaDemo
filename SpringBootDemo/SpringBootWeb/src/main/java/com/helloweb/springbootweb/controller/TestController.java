package com.helloweb.springbootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping("")
	public ModelAndView index() {

		ModelAndView result = new ModelAndView("/test/index");
		result.addObject("name", "Henry");

		return result;
	}
}
