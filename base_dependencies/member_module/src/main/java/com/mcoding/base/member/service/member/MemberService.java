package com.mcoding.base.member.service.member;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.bean.member.MemberExample;
import com.mcoding.comp.wechat.member.bean.WxMember;

public interface MemberService extends BaseService<Member, MemberExample> {
	
	/**
	 * 根据微信用户openId，增加或修改用户信息
	 * @param wxMpUser
	 * @return
	 */
	public Member createOrEditByOpenId(String openId);
	
	/**
	 * 根据微信用户资料，增加或修改用户信息
	 * @param wxMpUser
	 * @return
	 */
	public Member createOrEditByWxMember(WxMember wxMpUser);
	
	/**
	 * 只查询会员的信息，包括会员的拓展信息和微信会员信息
	 * @param id
	 * @return
	 */
	public Member queryMemberFromSingleTable(int id);
	
	/**
	 * 根据微信的会员openid，查询出会员信息
	 * @param openId
	 * @return
	 */
	public Member queryByOpenId(String openId);
	
}
