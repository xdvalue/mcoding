package com.mcoding.comp.wechat.msg.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.Constant;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.comp.wechat.msg.bean.WxMsgAutoReply;
import com.mcoding.comp.wechat.msg.bean.WxMsgAutoReplyExample;
import com.mcoding.comp.wechat.msg.service.WxMsgAutoReplyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value="自动回复的消息规则")
@Controller
@RequestMapping("wxMsgAutoReply")
public class WxMsgAutoReplyController {
    @Resource
    protected WxMsgAutoReplyService wxMsgAutoReplyService;

    @ApiOperation(httpMethod="POST", value="创建自动回复的消息规则")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody WxMsgAutoReply wxMsgAutoReply) {
    	String originId = wxMsgAutoReply.getWxAccountOriginId();
    	
    	WxMsgAutoReplyExample example = new WxMsgAutoReplyExample();
    	example.createCriteria()
    	       .andWxAccountOriginIdEqualTo(originId)
    	       .andIsDefaultEqualTo(Constant.YES_INT);
    	List<WxMsgAutoReply> liset = this.wxMsgAutoReplyService.queryAllObjByExample(example);
    	if (CollectionUtils.isEmpty(liset)) {
			wxMsgAutoReply.setIsDefault(Constant.YES_INT);
		}
    	
        JsonResult<String> result = new JsonResult<>();
        this.wxMsgAutoReplyService.addObj(wxMsgAutoReply);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑自动回复的消息规则")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody WxMsgAutoReply wxMsgAutoReply) {
        if (wxMsgAutoReply.getId() == null || wxMsgAutoReply.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.wxMsgAutoReplyService.modifyObj(wxMsgAutoReply);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="删除自动回复的消息规则")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.wxMsgAutoReplyService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }
    
    @ApiOperation(httpMethod="GET", value="设置默认的自动回复内容")
    @RequestMapping("service/setDefaultById")
    @ResponseBody
    public JsonResult<String> setDefaultById(
    		@RequestParam(required=true, name="originId") String originId,
    		int id) {
    	JsonResult<String> result = new JsonResult<>();
//    	this.wxMsgAutoReplyService.deleteObjById(id);
//    	WxMsgAutoReply autoReply = new WxMsgAutoReply();
//    	autoReply.setId(id);
//    	autoReply.setIsDefault(Constant.YES_INT);
//    	this.wxMsgAutoReplyService.modifyObj(autoReply);
    	this.wxMsgAutoReplyService.setDefalutById(originId, id);
    	
    	result.setData(null);
    	result.setMsg("ok");
    	result.setStatus("00");
    	return result;
    }

    @ApiOperation(httpMethod="GET", value="查询自动回复的消息规则")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<WxMsgAutoReply> findByPage(
    		@ApiParam(value="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, 
    		@ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, 
    		@ApiParam(value="查询条件") @RequestParam(required=true,name="originId") String originId,
    		@ApiParam(value="查询条件") String sSearch) {
        PageView<WxMsgAutoReply> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        WxMsgAutoReplyExample example = new WxMsgAutoReplyExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        example.createCriteria().andWxAccountOriginIdEqualTo(originId);
        return this.wxMsgAutoReplyService.queryObjByPage(example);
    }
}