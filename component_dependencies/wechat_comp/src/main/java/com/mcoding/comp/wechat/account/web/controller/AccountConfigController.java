package com.mcoding.comp.wechat.account.web.controller;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.bean.AccountConfigExample;
import com.mcoding.comp.wechat.account.service.AccountConfigService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "微信账号表")
@Controller
@RequestMapping("accountConfig")
public class AccountConfigController {
	@Resource
	protected AccountConfigService accountConfigService;

	@ApiOperation(httpMethod = "POST", value = "创建微信账号表")
	@RequestMapping("service/create")
	@ResponseBody
	public JsonResult<String> create(@RequestBody AccountConfig accountConfig) {
		JsonResult<String> result = new JsonResult<>();
		this.accountConfigService.addObj(accountConfig);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(httpMethod = "POST", value = "编辑微信账号表")
	@RequestMapping("service/edit")
	@ResponseBody
	public JsonResult<String> edit(@RequestBody AccountConfig accountConfig) {
		if (accountConfig.getId() == null || accountConfig.getId() <= 0) {
			throw new CommonException("id 为空，保存失败");
		}
		JsonResult<String> result = new JsonResult<>();
		this.accountConfigService.modifyObj(accountConfig);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(httpMethod = "POST", value = "删除微信账号表")
	@RequestMapping("service/deleteById")
	@ResponseBody
	public JsonResult<String> deleteById(int id) {
		JsonResult<String> result = new JsonResult<>();
		this.accountConfigService.deleteObjById(id);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(httpMethod = "GET", value = "查询微信账号表")
	@RequestMapping("service/findByPage")
	@ResponseBody
	public PageView<AccountConfig> findByPage(
			@ApiParam(name = "分页索引", defaultValue = "0") @RequestParam(defaultValue = "0") String iDisplayStart,
			@ApiParam(name = "每页的数量", defaultValue = "10") @RequestParam(defaultValue = "10") String iDisplayLength,
			@ApiParam(value = "查询条件") String sSearch) {
		PageView<AccountConfig> pageView = new PageView<>(iDisplayStart, iDisplayLength);
		AccountConfigExample example = new AccountConfigExample();
		example.setPageView(pageView);
		if (StringUtils.isNotBlank(sSearch)) {
			// TODO Auto-generated method stub
		}
		return this.accountConfigService.queryObjByPage(example);
	}
}