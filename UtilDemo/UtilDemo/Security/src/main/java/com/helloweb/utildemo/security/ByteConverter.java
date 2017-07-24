package com.helloweb.utildemo.security;

public class ByteConverter {

	public static String toHexString(byte[] bytes) {
		if (null == bytes || 0 == bytes.length)
			return "";

		StringBuffer sb = new StringBuffer(bytes.length);
		String tmp = null;
		for (int i = 0, iMax = bytes.length; i < iMax; i++) {
			tmp = Integer.toHexString(0xFF & bytes[i]);
			if (tmp.length() < 2)
				sb.append(0);
			sb.append(tmp.toUpperCase());
		}

		return sb.toString();
	}
}
