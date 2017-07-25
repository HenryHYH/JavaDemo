package com.helloweb.utildemo.networking;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HttpUtilTest {

	private final String url = "http://localhost:10000/";
	private final int port = 10000;
	private HttpServerMock mock;

	@Before
	public void setUp() throws Exception {
		mock = new HttpServerMock(port);
	}

	@After
	public void tearDown() throws Exception {
		if (null != mock) {
			mock.stop();
		}
	}

	@Test
	public void testGet() throws Exception {

		String expected = "Hello world!!!";
		mock.start(expected, "text/plain");

		Assert.assertEquals(expected, HttpUtil.get(url));
	}

	@Test
	public void testPost() throws Exception {

		String expected = "Hello world!!!";
		mock.start(expected, "text/plain");

		Assert.assertEquals(expected, HttpUtil.post(url, ""));
	}

}
