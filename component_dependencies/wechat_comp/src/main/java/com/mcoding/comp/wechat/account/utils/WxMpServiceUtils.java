package com.mcoding.comp.wechat.account.utils;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.mcoding.base.ui.utils.spring.SpringContextHolder;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.service.AccountConfigService;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

public class WxMpServiceUtils {
	
	public static final ThreadLocal<WxMpService> wxMpServiceThreadLocal = new ThreadLocal<>();
	public static Map<String, WxMpService> wxMpServiceMap = new Hashtable<>();
	
	public static void setInThreadLocal(WxMpService wxMpService){
		wxMpServiceThreadLocal.set(wxMpService);
	}
	
	public static WxMpService getWxMpServiceFromThreadLocal(){
		return wxMpServiceThreadLocal.get();
	}
	/*
	public static WxMpService getWxMpServiceByAccount(AccountConfig accountConfig){
		return WxMpServicePool.getWxMpServiceByAccount(accountConfig);
	}*/
	
	public static WxMpService getWxMpServiceByRequest(HttpServletRequest request) {
		AccountConfigService accountConfigService = SpringContextHolder.getOneBean(AccountConfigService.class);
		AccountConfig accountConfig = accountConfigService.queryByRequest(request);
		return getWxMpServiceByAccount(accountConfig);
	}

	public static WxMpService getWxMpServiceByAccount(AccountConfig accountConfig) {
		if (accountConfig == null) {
			return null;
		}
		
		String appId = accountConfig.getAppId();
		if (StringUtils.isBlank(appId)) {
			throw new NullPointerException("公众号配置中，appid不能为空");
		}
		
		if (wxMpServiceMap.get(appId) != null) {
			return wxMpServiceMap.get(appId);
		}
		
		synchronized (wxMpServiceMap) {
			if (wxMpServiceMap.get(appId) == null) {
				WxMpService service = new WxMpServiceImpl();
				service.setWxMpConfigStorage(accountConfig);
				wxMpServiceMap.put(appId, service);
			}
		}
		
		return wxMpServiceMap.get(appId);
//		WxMpService service = new McodingWxMpServiceImpl(accountConfig);
//		WxMpService service = new WxMpServiceImpl();
//		service.setWxMpConfigStorage(accountConfig);
//		return service;
	}


}
