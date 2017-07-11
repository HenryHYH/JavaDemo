package com.helloweb.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.helloweb.entity.User;

@XmlRootElement(name = "UserArray")
public class UserListDto {

	private List<User> list;

	@XmlElement(name = "Item")
	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}
}
