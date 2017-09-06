package com.mcoding.base.wechat.web.controller.msgrule;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.comp.wechat.account.service.AccountConfigService;
import com.mcoding.comp.wechat.msg.bean.WxMsgAutoReply;
import com.mcoding.comp.wechat.msg.service.WxMsgAutoReplyService;

import io.swagger.annotations.Api;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="自动回复的消息规则")
@Controller("WechatMsgAutoReplyController")
@RequestMapping("wxMsgAutoReply")
public class WxMsgAutoReplyController {
    @Resource
    protected WxMsgAutoReplyService wxMsgAutoReplyService;

    @Resource
    protected AccountConfigService accountConfigService;
    
    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView(@RequestParam(required=true, value="originId") String originId) {
        
        ModelAndView view = new ModelAndView("wechat/wxMsgAutoReply/toAddView");
    	view.addObject("account", accountConfigService.queryByOriginId(originId));
        return view;
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView(@RequestParam(required=true, value="originId") String originId) {
    	ModelAndView view = new ModelAndView("wechat/wxMsgAutoReply/toMainView");
    	view.addObject("account", accountConfigService.queryByOriginId(originId));
        return view;
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        WxMsgAutoReply wxMsgAutoReply = this.wxMsgAutoReplyService.queryObjById(id);
        view.addObject("wxMsgAutoReply", wxMsgAutoReply);
        view.addObject("account", accountConfigService.queryByOriginId(wxMsgAutoReply.getWxAccountOriginId()));
        view.setViewName("wechat/wxMsgAutoReply/toAddView");
        return view;
    }

}