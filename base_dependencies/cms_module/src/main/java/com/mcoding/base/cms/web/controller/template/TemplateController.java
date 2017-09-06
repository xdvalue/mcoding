package com.mcoding.base.cms.web.controller.template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.auth.utils.SpringSecurityUtils;
import com.mcoding.base.cms.bean.template.Template;
import com.mcoding.base.cms.bean.template.TemplateExample;
import com.mcoding.base.cms.service.template.TemplateService;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * TemplateController
 * 
 * @author acer
 * 
 */
@Api("模板管理")
@Controller
@RequestMapping("template")
public class TemplateController {
	@Autowired
	private TemplateService templateService;

	@ApiOperation(value = "模板页面跳转", httpMethod = "GET")
	@RequestMapping("service/init")
	public ModelAndView init() {
		ModelAndView mv = new ModelAndView("cms/template/main");
		return mv;
	}

	/**
	 * 页面跳转
	 * 
	 * @param templateId
	 * @param actionType
	 *            (A:add,U:update,D:detail)
	 * @return
	 */
	@RequestMapping(value = "service/action", params = "actionType")
	public ModelAndView action(Integer templateId, String actionType,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("cms/template/add");
		mv.addObject("actionType", actionType);
		if (StringUtils.isNotBlank(actionType)) {
			if ("U".equalsIgnoreCase(actionType)
					|| "D".equalsIgnoreCase(actionType)) {
				Template template = this.templateService
						.queryObjById(templateId);
				mv.addObject("template", template);
			}
		}
		return mv;
	}

	@ApiOperation(value = "新增模板", httpMethod = "POST")
	@RequestMapping("service/create")
	@ResponseBody
	public JsonResult<String> create(@RequestBody Template template) {
		this.templateService.addObj(template);
		template.setOperater(SpringSecurityUtils.getLoginUserId());
		JsonResult<String> result = new JsonResult<String>();
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(value = "修改模板", httpMethod = "POST")
	@RequestMapping("service/edit")
	@ResponseBody
	public JsonResult<String> edit(@RequestBody Template template) {
		this.templateService.modifyObj(template);
		JsonResult<String> result = new JsonResult<String>();
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(value = "删除模板", httpMethod = "POST")
	@RequestMapping("service/delete")
	@ResponseBody
	public JsonResult<String> delete(int templateId) {
		JsonResult<String> result = new JsonResult<String>();
		this.templateService.deleteObjById(templateId);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(value = "根据模板ID查询模板", httpMethod = "GET")
	@RequestMapping(value = { "front/findByTemplateId",
			"service/findByTemplateId" })
	@ResponseBody
	public JsonResult<Template> findByTemplateId(int templateId) {
		JsonResult<Template> result = new JsonResult<Template>();
		Template template = this.templateService.queryObjById(templateId);
		result.setData(template);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(value = "分页查询模板", httpMethod = "GET")
	@RequestMapping("service/findByPage")
	@ResponseBody
	public PageView<Template> findByPage(HttpServletRequest request,
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

		PageView<Template> pageView = new PageView<Template>(iDisplayStart,
				iDisplayLength);
		TemplateExample example = new TemplateExample();
		example.setPageView(pageView);
		if (StringUtils.isNotBlank(search)) {
			example.createCriteria().andTemplateNameLike("%" + search + "%");
		}
		return this.templateService.queryObjByPage(example);
	}
}