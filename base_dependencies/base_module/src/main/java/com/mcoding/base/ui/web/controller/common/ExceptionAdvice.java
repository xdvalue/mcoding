package com.mcoding.base.ui.web.controller.common;

import java.util.Hashtable;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.CommonException;

@ControllerAdvice
public class ExceptionAdvice {

Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class); 
	
	/** 
     * 400 - Bad Request 
     */  
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Map<String, String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {  
        logger.error("参数解析失败", e);
        
        Map<String, String> result = new Hashtable<>();
        result.put("status", "400");
        result.put("msg", "参数解析失败");
        result.put("data", "");
        result.put("size", "");
        return result;  
    }  
  
    /** 
     * 405 - Method Not Allowed 
     */  
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Map<String, String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {  
    	logger.error("不支持当前请求方法", e);  
    	Map<String, String> result = new Hashtable<>();
        result.put("status", "405");
        result.put("msg", "不支持当前请求方法");
        result.put("data", "");
        result.put("size", "");
        return result;  
    }  
  
    /** 
     * 415 - Unsupported Media Type 
     */  
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public Map<String, String> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {  
        logger.error("不支持当前媒体类型", e);  
        Map<String, String> result = new Hashtable<>();
        result.put("status", "415");
        result.put("msg", "不支持当前媒体类型");
        result.put("data", "");
        result.put("size", "");
        return result;  
    }  
  
    /** 
     * 500 - Internal Server Error 
     */  
    @ExceptionHandler(Exception.class) 
    @ResponseBody
    public Map<String, String> handleException(Exception e) {  
        logger.error("服务运行异常", e);
        
        Map<String, String> result = new Hashtable<>();
        result.put("status", "500");
        result.put("msg", "页面加载异常，请刷新重试");
        result.put("data", "");
        result.put("size", "");
        return result;  
    }
    
    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public Map<String, Object> handleCmException(CommonException e){
    	logger.debug("业务异常:" + e.getMessage());
    	
    	Map<String, Object> result = new Hashtable<>();
        result.put("status", "error");
        result.put("size", "");
        result.put("msg", "提交失败：" + e.getMessage());
        if (e.getData() != null) {
        	result.put("data", e.getData());
		}
        
        return result;
    }
}
