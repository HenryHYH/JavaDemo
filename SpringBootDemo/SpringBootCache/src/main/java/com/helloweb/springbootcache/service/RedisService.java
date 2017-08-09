package com.helloweb.springbootcache.service;

import java.util.Date;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.helloweb.springbootcache.entity.RedisEntity;

@Service
@CacheConfig(cacheNames = "redis")
public class RedisService {

	@Cacheable
	public String test() {
		return new Date().toString();
	}

	@Cacheable
	public RedisEntity get(long id, String name) {
		RedisEntity entity = new RedisEntity();
		entity.setId(id);
		entity.setName(name);
		entity.setCreateTime(new Date());

		return entity;
	}

}
