package com.helloweb.springbootmybatis.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.helloweb.springbootmybatis.entity.User;
import com.helloweb.springbootmybatis.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IUserService userService;

	@RequestMapping("")
	public List<User> list() {
		return userService.list();
	}

	@RequestMapping("/count")
	public int count() {
		return userService.count();
	}

	@RequestMapping(value = "/add/{name}", method = RequestMethod.GET)
	public int add(@PathVariable String name) {

		log.info(name);

		User item = new User();
		item.setName(name);

		return userService.add(item);
	}
}
