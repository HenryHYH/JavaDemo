package com.helloweb.springbootmybatis.service;

import java.util.List;

import com.helloweb.springbootmybatis.entity.User;

public interface IUserService {

	int add(User item);

	int count();

	List<User> list();
}
