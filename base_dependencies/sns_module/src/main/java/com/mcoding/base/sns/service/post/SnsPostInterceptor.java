package com.mcoding.base.sns.service.post;


import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.sns.bean.post.SnsPost;

public interface SnsPostInterceptor {
	
	public boolean preHandle(SnsPost snsPost, Member member);
	
	public void afterCompletion(SnsPost snsPost, Member member);

}
