package com.mcoding.base.sns.service.post.interceptor;

import java.util.Date;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.sns.bean.post.SnsPost;
import com.mcoding.base.sns.bean.post.SnsPostExample;
import com.mcoding.base.sns.service.post.SnsPostInterceptor;
import com.mcoding.base.sns.service.post.SnsPostService;

@Component
public class NoRepeatPost implements SnsPostInterceptor{
	
	@Autowired
	protected SnsPostService snsPostService;

	@Override
	public boolean preHandle(SnsPost snsPost, Member member) {
		
		SnsPostExample snsPostExample = new SnsPostExample();
		snsPostExample.createCriteria()
		              .andTitleEqualTo(snsPost.getTitle())
		              .andMemeberIdEqualTo(member.getId())
		              .andCreateTimeLessThanOrEqualTo(DateUtils.addMinutes(new Date(), -30));
		
		if (CollectionUtils.isNotEmpty(this.snsPostService.queryAllObjByExample(snsPostExample))) {
			throw new CommonException("30分钟内不能发表同一标题的帖子");
		}
		return true;
	}

	@Override
	public void afterCompletion(SnsPost snsPost, Member member) {
		// TODO Auto-generated method stub
		
	}

}
