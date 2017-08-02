package com.helloweb.springbootmybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.helloweb.springbootmybatis.entity.User;

@Mapper
public interface UserDao {

	int add(User item);

	int count();

	List<User> list();
}
