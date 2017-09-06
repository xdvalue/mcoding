package com.mcoding.comp.wechat.oauth.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * H5页面获取 微信用户资料的处理
 * @author hzy
 *
 */
public interface WechatOauthService {
	
	/**
	 * H5页面获取 微信openid
	 * @param openId 微信openid
	 * @param paramsMap 原请求的参数
	 * @param request 原请求
	 * @throws Exception 
	 */
	public void handleForOpenId(String openId, Map<String, String> paramsMap, HttpServletRequest request);
	
	/**
	 * H5 页面， 获取微信用户信息
	 * @param wxMpUser 微信用户信息
	 * @param paramsMap 原请求的参数
	 * @param request 原请求
	 */
	public void handleForWxUser(WxMpUser wxMpUser, Map<String, String> paramsMap, HttpServletRequest request);

}
