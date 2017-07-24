package com.helloweb.utildemo.serialization;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

public class JsonUtilTest {

	private String json = "{\"name\":{\"first\":\"Joe\",\"last\":\"Sixpack\"},\"gender\":\"MALE\",\"verified\":false,\"image\":\"Rm9vYmFyIQ==\",\"age\":100,\"birthday\":\"2017-01-01 02:03:04\"}";
	private String expected = "{\"name\":{\"first\":\"Joe\",\"last\":\"Sixpack\"},\"gender\":\"MALE\",\"verified\":false,\"image\":\"Rm9vYmFyIQ==\",\"birthday\":\"2017-01-01 02:03:04\"}";

	@Test
	public void test() throws Exception {
		TestObject obj = JsonUtil.deserialize(json, TestObject.class);

		Assert.assertEquals("Joe", obj.getName().getFirst());
		Assert.assertEquals("Sixpack", obj.getName().getLast());
		Assert.assertEquals(TestObject.Gender.MALE, obj.getGender());
		Assert.assertEquals(false, obj.isVerified());
		Assert.assertEquals(0, obj.getAge());
		Calendar c = new GregorianCalendar(2017, 0, 1, 02, 03, 04);
		Assert.assertEquals(c.getTime(), obj.getBirthday());

		String newJson = JsonUtil.serialize(obj);
		System.out.println(newJson);

		Assert.assertEquals(expected, newJson);
	}
}
