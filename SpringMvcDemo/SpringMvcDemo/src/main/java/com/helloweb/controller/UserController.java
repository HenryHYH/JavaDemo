package com.helloweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.helloweb.service.IUserService;

@Controller
public class UserController {

	@Autowired
	@Qualifier("UserService")
	private IUserService userService;

	@RequestMapping("/user")
	public ModelAndView Index(String name) {
		String message = userService.getName(name);
		
		ModelAndView result = new ModelAndView();
		result.addObject("message", message);
		result.setViewName("user/index");

		return result;
	}
}
