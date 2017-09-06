package com.mcoding.base.sns.web.controller.module;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
import com.mcoding.base.sns.bean.module.SnsModule;
import com.mcoding.base.sns.bean.module.SnsModuleExample;
import com.mcoding.base.sns.service.module.SnsModuleService;
import com.mcoding.base.ui.utils.common.Constant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api("微社区模块分类")
@Controller
@RequestMapping("snsModule")
public class SnsModuleController {

	@Resource
	private SnsModuleService snsModuleService;

	@ApiIgnore
	@RequestMapping("service/toAddView")
	public ModelAndView toAddView() {
		return new ModelAndView("sns/snsModule/toAddView");
	}

	@ApiIgnore
	@RequestMapping("service/toMainView")
	public ModelAndView toMainView() {
		return new ModelAndView("sns/snsModule/toMainView");
	}

	@ApiIgnore
	@RequestMapping("service/toUpdateViewById")
	public ModelAndView toUpdateViewById(int id) {
		ModelAndView view = new ModelAndView();
		SnsModule snsModule = this.snsModuleService.queryObjById(id);
		view.addObject("snsModule", snsModule);
		view.setViewName("sns/snsModule/toAddView");
		return view;
	}

	@ApiOperation(httpMethod = "POST", value = "创建模块")
	@RequestMapping("service/create")
	@ResponseBody
	public JsonResult<String> create(@RequestBody SnsModule snsModule, HttpSession session) {
		if (snsModule.getStoreId() == null || snsModule.getStoreId() == 0) {
			Integer storeId = (Integer) session.getAttribute("storeId");
			snsModule.setStoreId(storeId);
		}

		this.snsModuleService.addObj(snsModule);

		JsonResult<String> result = new JsonResult<>();
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(httpMethod = "POST", value = "编辑模块")
	@RequestMapping("service/edit")
	@ResponseBody
	public JsonResult<String> edit(@RequestBody SnsModule snsModule) {
		if (snsModule.getId() == null || snsModule.getId() <= 0) {
			throw new CommonException("id 为空，保存失败");
		}

		JsonResult<String> result = new JsonResult<>();
		this.snsModuleService.modifyObj(snsModule);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(httpMethod = "GET", value = "分页查询模块")
	@RequestMapping("service/findByPage")
	@ResponseBody
	public PageView<SnsModule> findByPage(
			@ApiParam(name = "分页索引", defaultValue = "0") @RequestParam(defaultValue = "0") String iDisplayStart,
			@ApiParam(name = "每页的数量", defaultValue = "10") @RequestParam(defaultValue = "10") String iDisplayLength,
			@ApiParam(value = "查询条件") String sSearch) {

		PageView<SnsModule> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		SnsModuleExample example = new SnsModuleExample();
		example.setPageView(pageView);

		if (StringUtils.isNotBlank(sSearch)) {
			example.createCriteria().andNameLike(sSearch + "%");
		}
		example.setOrderByClause("seq_num DESC");
		return this.snsModuleService.queryObjByPage(example);
	}

	@ApiOperation(httpMethod = "GET", value = "启用或禁用模块")
	@RequestMapping("service/setIsEnableById")
	@ResponseBody
	public JsonResult<String> setIsEnableById(int id, int isEnable) {
		JsonResult<String> result = new JsonResult<>();
		// this.snsModuleService.deleteObjById(id);
		SnsModule tmp = new SnsModule();
		tmp.setId(id);
		if (Constant.YES_INT.equals(isEnable)) {
			tmp.setStatusCode(Constant.YES_INT);
		} else {
			tmp.setStatusCode(Constant.NO_INT);
		}
		this.snsModuleService.modifyObj(tmp);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(httpMethod = "GET", value = "删除模块")
	@RequestMapping("service/deleteById")
	@ResponseBody
	public JsonResult<String> deleteById(int id) {
		JsonResult<String> result = new JsonResult<>();
		this.snsModuleService.deleteObjById(id);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(value = "查询微社区所有模块", httpMethod = "GET")
	@RequestMapping(value = "/front/findAllModules")
	@ResponseBody
	public JsonResult<List<SnsModule>> findAllModules(HttpSession session) {
		JsonResult<List<SnsModule>> result = new JsonResult<>();

		Integer storeId = (Integer) session.getAttribute("storeId");
		if (storeId < 0) {
			throw new CommonException("storeid 不能小于0");
		}

		SnsModuleExample example = new SnsModuleExample();
		example.createCriteria().andStoreIdEqualTo(storeId).andStatusCodeEqualTo(Constant.YES_INT);
		example.setOrderByClause("seq_num DESC");
		
		List<SnsModule> modules = snsModuleService.queryAllObjByExample(example);

		result.setMsg("ok");
		result.setStatus("00");
		result.setData(modules);
		return result;
	}

}
