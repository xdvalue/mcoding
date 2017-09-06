package com.mcoding.base.member.service.wechat;

import com.mcoding.base.member.bean.member.Member;

public interface WechatLoginInterceptor {

	public boolean preHandle(String openId);

	public void afterCompletion(Member member);

}
