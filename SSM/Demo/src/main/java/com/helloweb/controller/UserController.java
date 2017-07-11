package com.helloweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.helloweb.entity.User;
import com.helloweb.service.IUserService;

@Controller
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping("/user")
	public ModelAndView index() {
		ModelAndView result = new ModelAndView("user/index");

		List<User> list = userService.selectUser();
		if (null == list)
			result.addObject("count", "NULL");
		else
			result.addObject("count", list.size());

		return result;
	}

	@RequestMapping("/user/test")
	public String test() {
		return "user/test";
	}
}
