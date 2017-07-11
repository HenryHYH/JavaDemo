package com.helloweb.service;

import java.util.List;

import com.helloweb.entity.User;

public interface IUserService {
	List<User> selectUser();
}
