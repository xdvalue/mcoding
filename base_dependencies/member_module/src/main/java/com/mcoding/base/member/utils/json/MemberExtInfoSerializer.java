package com.mcoding.base.member.utils.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.mcoding.base.member.bean.member.MemberExtInfo;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;

public class MemberExtInfoSerializer extends JsonSerializer<MemberExtInfo> {

	@Override
	public void serialize(MemberExtInfo memberExtInfo, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		String json = "";
		if (memberExtInfo != null) {
			json = JsonUtilsForMcoding.writeValueAsString(memberExtInfo);
		}
		
		gen.writeString(json);
	}

}
