package com.helloweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.helloweb.entity.User;
import com.helloweb.model.UserListDto;
import com.helloweb.service.impl.UserService;

@Controller
@RequestMapping("/xml")
public class XmlController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "detail/{name}", method = RequestMethod.GET, produces = "application/xml")
	@ResponseBody
	public User get(@PathVariable String name) {
		User entity = new User();
		entity.setName(name);

		return entity;
	}

	@RequestMapping(value = "list", method = RequestMethod.GET, produces = "application/xml")
	@ResponseBody
	public UserListDto getList() {
		UserListDto dto = new UserListDto();
		dto.setList(userService.selectUser());

		return dto;
	}
}
