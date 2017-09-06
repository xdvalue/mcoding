package com.mcoding.base.utils.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException, JsonProcessingException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = "";
        if (date != null) {
            dateStr = format.format(date);
        }
        jsonGenerator.writeString(dateStr);
		
	}
}
