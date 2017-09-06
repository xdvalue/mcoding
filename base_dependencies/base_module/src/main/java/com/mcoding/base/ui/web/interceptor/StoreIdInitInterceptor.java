package com.mcoding.base.ui.web.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.service.store.StoreService;
import com.mcoding.base.ui.utils.StoreUtils;

public class StoreIdInitInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(StoreIdInitInterceptor.class);
	
	@Resource
	protected StoreService storeService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Integer storeId = (Integer) request.getSession().getAttribute("storeId");
		Store store = (Store) request.getSession().getAttribute("store");
		
		String storeName = store == null ? null :store.getStoreName();
		logger.debug("storeId inSession ["+storeId+"], storeName :["+storeName+"],sessionid" +request.getSession().getId());
		if (storeId != null) {
			if (store == null) {
				store = this.storeService.queryObjById(storeId);
			}
			StoreUtils.setInThreadLocal(store);
			return super.preHandle(request, response, handler);
		}
		
		logger.debug("storeId 为空，根据域名检查store");
		try {
			String schemem = request.getScheme();
			String serverName = request.getServerName();
			String requestUri = request.getRequestURI();
			/*
			int serverPort = request.getServerPort();
			store = this.storeService.queryStoreByRequest(schemem, serverName, serverPort, requestUri);
			*/
			store = this.storeService.queryStoreByRequest(schemem, serverName, requestUri);
		} catch (Exception e) {
			logger.error("storeId 检查异常：" + e.getMessage());
		}
		
		
		if(store != null){
			request.getSession().setAttribute("storeId", store.getId());
			request.getSession().setAttribute("store", store);
			StoreUtils.setInThreadLocal(store);
		}
		
		return super.preHandle(request, response, handler);
	}
	
}
