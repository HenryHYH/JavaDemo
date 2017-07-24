package com.helloweb.utildemo.security;

/**
 * DES加密和解密过程中，密钥长度都必须是8的倍数
 */
public class DESUtil {

	private static String algorithm = "DES";
	private static String transformation = "DES";

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
