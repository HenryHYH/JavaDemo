package com.helloweb.springbootshiro.service;

import org.springframework.stereotype.Service;

import com.helloweb.springbootshiro.entity.User;

@Service
public class UserService {

	public User findByLoginName(String userName) {
		User info = new User();
		info.setName(userName);

		return info;
	}

}
