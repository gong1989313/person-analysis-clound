package com.person.analysis.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonJsonUtil {
	private static final ObjectMapper mapper = new ObjectMapper();

	public static Map<String, Object> json2Map(String json) throws IOException {
		Map map = (Map) mapper.readValue(json, Map.class);
		return map;
	}

	public static String object2Json(Object object) throws IOException {
		return mapper.writeValueAsString(object);
	}

	public static <T> T json2Object(String json, Class<T> clazz) throws IOException {
		return mapper.readValue(json, clazz);
	}

	public static List<?> json2List(String json, TypeReference<?> typeRef) throws IOException {
		List list = (List) mapper.readValue(json, typeRef);
		return list;
	}
}
