package com.helloweb.utildemo.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

	private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

	public static void main(String[] args) {
		logger.info("now {}", "starting server");
	}
}
