package com.helloweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;

import com.helloweb.service.MyService;

@Controller
public class FirstController {

	@Autowired
	private MyService service;

	@RequestMapping("/hello")
	public String hello(/* @RequestParam("userName1") */ String userName, Model model) {
		String value = service.process(userName);
		model.addAttribute("userName", value);

		return "demo";
	}

}