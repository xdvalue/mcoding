package com.mcoding.base.ui.persistence.common.impl;

import java.io.Serializable;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcoding.base.core.IExample;

public abstract class BaseExample<T extends Serializable> implements IExample<Serializable>{
	
	@Override
	public String toJson() throws JsonProcessingException {
		return Jackson2ObjectMapperBuilder.json().build().writeValueAsString(this);
	}
}
