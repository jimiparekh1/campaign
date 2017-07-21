package com.myapps.campaigns.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	
	private static ObjectMapper staticMapper = new ObjectMapper();
	static {
		staticMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		staticMapper.setSerializationInclusion(Include.NON_NULL);
	}

	public static String asJson(final Object obj) {
		try {
			return staticMapper.writeValueAsString(obj);
		} catch (final JsonProcessingException e){
			throw new RuntimeException("Invalid Json Object");
		}
	}

}
