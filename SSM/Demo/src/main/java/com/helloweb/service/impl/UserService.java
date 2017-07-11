package com.helloweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helloweb.aspect.Log;
import com.helloweb.dao.UserDao;
import com.helloweb.entity.User;
import com.helloweb.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserDao userDao;

	@Log
	public List<User> selectUser() {
		return userDao.selectUser();
	}

}
