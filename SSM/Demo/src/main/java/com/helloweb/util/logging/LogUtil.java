package com.helloweb.util.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
	private static Logger log = LoggerFactory.getLogger(LogUtil.class);

	public static void info(String message) {
		log.info(message);
	}
}
