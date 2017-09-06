package com.mcoding.comp.wechat.msg.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.utils.http.HttpRequestUtils;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.service.AccountConfigService;
import com.mcoding.comp.wechat.account.utils.WxMpServiceUtils;
import com.mcoding.comp.wechat.msg.service.WxMsgRuleService;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@Controller
@RequestMapping("wechatMsg")
public class WechatMsgHandleController {
	
	protected static Logger logger = LoggerFactory.getLogger(WechatMsgHandleController.class);
	
	@Resource
	protected AccountConfigService accountConfigService;
	
	@Resource
	protected WxMsgRuleService wxMsgRuleService;
	
	/**
	 * 接收 微信消息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="front/{originId}", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String wechatApi(@PathVariable(value="originId") String originId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (StringUtils.isBlank(originId)) {
			return "wechatCode can not be null";
		}
		
		AccountConfig account = this.accountConfigService.queryByOriginId(originId);
		if (account == null) {
			throw new CommonException("未匹配到合适的公众号配置");
		}
		
//		if (!this.checkSignature(request, response, account)) {  // 消息签名不正确，说明不是公众平台发过来的消息
//			return "非法请求";
//		}
		
		String echostr = request.getParameter("echostr"); // 随机字符串
		String timestamp = request.getParameter("timestamp"); // 时间戳
		String nonce = request.getParameter("nonce"); // 随机数
		
		if (StringUtils.isNotBlank(echostr)) {
			// 说明是一个仅仅用来验证的请求，回显echostr
			return echostr;
		}
		
		String body = HttpRequestUtils.getRequestBody(request);
		logger.debug("input msg:" + body);
		
		String encryptType = StringUtils.isBlank(request
				.getParameter("encrypt_type")) ? "raw" : request
				.getParameter("encrypt_type");
		
		if ("raw".equals(encryptType)) {
			// 明文传输的消息
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(body);
			WxMpXmlOutMessage outMessage = this.wxMsgRuleService.createRouter(account).route(inMessage);
			
			if(outMessage == null){
				return "";
			}else{
				return outMessage.toXml();
			}
			
		}else if ("aes".equals(encryptType)) {
			// 是aes加密的消息
			String msgSignature = request.getParameter("msg_signature");
			
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(body, account, 
					timestamp, nonce, msgSignature);
			WxMpXmlOutMessage outMessage = this.wxMsgRuleService.createRouter(account).route(inMessage);
			
			if(outMessage == null){
				return "";
			}else{
				return outMessage.toEncryptedXml(account);
			}
			
		}else{
			return "不可识别的加密类型";
		}
	}
	
	/**
	 * 通过检验signature对请求进行校验
	 * @param request
	 * @param response
	 * @return
	 */
	private boolean checkSignature(HttpServletRequest request,
			HttpServletResponse response, AccountConfig account){
		String signature = request.getParameter("signature"); // 微信加密签名
		String timestamp = request.getParameter("timestamp"); // 时间戳
		String nonce = request.getParameter("nonce"); // 随机数
		
		WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(account);
		return wxMpService.checkSignature(timestamp, nonce, signature);
	}
	
	/**
	 * 查出请求的body
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	/*private String getRequestBody(HttpServletRequest request) throws IOException{
		request.setCharacterEncoding("UTF-8");
		
		StringBuffer bodyBuffer = new StringBuffer();
		ServletInputStream in = request.getInputStream();
		
		byte[] tmp = new byte[1024];
		while (in.read(tmp) != -1) {
			bodyBuffer.append(new String(tmp, "utf-8"));
		}
		
		return bodyBuffer.toString();
	}*/
	
}
