package com.helloweb.utildemo.serialization;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlUtil {

	@SuppressWarnings("unchecked")
	public static <T> T deserialize(String xml, Class<T> classType) throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(classType);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(xml);

		return (T) unmarshaller.unmarshal(reader);
	}

	public static <T> String serialize(T entity, Class<T> classType) throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(classType);
		Marshaller marshaller = jaxbContext.createMarshaller();
		// marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); //格式化

		StringWriter sw = new StringWriter();
		marshaller.marshal(entity, sw);

		return sw.toString();
	}
}
