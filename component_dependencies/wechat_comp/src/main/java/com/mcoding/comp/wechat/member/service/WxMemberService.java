package com.mcoding.comp.wechat.member.service;

import com.mcoding.base.core.BaseService;
import com.mcoding.comp.wechat.member.bean.WxMember;
import com.mcoding.comp.wechat.member.bean.WxMemberExample;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

public interface WxMemberService extends BaseService<WxMember, WxMemberExample>{
	
	/**
	 * 根据openid查询微信会员资料
	 * @param openid
	 * @return
	 */
	public WxMember queryByOpenId(String openid);
	
	/**
	 * 根据微信用户资料添加或者更新 微信会员信息
	 * @param wxMpUser
	 */
//	public WxMember createOrEditWxMember(WxMpUser wxMpUser);
	
	/**
	 * 根据微信用户资料添加或者更新 微信会员信息
	 * @param wxMpUser 关注公众号后，查询结果
	 * @param subscribeKey 关注公众号的，事件key
	 */
//	public WxMember createOrEditWxMember(WxMpUser wxMpUser, String subscribeKey);
	
	/**
	 * 当会员取消关注的时候，更新微信会员的关注状态
	 * @param openid
	 */
	public void updateForUnsubscribe(String openid);

	WxMember createOrEditWxMember(WxMpUser wxMpUser, String subscribeKey, String originId);

}
