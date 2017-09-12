package com.helloweb.aliyundemo.recommend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("")
	public String index() {
		return "Home/Index";
	}

}
