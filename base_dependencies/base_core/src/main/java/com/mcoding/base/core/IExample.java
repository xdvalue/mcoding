package com.mcoding.base.core;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IExample<T extends Serializable> {
	
	public PageView<T> getPageView() ;

	public void setPageView(PageView<T> pageView) ;
	
	public String toJson() throws JsonProcessingException;

}

