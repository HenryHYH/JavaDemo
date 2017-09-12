package com.helloweb.aliyundemo.recommend.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helloweb.aliyundemo.recommend.sdk.RecommendFactory;

@RestController
public class HomeController {

	@RequestMapping("")
	public String index() {
		return "Home/Index";
	}

	@RequestMapping("/home/rec")
	public String rec() throws Exception {
		RecommendFactory.rec();

		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
	}

}
