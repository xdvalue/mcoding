package com.mcoding.base.core;

import java.io.Serializable;

public class JsonResult<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String status;//success exception error
	
	private String msg;

    private T data;
    
    private Integer size;

    
	public JsonResult() {
		super();
		status="error";
		msg="none message";
		size=0;
		data=null;
	}

	public JsonResult(String status, String msg, T data, Integer size) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
		this.size = size;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
    
    
}