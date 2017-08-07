package com.helloweb.springbootquartz.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SimpleService {

	private static final Logger LOG = LoggerFactory.getLogger(SimpleService.class);

	public void processData(String key) {
		LOG.info(String.format("%s - %s", key, new Date().toString()));
	}

}
