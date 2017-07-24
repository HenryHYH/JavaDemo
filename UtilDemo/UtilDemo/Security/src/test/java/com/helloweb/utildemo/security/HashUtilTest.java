package com.helloweb.utildemo.security;

import org.junit.Assert;
import org.junit.Test;

public class HashUtilTest {

	@Test
	public void testMd5() {
		String plainText = "mytest";
		String hashText = "a599d36c4c7a71ddcc1bc7259a15ac3a";

		Assert.assertEquals(hashText, HashUtil.md5(plainText));
	}

	@Test
	public void testSHA1() {
		String plainText = "mytest";
		String hashText = "f5f7305bf097af39c68b790d817d7889f788f222";

		Assert.assertEquals(hashText, HashUtil.sha1(plainText));
	}
}
