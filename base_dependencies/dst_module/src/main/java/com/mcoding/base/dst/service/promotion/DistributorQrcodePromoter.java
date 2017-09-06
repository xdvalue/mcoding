package com.mcoding.base.dst.service.promotion;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.mcoding.base.dst.bean.level.DstMemberLevel;
import com.mcoding.base.dst.service.level.DstMemberLevelService;
import com.mcoding.base.dst.utils.DstUtils;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.qrcode.bean.WxQrcode;
import com.mcoding.comp.wechat.qrcode.service.WxQrcodeService;

import me.chanjar.weixin.common.exception.WxErrorException;

@Component
public class DistributorQrcodePromoter {
	
	public static final String QRCODE_KEY_WORDS = "分销商推广二维码";
	
	@Resource
	protected WxQrcodeService wxQrcodeService;
	
	@Resource
	protected DstMemberLevelService dstMemberLevelService;
	
	/**
	 * 创建推广二维码
	 * @param distributor
	 * @param account
	 * @return
	 * @throws WxErrorException
	 * @throws IOException
	 */
	public WxQrcode createQrcode(Member distributor, AccountConfig account) throws WxErrorException, IOException{
		if (distributor == null || account == null) {
			throw new NullPointerException("经销商资料，或者公众号资料为空");
		}
		
		DstMemberLevel memberLevel = this.dstMemberLevelService.queryByMemberId(distributor.getId());
		if (memberLevel == null || memberLevel.getLevelId() == null) {
			throw new NullPointerException("经销商的级别资料为空");
		}
		
		String sceneStr = DstUtils.createQrcodeKey(account.getId(), distributor.getId());
		WxQrcode wxQrcode = wxQrcodeService.queryByKey(account.getOriginId(), sceneStr);
		if (wxQrcode != null) {
			return wxQrcode;
		}
		
		wxQrcode = new WxQrcode();
		wxQrcode.setAccountId(account.getId());
		wxQrcode.setAccountOriginId(account.getOriginId());
		wxQrcode.setName("经销商推广二维码["+ distributor.getId()+","+distributor.getName()+"]");
		wxQrcode.setType(WxQrcode.QRCODE_TYPE_UNLIMIT);
		wxQrcode.setSceneStr(sceneStr);
		wxQrcode.setReplyContent(QRCODE_KEY_WORDS);
		wxQrcode = this.wxQrcodeService.createUnLimitQrcode(wxQrcode);
		return wxQrcode;
	}
}
