package com.helloweb.utildemo.networking;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class HttpUtilTest {

	@Test
	public void testGet() throws IOException {

		String expected = "# JavaDemo\nJava demo\n";
		String url = "https://raw.githubusercontent.com/HenryHYH/JavaDemo/master/README.md";
		String message = HttpUtil.get(url);

		Assert.assertEquals(expected, message);
	}

	@Test
	public void testPost() {
		Assert.assertTrue(true);
	}

}
