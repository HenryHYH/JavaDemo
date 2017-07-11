package com.helloweb.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User extends BaseEntity<Long> {

	private String name;
	private Date createTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		return "User [id=" + getId() + ", name=" + name + ", createTime=" + format.format(createTime) + "]";
	}
}
