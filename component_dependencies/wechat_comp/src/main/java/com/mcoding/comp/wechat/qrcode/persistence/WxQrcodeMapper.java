package com.mcoding.comp.wechat.qrcode.persistence;

import com.mcoding.base.core.IMapper;
import com.mcoding.comp.wechat.qrcode.bean.WxQrcode;
import com.mcoding.comp.wechat.qrcode.bean.WxQrcodeExample;

public interface WxQrcodeMapper extends IMapper<WxQrcode, WxQrcodeExample> {
	
	public void addScanCount(int id);
}