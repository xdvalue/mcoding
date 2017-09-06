package com.mcoding.base.sns.service.rule;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.service.member.MemberService;
import com.mcoding.base.sns.bean.comment.SnsComment;
import com.mcoding.base.sns.bean.post.SnsPost;
import com.mcoding.base.sns.service.comment.SnsCommentInterceptor;
import com.mcoding.base.sns.service.post.SnsPostInterceptor;
import com.mcoding.base.ui.utils.common.Constant;

/***
 * 发表帖子，评论帖子的拦截器。<p/>
 * 在发表帖子之前，或评论之前，进行拦截，检查会员的资料是否已经完善，如果未完善抛出异常，无法继续进行。
 * @author hzy
 *
 */
@Service
public class CheckMemberInfoHandler implements SnsCommentInterceptor, SnsPostInterceptor {
	
	@Autowired
	protected MemberService memberService;
	
	private void checkMemberInfo(Member member){
		if (StringUtils.isBlank(member.getName())) {
			throw new CommonException("会员信息未完善，请完善后再进行操作。谢谢！");
		}
		
		if(member.getId()!=null && 
				Constant.NO_INT.equals(memberService.queryObjById(member.getId()).getIsEnable())){
			throw new CommonException("很抱歉，您暂时被禁言了，无法进行发帖和评论了！若有疑问，请与客服联系，谢谢！");
		}
	}

	@Override
	public boolean preHandle(SnsPost snsPost, Member member) {
		this.checkMemberInfo(member);
		return true;
	}

	@Override
	public boolean preHandle(SnsPost post, SnsComment comment, Member member) {
		this.checkMemberInfo(member);
		return true;
	}

	@Override
	public void afterCompletion(SnsPost post, SnsComment comment, Member member) {
	}

	@Override
	public void afterCompletion(SnsPost snsPost, Member member) {
	}

}
