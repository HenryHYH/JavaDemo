package com.helloweb.springbootcache.service;

import java.util.Date;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TestService {

	@Cacheable("test")
	public String test() {
		return new Date().toString();
	}

}
