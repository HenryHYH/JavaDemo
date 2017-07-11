package com.helloweb.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.helloweb.aspect.Log;
import com.helloweb.entity.AopEntity;
import com.helloweb.service.IAopService;

@Service
public class AopService implements IAopService {

	@Log
	public String toLower(String name) {
		if (null == name)
			return "";

		return name.toLowerCase();
	}

	@Log
	public List<AopEntity> toList(String name) {
		AopEntity item = new AopEntity();
		item.setName(name);

		return Arrays.asList(item);
	}

	@Log
	public void doNothing(String name) {
	}
}
