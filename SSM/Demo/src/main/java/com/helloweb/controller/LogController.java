package com.helloweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.helloweb.util.logging.LogUtil;

@Controller
@RequestMapping("/log")
public class LogController {

	@RequestMapping("")
	@ResponseBody
	public String index() {

		LogUtil.info("Hello world");

		return "Success";
	}
}
