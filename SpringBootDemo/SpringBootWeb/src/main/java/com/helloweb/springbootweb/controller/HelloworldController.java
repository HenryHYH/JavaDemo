package com.helloweb.springbootweb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.helloweb.springbootweb.dto.UserDto;

@Controller
@RequestMapping("/hello")
public class HelloworldController {

	@RequestMapping("")
	public ModelAndView index() {

		List<UserDto> list = new ArrayList<>();

		UserDto item = new UserDto();
		item.setName("A");
		list.add(item);
		item = new UserDto();
		item.setName("B");
		list.add(item);
		item = new UserDto();
		item.setName("C");
		list.add(item);

		ModelAndView result = new ModelAndView("hello/index");
		result.addObject("list", list);

		return result;
	}
}
