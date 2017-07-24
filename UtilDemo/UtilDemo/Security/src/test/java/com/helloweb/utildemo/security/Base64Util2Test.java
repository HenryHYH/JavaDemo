package com.helloweb.utildemo.security;

import org.junit.Assert;
import org.junit.Test;

public class Base64Util2Test {

	private String plainText = "mytest";
	private String base64Str = "bXl0ZXN0";

	@Test
	public void testEncode() {
		Assert.assertEquals(base64Str, Base64Util2.encode(plainText));
	}

	@Test
	public void testDecode() {
		Assert.assertEquals(plainText, Base64Util2.decode(base64Str));
	}

}
