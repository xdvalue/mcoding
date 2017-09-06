package com.mcoding.base.member.utils.member;

import com.mcoding.base.member.bean.member.Member;
import com.mcoding.comp.wechat.member.bean.WxMember;

public class MemberUtils {
	
//	private static final Pattern emoji = Pattern.compile(
//			"[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
//			Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
	
	/**
	 * 把微信会员的信息，设置到 会员信息
	 * @return
	 */
	public static void setWxMemberInfoToMember(WxMember source, Member target){
		target.setName(source.getWxNickname());
		target.setHeadImgUrl(source.getWxHeadimgurl());
		target.setGender(source.getWxSex());
		target.setProvince(source.getWxProvince());
		target.setCity(source.getWxCity());
		target.setWxMember(source);
	}

}
