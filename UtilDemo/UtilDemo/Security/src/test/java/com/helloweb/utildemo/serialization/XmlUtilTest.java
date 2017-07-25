package com.helloweb.utildemo.serialization;

import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

public class XmlUtilTest {

	private final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><testXmlObject><age>1</age><birthday>2017-01-01T02:03:04+08:00</birthday><nickname>Henry</nickname></testXmlObject>";
	private final String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><testXmlObject><birthday>2017-01-01T02:03:04+08:00</birthday><nickname>Henry</nickname></testXmlObject>";

	@Test
	public void testDeserialize() throws Exception {
		TestXmlObject obj = XmlUtil.deserialize(xml, TestXmlObject.class);

		Assert.assertEquals("Henry", obj.getName());
		Assert.assertEquals(0, obj.getAge());
		Assert.assertEquals(new GregorianCalendar(2017, 0, 1, 2, 3, 4).getTime(), obj.getBirthday());
	}

	@Test
	public void testSerialize() throws Exception {
		TestXmlObject obj = new TestXmlObject();
		obj.setName("Henry");
		obj.setAge(1);
		obj.setBirthday(new GregorianCalendar(2017, 0, 1, 2, 3, 4).getTime());

		String actualXml = XmlUtil.serialize(obj, TestXmlObject.class);

		Assert.assertEquals(expected, actualXml);
	}

}
