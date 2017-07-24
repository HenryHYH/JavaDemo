package com.helloweb.utildemo.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	public static <T> T deserialize(String json, Class<T> valueType) throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		return mapper.readValue(json, valueType);
	}

	public static String serialize(Object obj) throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(obj);
	}
}
