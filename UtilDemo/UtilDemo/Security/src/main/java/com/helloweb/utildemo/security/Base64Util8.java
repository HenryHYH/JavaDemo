package com.helloweb.utildemo.security;

import java.util.Base64;

public class Base64Util8 {

	public static String encode(byte[] data) {
		return Base64.getEncoder().encodeToString(data);
	}

	public static String encode(String str) {
		return encode(str.getBytes());
	}

	public static String decode(String str) {
		byte[] tmp = Base64.getDecoder().decode(str);

		return new String(tmp);
	}
}
