package com.mcoding.base.cms.web.controller.label;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.cms.bean.label.Label;
import com.mcoding.base.cms.bean.label.LabelExample;
import com.mcoding.base.cms.service.label.LabelService;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.ui.utils.StoreUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * LabelController
 * 
 * @author acer
 * 
 */
@Api("标签管理")
@Controller
@RequestMapping("label")
public class LabelController {

	@Autowired
	private LabelService labelService;

	@ApiOperation(value = "标签页面跳转", httpMethod = "GET")
	@RequestMapping("service/init")
	public ModelAndView init() {
		ModelAndView mv = new ModelAndView("cms/label/main");
		return mv;
	}

	/**
	 * 页面跳转
	 * 
	 * @param labelId
	 * @param actionType
	 *            (A:add,U:update,D:detail)
	 * @return
	 */
	@RequestMapping(value = "service/action", params = "actionType")
	public ModelAndView action(Integer labelId, String actionType,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("cms/label/add");
		mv.addObject("actionType", actionType);
		if (StringUtils.isNotBlank(actionType)) {
			if ("U".equalsIgnoreCase(actionType)
					|| "D".equalsIgnoreCase(actionType)) {
				Label label = this.labelService.queryObjById(labelId);
				mv.addObject("label", label);
			}
		}
		return mv;
	}

	@ApiOperation(value = "添加标签", httpMethod = "POST")
	@RequestMapping("service/create")
	@ResponseBody
	public JsonResult<String> create(@RequestBody Label label) {
		this.labelService.addObj(label);
		JsonResult<String> result = new JsonResult<String>();
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(value = "修改标签", httpMethod = "POST")
	@RequestMapping("service/edit")
	@ResponseBody
	public JsonResult<String> edit(@RequestBody Label label) {
		this.labelService.modifyObj(label);
		JsonResult<String> result = new JsonResult<String>();
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(value = "删除标签", httpMethod = "POST")
	@RequestMapping("service/delete")
	@ResponseBody
	public JsonResult<String> delete(int labelId) {
		JsonResult<String> result = new JsonResult<String>();
		this.labelService.deleteObjById(labelId);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(value = "根据标签ID查询标签", httpMethod = "GET")
	@RequestMapping(value = { "front/findByLabelId", "service/findByLabelId" })
	@ResponseBody
	public JsonResult<Label> findByLabelId(int labelId) {
		JsonResult<Label> result = new JsonResult<Label>();
		Label label = this.labelService.queryObjById(labelId);
		result.setData(label);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(value = "分页查询标签", httpMethod = "GET")
	@RequestMapping("service/findByPage")
	@ResponseBody
	public PageView<Label> findByPage(HttpServletRequest request) {
		String iDisplayStart = "0";// 起始索引
		String iDisplayLength = "10";// 每页显示的行数

		if (StringUtils.isNotBlank(request.getParameter("iDisplayStart"))) {
			iDisplayStart = request.getParameter("iDisplayStart");
		}

		if (StringUtils.isNotBlank(request.getParameter("iDisplayLength"))) {
			iDisplayLength = request.getParameter("iDisplayLength");
		}

		String search = request.getParameter("sSearch");

		PageView<Label> pageView = new PageView<Label>(iDisplayStart,
				iDisplayLength);

		LabelExample example = new LabelExample();
		example.setPageView(pageView);
		if (StringUtils.isNotBlank(search)) {
			example.createCriteria().andMarkLike(search + "%");
		}
		example.setOrderByClause("hit DESC, seq_num DESC");
		return this.labelService.queryObjByPage(example);
	}

	/**
	 * 更新标签热度
	 * 
	 * @param label
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "更新标签热度", httpMethod = "GET")
	@RequestMapping("front/updateHit")
	@ResponseBody
	public JsonResult<String> updateHit(String label, HttpServletRequest request) {
		labelService.addLabelHit(StoreUtils.getStoreIdFromThreadLocal(), label);
		JsonResult<String> result = new JsonResult<String>();
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	/**
	 * 查询标签热度接口
	 * 
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "查询标签热度接口", httpMethod = "GET")
	@RequestMapping("front/findLabelByPage")
	@ResponseBody
	public PageView<Label> findLabelByPage(String iDisplayStart,
			String iDisplayLength, HttpServletRequest request) {
		PageView<Label> pageView = new PageView<Label>(iDisplayStart,
				iDisplayLength);
		LabelExample example = new LabelExample();
		example.createCriteria().andStoreIdEqualTo(StoreUtils.getStoreIdFromThreadLocal());
		example.setOrderByClause(" hit desc ");
		example.setPageView(pageView);
		return labelService.queryObjByPage(example);
	}
}