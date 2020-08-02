package br.com.pupposoft.trustly.connector.git.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	public static String generateJsonStringFromObject(Object object) throws JsonProcessingException {
		final ObjectMapper mapper = new ObjectMapper();
		return  mapper.writeValueAsString(object);
	}
}
