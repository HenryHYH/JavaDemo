package com.helloweb.utildemo.security;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class SymmetricCryptoUtil {

	public static byte[] encrypt(String data, String key, String algorithm, String transformation) throws Exception {
		return encrypt(data.getBytes(), algorithm, transformation, key);
	}

	public static byte[] encrypt(byte[] data, String key, String algorithm, String transformation) throws Exception {
		return execute(data, key, algorithm, transformation, Cipher.ENCRYPT_MODE);
	}

	public static byte[] decrypt(byte[] data, String key, String algorithm, String transformation) throws Exception {
		return execute(data, key, algorithm, transformation, Cipher.DECRYPT_MODE);
	}

	private static byte[] execute(byte[] data, String key, String algorithm, String transformation, int mode)
			throws Exception {
		SecureRandom random = new SecureRandom();
		DESKeySpec keySpec = new DESKeySpec(key.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(keySpec);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(mode, secretKey, random);

		return cipher.doFinal(data);
	}
}
