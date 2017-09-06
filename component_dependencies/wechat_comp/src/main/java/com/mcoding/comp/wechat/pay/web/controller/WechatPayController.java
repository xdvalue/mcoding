package com.mcoding.comp.wechat.pay.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;
import com.mcoding.base.utils.http.HttpRequestUtils;
import com.mcoding.comp.wechat.common.WxConstant;
import com.mcoding.comp.wechat.common.utils.XmlUtils;
import com.mcoding.comp.wechat.pay.bean.WxMpXmlInOrderQueryMessage;
import com.mcoding.comp.wechat.pay.bean.WxMpXmlUnifiedOrderInMessage;
import com.mcoding.comp.wechat.pay.service.WechatPayNotifyService;

/**
 * 微信支付接口
 * @author hzy
 *
 */
@Controller("wechatPayController")
public class WechatPayController {
	
	private Logger logger = LoggerFactory.getLogger(WechatPayController.class);
	
	@RequestMapping(WxConstant.URL_PAID_NOTIFY)
	@ResponseBody
	public String payResultNotify(HttpServletRequest request) throws IOException{
		WechatPayNotifyService wechatPayNotifyService = SpringContextHolder.getOneBean(WechatPayNotifyService.class);
		
		JsonResult<String> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		
		String requestBody = HttpRequestUtils.getRequestBody(request);
		logger.info("receive PayResult notify:" +requestBody);
		
		WxMpXmlInOrderQueryMessage inMsg = XmlUtils.parseXMl(requestBody, WxMpXmlInOrderQueryMessage.class);
		if (wechatPayNotifyService != null) {
			wechatPayNotifyService.handlePaidNotifyMsg(inMsg);
		}
		
		WxMpXmlUnifiedOrderInMessage resultMsg = new WxMpXmlUnifiedOrderInMessage();
		resultMsg.setReturnCode("SUCCESS");
		resultMsg.setResultCode("OK");
		return resultMsg.toXml();
	}

}
