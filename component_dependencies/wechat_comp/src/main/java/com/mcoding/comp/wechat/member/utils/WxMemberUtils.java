package com.mcoding.comp.wechat.member.utils;

import java.util.Date;

import com.mcoding.base.ui.utils.common.Constant;
import com.mcoding.comp.wechat.member.bean.WxMember;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

public class WxMemberUtils {
	
	public static WxMember getWxMemberFormMpUser(WxMpUser wxUser){
		WxMember wxMember = new WxMember();
		if (wxUser == null) {
			return null;
		}

		wxMember.setWxCity(wxUser.getCity());
		wxMember.setWxCountry(wxUser.getCountry());
		wxMember.setWxHeadimgurl(wxUser.getHeadImgUrl());
		wxMember.setWxLanguage(wxUser.getLanguage());
		wxMember.setWxOpenid(wxUser.getOpenId());
		wxMember.setWxProvince(wxUser.getProvince());
		wxMember.setWxUnionid(wxUser.getUnionId());
		wxMember.setWxSex(wxUser.getSexId());
		
		if (wxUser.getSubscribe() != null && wxUser.getSubscribe()) {
			wxMember.setWxSubscribe(Constant.YES_INT);
		} else {
			wxMember.setWxSubscribe(Constant.NO_INT);
		}

		if (wxUser.getSubscribeTime() != null) {
			wxMember.setWxSubscribeTime(new Date(wxUser.getSubscribeTime() * 1000));
		}

		String nickname = wxUser.getNickname();
		wxMember.setWxNickname(nickname);
		
		return wxMember;
	}

}
