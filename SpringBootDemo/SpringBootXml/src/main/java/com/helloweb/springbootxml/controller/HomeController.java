package com.helloweb.springbootxml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helloweb.springbootxml.beans.IBean;

@RestController
@RequestMapping("/")
public class HomeController {

	@Autowired
	@Qualifier("beanA")
	private IBean beanA;

	@Autowired
	@Qualifier("beanB")
	private IBean beanB;

	@Autowired
	@Qualifier("beanC")
	private IBean beanC;

	@Autowired
	private IBean beanD;

	@RequestMapping("")
	public String index() {
		return String.format("%s,%s,%s,%s", beanA.get(), beanB.get(), beanC.get(), beanD.get());
	}

}
