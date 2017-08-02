package com.helloweb.springbootmybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.helloweb.springbootmybatis.entity.User;

@Component
@Mapper
public interface UserMapper {

	@Insert("INSERT INTO user (name) VALUES (#{name})")
	int add(User item);

	@Select("SELECT COUNT(1) Cnt FROM user")
	int count();

	@Select("SELECT * FROM user")
	List<User> list();
}
