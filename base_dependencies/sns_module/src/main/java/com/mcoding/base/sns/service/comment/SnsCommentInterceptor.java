package com.mcoding.base.sns.service.comment;

import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.sns.bean.comment.SnsComment;
import com.mcoding.base.sns.bean.post.SnsPost;

public interface SnsCommentInterceptor {

	public boolean preHandle(SnsPost post, SnsComment comment, Member member);

	public void afterCompletion(SnsPost post, SnsComment comment, Member member);

}
