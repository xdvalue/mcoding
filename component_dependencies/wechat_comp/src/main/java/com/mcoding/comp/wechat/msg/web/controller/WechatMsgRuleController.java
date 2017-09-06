package com.mcoding.comp.wechat.msg.web.controller;

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
import com.mcoding.base.ui.utils.common.Constant;
import com.mcoding.comp.wechat.msg.bean.WxMsgRule;
import com.mcoding.comp.wechat.msg.bean.WxMsgRuleExample;
import com.mcoding.comp.wechat.msg.service.WxMsgRuleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value="微信消息规则")
@Controller
@RequestMapping("wechatMsgRule")
public class WechatMsgRuleController {
    @Resource
    protected WxMsgRuleService wxMsgRuleService;

    @ApiOperation(httpMethod="POST", value="创建微信消息规则")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody WxMsgRule wxMsgRule) {
        JsonResult<String> result = new JsonResult<>();
        this.wxMsgRuleService.addObj(wxMsgRule);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑微信消息规则")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody WxMsgRule wxMsgRule) {
        if (wxMsgRule.getId() == null || wxMsgRule.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.wxMsgRuleService.modifyObj(wxMsgRule);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除微信消息规则")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.wxMsgRuleService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询微信消息规则")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<WxMsgRule> findByPage(
    		@ApiParam(name="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, 
    		@ApiParam(name="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, 
    		@ApiParam(name="查询条件") String sSearch,
    		@ApiParam(name="公众号原始id") String originId) {
        PageView<WxMsgRule> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        WxMsgRuleExample example = new WxMsgRuleExample();
        example.setPageView(pageView);
        example.createCriteria().andWxAccountOriginIdEqualTo(originId);
        
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        
        example.setOrderByClause("priority DESC");
        return this.wxMsgRuleService.queryObjByPage(example);
    }
    
    @ApiOperation(httpMethod = "GET", value = "启用或禁用消息规则")
	@RequestMapping("service/setIsEnableById")
	@ResponseBody
	public JsonResult<String> setIsEnableById(int id, int isEnable) {
		JsonResult<String> result = new JsonResult<>();
		// this.snsModuleService.deleteObjById(id);
		WxMsgRule tmp = this.wxMsgRuleService.queryObjById(id);
		tmp.setId(id);
		if (Constant.YES_INT.equals(isEnable)) {
			tmp.setIsEnable(Constant.YES_INT);
		} else {
			tmp.setIsEnable(Constant.NO_INT);
		}
		
		this.wxMsgRuleService.modifyObj(tmp);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}
}