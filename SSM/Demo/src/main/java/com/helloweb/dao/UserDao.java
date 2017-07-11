package com.helloweb.dao;

import java.util.List;

import com.helloweb.entity.User;

public interface UserDao {
	public List<User> selectUser();
}
