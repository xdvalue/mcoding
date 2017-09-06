package com.mcoding.base.sns.web.controller.banner;

import java.util.List;

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
import com.mcoding.base.sns.bean.banner.SnsBanner;
import com.mcoding.base.sns.bean.banner.SnsBannerExample;
import com.mcoding.base.sns.service.banner.SnsBannerService;
import com.mcoding.base.ui.utils.common.Constant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "轮播图")
@Controller
@RequestMapping("snsBanner")
public class SnsBannerController {
	@Resource
	protected SnsBannerService snsBannerService;

	@ApiIgnore
	@RequestMapping("service/toAddView")
	public ModelAndView toAddView() {
		return new ModelAndView("sns/snsBanner/toAddView");
	}

	@ApiIgnore
	@RequestMapping("service/toMainView")
	public ModelAndView toMainView() {
		return new ModelAndView("sns/snsBanner/toMainView");
	}

	@ApiIgnore
	@RequestMapping("service/toUpdateViewById")
	public ModelAndView toUpdateViewById(int id) {
		ModelAndView view = new ModelAndView();
		SnsBanner snsBanner = this.snsBannerService.queryObjById(id);
		view.addObject("snsBanner", snsBanner);
		view.setViewName("sns/snsBanner/toAddView");
		return view;
	}

	@ApiOperation(httpMethod = "POST", value = "创建轮播图")
	@RequestMapping("service/create")
	@ResponseBody
	public JsonResult<String> create(@RequestBody SnsBanner snsBanner) {
		JsonResult<String> result = new JsonResult<>();
		this.snsBannerService.addObj(snsBanner);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(httpMethod = "POST", value = "编辑轮播图")
	@RequestMapping("service/edit")
	@ResponseBody
	public JsonResult<String> edit(@RequestBody SnsBanner snsBanner) {
		if (snsBanner.getId() == null || snsBanner.getId() <= 0) {
			throw new CommonException("id 为空，保存失败");
		}

		JsonResult<String> result = new JsonResult<>();
		this.snsBannerService.modifyObj(snsBanner);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(httpMethod = "POST", value = "删除轮播图")
	@RequestMapping("service/deleteById")
	@ResponseBody
	public JsonResult<String> deleteById(int id) {
		JsonResult<String> result = new JsonResult<>();
		this.snsBannerService.deleteObjById(id);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(httpMethod = "GET", value = "启用或禁用模块")
	@RequestMapping("service/setIsEnableById")
	@ResponseBody
	public JsonResult<String> setIsEnableById(int id, int isEnable) {
		JsonResult<String> result = new JsonResult<>();
		// this.snsModuleService.deleteObjById(id);
		SnsBanner tmp = new SnsBanner();
		tmp.setId(id);
		if (Constant.YES_INT.equals(isEnable)) {
			tmp.setIsEnable(Constant.YES_INT);
		} else {
			tmp.setIsEnable(Constant.NO_INT);
		}
		this.snsBannerService.modifyObj(tmp);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(httpMethod = "GET", value = "查询轮播图")
	@RequestMapping("service/findByPage")
	@ResponseBody
	public PageView<SnsBanner> findByPage(
			@ApiParam(name = "分页索引", defaultValue = "0") @RequestParam(defaultValue = "0") String iDisplayStart,
			@ApiParam(name = "每页的数量", defaultValue = "10") @RequestParam(defaultValue = "10") String iDisplayLength,
			@ApiParam(value = "查询条件") String sSearch) {
		PageView<SnsBanner> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		SnsBannerExample example = new SnsBannerExample();
		example.setPageView(pageView);
		if (StringUtils.isNotBlank(sSearch)) {
			example.createCriteria().andTitleLike(sSearch + "%");
			example.or().andImgAltLike(sSearch + "%");
		}
		return this.snsBannerService.queryObjByPage(example);
	}

	@ApiOperation(httpMethod = "GET", value = "查询所有轮播图")
	@RequestMapping("front/findAll")
	@ResponseBody
	public JsonResult<List<SnsBanner>> findAll() {
		SnsBannerExample example = new SnsBannerExample();
		example.createCriteria().andIsEnableEqualTo(Constant.YES_INT);
		
		example.setOrderByClause("sort desc");

		JsonResult<List<SnsBanner>> result = new JsonResult<>();
		result.setData(this.snsBannerService.queryAllObjByExample(example));
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}
}