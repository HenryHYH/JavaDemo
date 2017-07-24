package com.helloweb.utildemo.security;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {

	public static String md5(byte[] data) {
		return DigestUtils.md5Hex(data);
	}

	public static String md5(String str) {
		return DigestUtils.md5Hex(str);
	}

	public static String sha1(byte[] data) {
		return DigestUtils.sha1Hex(data);
	}

	public static String sha1(String str) {
		return DigestUtils.sha1Hex(str);
	}
}
