package com.employee.utils;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

	static ObjectMapper mapper = new ObjectMapper();

	public static String convertObjectToJsonString(Object object) throws IOException {
		return mapper.writeValueAsString(object);
	}

	public static <T> T convertJsonStringToObject(String jsonString, Class<T> className) throws IOException {
		return mapper.readValue(jsonString, className);

	}
}
