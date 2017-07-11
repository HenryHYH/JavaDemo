package com.helloweb.jUnitDemo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class HelloworldTestWithData {

	@Test
	@Parameters({ "1,2,3", "1,9,10", "1,-1,0" })
	public void test(int a, int b, int expected) {
		assertEquals(expected, a + b);
	}

	@Test
	@Parameters({ "1,1", "2|2" })
	public void testStr(int a, String expected) {
		assertEquals(expected, Integer.toString(a));
	}
}
