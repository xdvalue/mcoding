package com.mcoding.comp.wechat.msg.web.controller;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.comp.wechat.account.service.AccountConfigService;
import com.mcoding.comp.wechat.msg.bean.WxMsgReplyNews;
import com.mcoding.comp.wechat.msg.bean.WxMsgReplyNewsExample;
import com.mcoding.comp.wechat.msg.service.WxMsgReplyNewsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="自定义图文消息")
@Controller
@RequestMapping("wxMsgReplyNews")
public class WxMsgReplyNewsController {
    @Resource
    protected WxMsgReplyNewsService wxMsgReplyNewsService;
    
    @Autowired
    protected AccountConfigService accountConfigService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView(@RequestParam(required=true, name="originId") String originId) {
    	ModelAndView view = new ModelAndView("wechat/wxMsgReplyNews/toAddView");
    	view.addObject("account", accountConfigService.queryByOriginId(originId));
        return view;
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("wechat/wxMsgReplyNews/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id, @RequestParam(required=true, name="originId")String originId) {
        ModelAndView view = new ModelAndView();
        WxMsgReplyNews wxMsgReplyNews = this.wxMsgReplyNewsService.queryObjById(id);
        view.addObject("wxMsgReplyNews", wxMsgReplyNews);
        view.addObject("account", accountConfigService.queryByOriginId(originId));
        view.setViewName("wechat/wxMsgReplyNews/toAddView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建自定义图文消息")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody WxMsgReplyNews wxMsgReplyNews) {
        JsonResult<String> result = new JsonResult<>();
        this.wxMsgReplyNewsService.addObj(wxMsgReplyNews);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑自定义图文消息")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody WxMsgReplyNews wxMsgReplyNews) {
        if (wxMsgReplyNews.getId() == null || wxMsgReplyNews.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.wxMsgReplyNewsService.modifyObj(wxMsgReplyNews);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除自定义图文消息")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.wxMsgReplyNewsService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询自定义图文消息")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<WxMsgReplyNews> findByPage(@ApiParam(name="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(name="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<WxMsgReplyNews> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        WxMsgReplyNewsExample example = new WxMsgReplyNewsExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.wxMsgReplyNewsService.queryObjByPage(example);
    }
}