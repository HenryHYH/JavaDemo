package com.helloweb.service;

import java.util.List;

import com.helloweb.entity.AopEntity;

public interface IAopService {

	public String toLower(String name);

	public List<AopEntity> toList(String name);

	public void doNothing(String name);
}
