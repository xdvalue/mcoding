package com.mcoding.base.sns.web.controller.message;

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
import com.mcoding.base.sns.bean.message.SnsMsg;
import com.mcoding.base.sns.bean.message.SnsMsgExample;
import com.mcoding.base.sns.service.message.SnsMsgService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "消息")
@Controller
@RequestMapping("snsMsg")
public class SnsMsgController {
	@Resource
	protected SnsMsgService snsMsgService;

	@ApiIgnore
	@RequestMapping("service/toAddView")
	public ModelAndView toAddView() {
		return new ModelAndView("sns/snsMsg/toAddView");
	}

	@ApiIgnore
	@RequestMapping("service/toMainView")
	public ModelAndView toMainView() {
		return new ModelAndView("sns/snsMsg/toMainView");
	}

	@ApiIgnore
	@RequestMapping("service/toUpdateViewById")
	public ModelAndView toUpdateViewById(int id) {
		ModelAndView view = new ModelAndView();
		SnsMsg snsMsg = this.snsMsgService.queryObjById(id);
		view.addObject("snsMsg", snsMsg);
		view.setViewName("sns/snsMsg/toAddView");
		return view;
	}

	@ApiOperation(httpMethod = "POST", value = "创建消息")
	@RequestMapping("service/create")
	@ResponseBody
	public JsonResult<String> create(@RequestBody SnsMsg snsMsg) {
		JsonResult<String> result = new JsonResult<>();
		this.snsMsgService.addObj(snsMsg);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(httpMethod = "POST", value = "编辑消息")
	@RequestMapping("service/edit")
	@ResponseBody
	public JsonResult<String> edit(@RequestBody SnsMsg snsMsg) {
		if (snsMsg.getId() == null || snsMsg.getId() <= 0) {
			throw new CommonException("id 为空，保存失败");
		}
		JsonResult<String> result = new JsonResult<>();
		this.snsMsgService.modifyObj(snsMsg);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(httpMethod = "POST", value = "删除消息")
	@RequestMapping("service/deleteById")
	@ResponseBody
	public JsonResult<String> deleteById(int id) {
		JsonResult<String> result = new JsonResult<>();
		this.snsMsgService.deleteObjById(id);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(httpMethod = "GET", value = "查询消息")
	@RequestMapping("service/findByPage")
	@ResponseBody
	public PageView<SnsMsg> findByPage(
			@ApiParam(name = "分页索引", defaultValue = "0") @RequestParam(defaultValue = "0") String iDisplayStart,
			@ApiParam(name = "每页的数量", defaultValue = "10") @RequestParam(defaultValue = "10") String iDisplayLength,
			@ApiParam(value = "查询条件") String sSearch) {
		PageView<SnsMsg> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		SnsMsgExample example = new SnsMsgExample();
		SnsMsgExample.Criteria cri = example.createCriteria();
		cri.andTypeEqualTo(SnsMsg.MSG_TYPE_SYSTEM).andReceiverIdIsNull();
		example.setPageView(pageView);
		
		if (StringUtils.isNotBlank(sSearch)) {
			cri.andTitleLike(sSearch + "%");
		}
		return this.snsMsgService.queryObjByPage(example);
	}

	@ApiOperation(httpMethod = "GET", value = "启用或禁用消息")
	@RequestMapping("service/setIsEnableById")
	@ResponseBody
	public JsonResult<String> setIsEnableById(int id, int isEnable) {
		SnsMsg msg = new SnsMsg();
		msg.setId(id);
		msg.setIsEnable(isEnable);
		this.snsMsgService.modifyObj(msg);

		JsonResult<String> result = new JsonResult<>();
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

}