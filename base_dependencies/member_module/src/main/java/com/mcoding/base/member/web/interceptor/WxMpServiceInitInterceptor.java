package com.mcoding.base.member.web.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.service.AccountConfigService;
import com.mcoding.comp.wechat.account.utils.WxMpServiceUtils;

import me.chanjar.weixin.mp.api.WxMpService;

public class WxMpServiceInitInterceptor extends HandlerInterceptorAdapter{

	private static Logger logger = LoggerFactory.getLogger(WxMpServiceInitInterceptor.class);
	
	@Resource
	protected AccountConfigService accountConfigService;
	
	@Resource
	protected StoreWxRefService storeWxRefService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		AccountConfig wechatAccountConfig = (AccountConfig) request.getSession().getAttribute("accountConfig");
		if(wechatAccountConfig != null){
			WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(wechatAccountConfig);
			
			WxMpServiceUtils.setInThreadLocal(wxMpService);
			
			return super.preHandle(request, response, handler);
		}
//		Store
//		AccountConfig accountConfig = accountConfigService.queryByRequest(request);
		AccountConfig accountConfig = this.storeWxRefService.queryWxAccountByStoreId(StoreUtils.getStoreIdFromThreadLocal());
		if (accountConfig == null) {
			logger.warn("domain:" + request.getServerName() + ", match no wechat account");
			return super.preHandle(request, response, handler);
		}
		
		request.getSession().setAttribute("accountConfig", accountConfig);
		
		logger.debug("domain:" + request.getServerName() + ", match wechat account :" + accountConfig.getName());
		
		WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(accountConfig);
		WxMpServiceUtils.setInThreadLocal(wxMpService);
		
		return super.preHandle(request, response, handler);
	} 
	
}
