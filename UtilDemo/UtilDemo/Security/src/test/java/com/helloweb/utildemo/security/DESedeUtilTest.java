package com.helloweb.utildemo.security;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DESedeUtilTest {

	private String plainText;
	private String key;

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { "mytest", "01234567" }, { "DES加密解密", "01234567" } };

		return Arrays.asList(data);
	}

	public DESedeUtilTest(String plainText, String key) {
		this.plainText = plainText;
		this.key = key;
	}

	@Test
	public void test() throws Exception {
		byte[] encryptData = DESedeUtil.encrypt(plainText, key);
		byte[] decryptData = DESedeUtil.decrypt(encryptData, key);
		String decryptText = new String(decryptData);

		Assert.assertEquals(plainText, decryptText);
	}
}
