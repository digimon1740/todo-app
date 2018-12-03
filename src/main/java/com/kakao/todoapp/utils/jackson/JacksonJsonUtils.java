package com.kakao.todoapp.utils.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Map;

@Slf4j
public class JacksonJsonUtils {

	private static final ObjectMapper om = new ObjectMapper();

	public static <T> T readValue(String content, Class<T> valueType) throws Exception {
		T value = null;
		try {
			value = om.readValue(content, valueType);
		} catch (Exception e) {
			log.error("Error at JacksonJsonUtils.readValue({}, {}.class)", content, valueType.getName());
			throw e;
		}
		return value;
	}

	public static <T> T readValue(Map content, Class<T> valueType) throws Exception {
		T value = null;
		try {
			value = om.readValue(writeValueAsString(content), valueType);
		} catch (Exception e) {
			log.error("Error at JacksonJsonUtils.readValue({}, {}.class)", content, valueType.getName());
			throw e;
		}
		return value;
	}

	public static String writeValueAsString(Object value) throws Exception {
		String json = null;
		try {
			json = om.writeValueAsString(value);
			if (StringUtils.isEmpty(json) || "null".equals(json))
				return "";
		} catch (Exception e) {
			log.error("Error at JacksonJsonUtils.writeValueAsString(Object)", e);
		}
		return json;
	}

}
