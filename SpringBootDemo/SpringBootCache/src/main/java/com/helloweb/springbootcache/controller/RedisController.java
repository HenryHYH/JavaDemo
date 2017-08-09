package com.helloweb.springbootcache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.helloweb.springbootcache.entity.RedisEntity;
import com.helloweb.springbootcache.service.RedisService;

@RestController
public class RedisController {

	@Autowired
	private RedisService service;

	@GetMapping("/redis")
	public String index() {
		return service.test();
	}

	@GetMapping("/redis/get")
	public RedisEntity get(@RequestParam long id, @RequestParam String name) {
		return service.get(id, name);
	}

}
