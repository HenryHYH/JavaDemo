package com.helloweb.utildemo.security;

import javax.xml.bind.DatatypeConverter;

public class Base64Util2 {

	public static String encode(byte[] data) {
		return DatatypeConverter.printBase64Binary(data);
	}

	public static String encode(String str) {
		return encode(str.getBytes());
	}

	public static String decode(String str) {

		byte[] tmp = DatatypeConverter.parseBase64Binary(str);

		return new String(tmp);
	}
}
