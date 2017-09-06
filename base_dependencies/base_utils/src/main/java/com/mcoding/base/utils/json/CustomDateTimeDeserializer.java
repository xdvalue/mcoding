package com.mcoding.base.utils.json;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomDateTimeDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		try {
			String dateTimeStr = parser.getValueAsString();
			return DateUtils.parseDate(dateTimeStr, new String[] { "yyyy-MM-dd HH:mm:ss" });
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
