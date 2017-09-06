package com.mcoding.base.member.utils.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.mcoding.base.member.bean.member.MemberExtInfo;

public class MemberExtInfoDeserializer extends JsonDeserializer<MemberExtInfo>{

	@Override
	public MemberExtInfo deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		
		return ctxt.readValue(p, MemberExtInfo.class);
	}

}
