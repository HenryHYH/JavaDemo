package com.helloweb.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.helloweb.service.ITestService;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private ITestService testService;

	@Autowired
	private ITestService testBService;

	@Autowired
	@Qualifier("testBService")
	private ITestService testCService;

	@Resource(name = "testService")
	private ITestService testDService;

	@RequestMapping("/index")
	@ResponseBody
	public String index() {

		return testService.get() + "_" + testBService.get() + "_" + testCService.get() + "_" + testDService.get();
	}
}
