package com.mcoding.comp.wechat.account.utils;

import com.mcoding.base.ui.utils.spring.SpringContextHolder;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.service.AccountConfigService;

public class WxAccountConfigUtils {
	
	private static AccountConfigService accountConfigService = SpringContextHolder.getOneBean(AccountConfigService.class);
	
	public static AccountConfig getByOrginId(String originId){
		return accountConfigService.queryByOriginId(originId);
	}
	
}
