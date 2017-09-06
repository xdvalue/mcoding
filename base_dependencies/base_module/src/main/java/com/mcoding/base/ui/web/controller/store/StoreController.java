package com.mcoding.base.ui.web.controller.store;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.bean.store.StoreExample;
import com.mcoding.base.ui.service.store.StoreService;

import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("store")
public class StoreController {
	
	@Resource
	protected StoreService storeService;
	
	@RequestMapping("service/toMainView")
	public ModelAndView toMainView(){
		ModelAndView mv = new ModelAndView("base/store/toMainView");
		return mv;
	}
	
	@RequestMapping("service/toAddView")
	public ModelAndView toAddView() {
		ModelAndView view = new ModelAndView();
		view.setViewName("base/store/toAddView");
		return view;
	}
	
	@RequestMapping("service/toUpdateViewById")
	public ModelAndView toUpdateViewById(int id) {
		ModelAndView view = new ModelAndView();
		Store store = this.storeService.queryObjById(id);
		view.addObject("store", store);
		view.setViewName("base/store/toAddView");
		return view;
	}
	
	@RequestMapping("service/create")
	@ResponseBody
	public JsonResult<String> create(@RequestBody Store store) {
		if (store ==null || StringUtils.isBlank(store.getStoreName()) || 
				store.getGradeId() == null || store.getGradeId() == 0 || 
				store.getMemberId() == null || store.getMemberId() == 0 || 
				store.getScId() == null || store.getScId() == 0 ||
				StringUtils.isBlank(store.getStoreDomain())) {
			throw new CommonException("提交的参数不完整，请刷新后重试");
		}
		
		store.setMemberName(StringUtils.defaultIfBlank(store.getMemberName(), "未知"));
		this.storeService.addObj(store);
		
		JsonResult<String> result = new JsonResult<>();
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}
	
	@RequestMapping("service/edit")
	@ResponseBody
	public JsonResult<String> edit(@RequestBody Store store) {
		if (store.getId() == null || store.getId() <= 0) {
			throw new CommonException("提交的 id不能为空，请重新提交");
		}
		
		if (store ==null || StringUtils.isBlank(store.getStoreName()) || 
				store.getGradeId() == null || store.getGradeId() == 0 || 
				store.getMemberId() == null || store.getMemberId() == 0 || 
				store.getScId() == null || store.getScId() == 0 ||
				StringUtils.isBlank(store.getStoreDomain())) {
			throw new CommonException("提交的参数不完整，请刷新后重试");
		}
		
		store.setMemberName(StringUtils.defaultIfBlank(store.getMemberName(), "未知"));
		this.storeService.modifyObj(store);
		
		JsonResult<String> result = new JsonResult<>();
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}
	
	@RequestMapping("service/deleteById")
	@ResponseBody
	public JsonResult<String> deleteById(int id){
		this.storeService.deleteObjById(id);
		
		JsonResult<String> result = new JsonResult<>();
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}
	
	@RequestMapping("service/findByPage")
	@ResponseBody
	public PageView<Store> findByPage(
			@ApiParam(name="iDisplayStart",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, 
			@ApiParam(name="iDisplayLength",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, 
			@ApiParam(name="sSearch", value="查询条件") String sSearch){

		PageView<Store> pageView = new PageView<>(iDisplayStart, iDisplayLength);

		StoreExample example = new StoreExample();
		example.setPageView(pageView);
		
		StoreExample.Criteria cri1 = example.createCriteria();
		if (StringUtils.isNotBlank(sSearch)) {
			cri1.andStoreNameLike( sSearch + "%");
		}
		
		return this.storeService.queryObjByPage(example);
		
	}

}
