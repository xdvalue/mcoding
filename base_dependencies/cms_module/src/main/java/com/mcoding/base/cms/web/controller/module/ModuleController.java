package com.mcoding.base.cms.web.controller.module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.cms.bean.module.Module;
import com.mcoding.base.cms.bean.module.ModuleExample;
import com.mcoding.base.cms.service.module.ModuleService;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.ui.utils.StoreUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ModuleController
 * 
 * @author acer
 * 
 */
@Api("模板管理")
@Controller
@RequestMapping("module")
public class ModuleController {
	@Autowired
	private ModuleService moduleService;

	@ApiOperation(value = "模块页面跳转", httpMethod = "GET")
	@RequestMapping("service/init")
	public ModelAndView init() {
		ModelAndView mv = new ModelAndView("cms/module/main");
		return mv;
	}

	/**
	 * 页面跳转
	 * 
	 * @param moduleId
	 * @param actionType
	 *            (A:add,U:update,D:detail)
	 * @return
	 */
	@RequestMapping(value = "service/action", params = "actionType")
	public ModelAndView action(Integer moduleId, String actionType,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("cms/module/add");
		mv.addObject("actionType", actionType);
		if (StringUtils.isNotBlank(actionType)) {
			if ("U".equalsIgnoreCase(actionType)
					|| "D".equalsIgnoreCase(actionType)) {
				Module module = this.moduleService.queryObjById(moduleId);
				mv.addObject("module", module);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("moduleId", moduleId);
		
		List<Module> modules = moduleService.selectModules(map);
		mv.addObject("modules", modules);
		return mv;
	}

	@ApiOperation(value = "新增模块", httpMethod = "POST")
	@RequestMapping("service/create")
	@ResponseBody
	public JsonResult<String> create(@RequestBody Module module) {
		this.moduleService.addObj(module);
		JsonResult<String> result = new JsonResult<String>();
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(value = "修改模块", httpMethod = "POST")
	@RequestMapping("service/edit")
	@ResponseBody
	public JsonResult<String> edit(@RequestBody Module module) {
		this.moduleService.modifyObj(module);
		JsonResult<String> result = new JsonResult<String>();
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(value = "删除模块", httpMethod = "POST")
	@RequestMapping("service/delete")
	@ResponseBody
	public JsonResult<String> delete(int moduleId) {
		JsonResult<String> result = new JsonResult<String>();
		this.moduleService.deleteObjById(moduleId);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(value = "根据模板ID查询模块", httpMethod = "GET")
	@RequestMapping(value = { "front/findByTemplateId",
			"service/findByTemplateId" })
	@ResponseBody
	public JsonResult<Module> findByTemplateId(int moduleId) {
		JsonResult<Module> result = new JsonResult<Module>();
		Module module = this.moduleService.queryObjById(moduleId);
		result.setData(module);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(value = "分页查询模块", httpMethod = "GET")
	@RequestMapping("service/findByPage")
	@ResponseBody
	public PageView<Module> findByPage(HttpServletRequest request,
			HttpServletResponse response) {
		String iDisplayStart = "0";// 起始索引
		String iDisplayLength = "10";// 每页显示的行数
		if (StringUtils.isNotBlank(request.getParameter("iDisplayStart"))) {
			iDisplayStart = request.getParameter("iDisplayStart");
		}

		if (StringUtils.isNotBlank(request.getParameter("iDisplayLength"))) {
			iDisplayLength = request.getParameter("iDisplayLength");
		}

		String search = request.getParameter("sSearch");

		PageView<Module> pageView = new PageView<Module>(iDisplayStart,
				iDisplayLength);
		ModuleExample example = new ModuleExample();
		example.setPageView(pageView);
		if (StringUtils.isNotBlank(search)) {
			example.createCriteria().andNameLike("%" + search + "%");
		}
		return this.moduleService.queryObjByPage(example);
	}

	/**
	 * 根据父级ID查询模块
	 * 
	 * @param parentId
	 * @return
	 */
	@ApiOperation(value = "根据父级ID查询模块", httpMethod = "GET")
	@RequestMapping("front/findModuleByParentId")
	@ResponseBody
	public JsonResult<List<Module>> findModuleByParentId(Integer parentId,
			HttpServletRequest request) {
		ModuleExample example = new ModuleExample();
		example.createCriteria()
				.andStoreIdEqualTo(StoreUtils.getStoreIdFromThreadLocal())
				.andParentIdEqualTo(parentId);
		List<Module> list = moduleService.queryAllObjByExample(example);
		JsonResult<List<Module>> result = new JsonResult<List<Module>>();
		result.setData(list);
		result.setMsg("ok");
		result.setStatus("00");
		return result;

	}
	
	/**
	 * 加载模块信息
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param pramType
	 * @param pramValue
	 * @return
	 */
	@ApiOperation(value = "加载模块信息", httpMethod = "GET", notes = "paramType参数类型(articleId--文章,labels--多个标签名称使用逗号分隔,moduleId--文章模块,typeId--文章类型),paramValue参数值")
	@RequestMapping("front/queryModulePage")
	@ResponseBody
	public PageView<Module> queryModulePage(HttpServletRequest request) {
		return moduleService.selectByPage(StoreUtils.getStoreIdFromThreadLocal());
	}

}