package com.helloweb.utildemo.security;

public class DESedeUtil {

	private static String algorithm = "DESede";
	private static String transformation = "DESede";

	public static byte[] encrypt(String data, String key) throws Exception {
		return encrypt(data.getBytes(), key);
	}

	public static byte[] encrypt(byte[] data, String key) throws Exception {
		return SymmetricCryptoUtil.encrypt(data, key, algorithm, transformation);
	}

	public static byte[] decrypt(byte[] data, String key) throws Exception {
		return SymmetricCryptoUtil.decrypt(data, key, algorithm, transformation);
	}

}
