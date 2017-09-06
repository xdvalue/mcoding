package com.mcoding.comp.wechat.jssdk.web.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.service.AccountConfigService;
import com.mcoding.comp.wechat.account.utils.WxMpServiceUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.chanjar.weixin.common.bean.WxCardApiSignature;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

@Api("微信公众号JS_SDK接口")
@Controller
@RequestMapping("wechatJsSdk")
public class WechatJsSdkController {

	@Resource
	protected AccountConfigService accountConfigService;
	
	@ApiOperation(value="获取js sdk的配置信息", httpMethod="GET")
	@RequestMapping(value="front/getJsConfigParams")
	@ResponseBody
	public JsonResult<Map<String, String>> getJsConfigParams(
			HttpServletRequest request,
			@ApiParam("公众号配置的originid") String originId,
			@ApiParam("当前网页的URL，不包含#及其后面部分") @RequestParam(required=true) String url) throws WxErrorException{
		
		String serverName = StringUtils.substringBetween(url, "://", "/");
		String requestUri = url.replaceAll("https?://.+?/", "/");
		AccountConfig accountConfig = this.accountConfigService.queryByRequest("http", serverName, 80, requestUri);
		
//		AccountConfig accountConfig = this.getAccountConfig(request);
		WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(accountConfig);
		
		String jsapiTicket = wxMpService.getJsapiTicket();
		WxJsapiSignature jsSign = wxMpService.createJsapiSignature(url);
		
		Map<String, String> jsapiParams = new Hashtable<>();
		
		jsapiParams.put("appId", accountConfig.getAppId());
		jsapiParams.put("timestamp", String.valueOf(jsSign.getTimestamp()));
		jsapiParams.put("nonceStr", jsSign.getNonceStr());
		jsapiParams.put("signature", jsSign.getSignature());
		jsapiParams.put("ticket", jsapiTicket);
		
		JsonResult<Map<String, String>> result = new JsonResult<>();
		result.setStatus("00");
        result.setMsg("ok");
        result.setData(jsapiParams);
		
		return result;
	}
	
	@ApiOperation(value="拉取适用卡券列表，获取前端配置参数", httpMethod="GET")
	@RequestMapping("front/getChooseCardParam")
	@ResponseBody
	public JsonResult<WxCardApiSignature> getChooseCardParam(HttpServletRequest request,
			@ApiParam("公众号配置的originid,非必填")String originId, 
			@ApiParam("门店ID，非必填") String shopId, 
		    @ApiParam("卡券类型，非必填") String cardType, 
		    @ApiParam("卡券ID,非必填") String cardId) throws NoSuchAlgorithmException, WxErrorException{
		
		AccountConfig accountConfig = this.getAccountConfig(request);
		WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(accountConfig);
		
		List<String> paramList = new ArrayList<>();
		if (StringUtils.isNotBlank(shopId)) paramList.add(shopId);
		if (StringUtils.isNotBlank(cardId)) paramList.add(cardId);
		if (StringUtils.isNotBlank(cardType)) paramList.add(cardType);
		String[] args = (String[]) paramList.toArray();
		WxCardApiSignature cardSignatrue = wxMpService.getCardService().createCardApiSignature(args);
		
		JsonResult<WxCardApiSignature> result = new JsonResult<>();
		result.setData(cardSignatrue);
		result.setMsg("ok");
		result.setStatus("00");
		
		return result;
	}
	
	private AccountConfig getAccountConfig(HttpServletRequest request){
		String originId = request.getParameter("originId");
		
		AccountConfig accountConfig = null;
		if (StringUtils.isBlank(originId)) {
			accountConfig = accountConfigService.queryByRequest(request);
		}else{
			accountConfig = accountConfigService.queryByOriginId(originId);
		}
		
		if (accountConfig == null) {
			throw new CommonException("未匹配到合适的公众号配置");
		}
		
		return accountConfig;
	}
	
	
}
