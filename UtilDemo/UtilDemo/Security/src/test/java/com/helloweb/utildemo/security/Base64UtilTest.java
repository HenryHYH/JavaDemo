package com.helloweb.utildemo.security;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class Base64UtilTest {

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { "mytest", "bXl0ZXN0" }, { "Base64编码说明", "QmFzZTY057yW56CB6K+05piO" } };

		return Arrays.asList(data);
	}

	private String plainText;
	private String base64Str;

	public Base64UtilTest(String plainText, String base64Str) {
		this.plainText = plainText;
		this.base64Str = base64Str;
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testEncode() {
		Assert.assertEquals(base64Str, Base64Util.encode(plainText));
	}

	@Test
	public void testDecode() {
		Assert.assertEquals(plainText, Base64Util.decode(base64Str));
	}

}
