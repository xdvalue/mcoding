package com.mcoding.base.member.utils.json;

import java.io.IOException;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.mcoding.base.member.bean.member.MemberExtInfo;

public class MemberExtInfoSerializer extends JsonSerializer<MemberExtInfo> {

	@Override
	public void serialize(MemberExtInfo memberExtInfo, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		String json = "";
		if (memberExtInfo != null) {
//			json = JsonUtilsForMcoding.writeValueAsString(memberExtInfo);
			ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
			json = objectMapper.writeValueAsString(memberExtInfo);
		}
		
		gen.writeString(json);
	}

}
