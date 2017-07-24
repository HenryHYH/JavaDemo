package com.helloweb.utildemo.security;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {

	public static String encode(byte[] data) {
		return Base64.encodeBase64String(data);
	}

	public static String encode(String str) {
		return encode(str.getBytes());
	}

	public static String decode(String str) {

		byte[] tmp = Base64.decodeBase64(str);

		return new String(tmp);
	}
}
