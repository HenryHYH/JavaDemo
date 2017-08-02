package com.helloweb.springbootmybatis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helloweb.springbootmybatis.dao.UserDao;
import com.helloweb.springbootmybatis.dao.UserMapper;
import com.helloweb.springbootmybatis.entity.User;
import com.helloweb.springbootmybatis.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserDao userDao;

	@Override
	public int add(User item) {
		return userDao.add(item);
	}

	@Override
	public int count() {
		return userDao.count();
	}

	@Override
	public List<User> list() {
		return userMapper.list();
	}

}
