package com.helloweb.utildemo.security;

import org.junit.Assert;
import org.junit.Test;

public class Base64Util8Test {

	private String plainText = "mytest";
	private String base64Str = "bXl0ZXN0";

	@Test
	public void testEncode() {
		Assert.assertEquals(base64Str, Base64Util8.encode(plainText));
	}

	@Test
	public void testDecode() {
		Assert.assertEquals(plainText, Base64Util8.decode(base64Str));
	}

}
