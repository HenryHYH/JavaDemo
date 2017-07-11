package com.helloweb.service;

import org.springframework.stereotype.Service;

@Service("UserService")
public class UserService implements IUserService {

	@Override
	public String getName(String name) {
		return "Hello " + name;
	}

}
