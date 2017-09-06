package com.mcoding.comp.wechat.redpack.service;

import com.mcoding.base.core.BaseService;
import com.mcoding.comp.wechat.redpack.bean.WxRedpack;
import com.mcoding.comp.wechat.redpack.bean.WxRedpackExample;

public interface WxRedpackService extends BaseService<WxRedpack, WxRedpackExample> {
	
	public WxRedpack queryByCode(String redpackCode);
}