package com.mcoding.base.ui.web.controller.common;

import java.io.Serializable;

import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;

public interface BaseController<T extends Serializable> {
	
	public ModelAndView toMainView();
	
	public ModelAndView toAddView() ;
	
	public ModelAndView toDicGroupById(int id);
	
	public JsonResult<String> create(T bean);
	
	public JsonResult<String> edit(T bean);
	
	public JsonResult<String> deleteById(int id);
	
	public PageView<T> findByPage(String sSearch, String iDisplayStart, String iDisplayLength);

}
