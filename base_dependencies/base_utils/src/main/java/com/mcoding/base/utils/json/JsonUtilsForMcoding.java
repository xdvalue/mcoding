package com.mcoding.base.utils.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtilsForMcoding {
	
	/**
	 * 把对象转化为json字符串
	 * @param object
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String writeValueAsString(Object object) throws JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	
	/**
	 * 将json字符串转化为对象
	 * @param fromValue
	 * @param toValueType
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> T convertValue(String fromValue, Class<T> toValueType) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(fromValue, toValueType);
	}
	
	/**
	 * 将json字符串转化为 列表(Collection)
	 * @param fromValue
	 * @param toValueType
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */

	public static <T> T convertCollection(String fromValue, Class<T> collectionClass, Class<?> subCollectionClass, Class<?>... elementClasses) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		JavaType javaType = objectMapper.getTypeFactory().constructParametrizedType(collectionClass, subCollectionClass, elementClasses);
		return objectMapper.readValue(fromValue, javaType);
	}
	
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?> subCollectionClass, Class<?>... elementClasses){
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.getTypeFactory().constructParametrizedType(collectionClass, subCollectionClass, elementClasses);
	}

}
