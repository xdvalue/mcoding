package com.mcoding.base.sns.service.comment.impl;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.service.member.MemberService;
import com.mcoding.base.member.service.setting.MemberSettingValueService;
import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.sns.bean.comment.SnsComment;
import com.mcoding.base.sns.bean.post.SnsPost;
import com.mcoding.base.sns.service.comment.SnsCommentInterceptor;
import com.mcoding.base.sns.service.comment.SnsCommentService;
import com.mcoding.base.sns.service.message.SnsMsgService;
import com.mcoding.base.sns.service.message.command.CommentMsgCommand;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.utils.StoreUtils;

@Component
public class AfterCommentSendMsgHandler implements SnsCommentInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(AfterCommentSendMsgHandler.class);
	
	@Resource
	protected MemberSettingValueService memberSettingValueService;
	
	@Resource
	protected SnsMsgService snsMsgService;
	
	@Resource
	protected MemberService memberService;
	
	@Resource
	protected StoreWxRefService storeWxRefService;
	
	@Resource
	protected SnsCommentService snsCommentService;

	@Override
	public boolean preHandle(SnsPost post, SnsComment comment, Member member) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void afterCompletion(final SnsPost post, final SnsComment comment, final Member member) {
//		if (comment.getMemberId().equals(post.getMemeberId())) {
//			return;
//		}
		
		final Store store = StoreUtils.getStoreFromThreadLocal();
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					AfterCommentSendMsgHandler.this.sendMsg(post, comment, member, store);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		});
		thread.start();
		
		
	}
	
	private void sendMsg(SnsPost post, SnsComment comment, Member sender, Store store) throws Exception{
		String content = comment.getContent();
		if (StringUtils.isBlank(content) && CollectionUtils.isNotEmpty(comment.getPostImgs())) {
			content = "(回复的图片,请点击查看)";
		}
		
//		发消息给楼主
		Integer postOwner = post.getMemeberId();
		if (!postOwner.equals(sender.getId())) {
			Member receiver = AfterCommentSendMsgHandler.this.memberService.queryObjById(post.getMemeberId());
			this.snsMsgService.sendMsg(new CommentMsgCommand(sender, receiver, store, comment.getId(), content));
		}
		
//		发消息给被评论的人
		Integer peopleCommented = comment.getParentMemberId();
		if(peopleCommented !=null && !peopleCommented.equals(sender.getId())
				&& !peopleCommented.equals(postOwner)){
            Member parentCommentAuthor = this.memberService.queryObjById(peopleCommented);
			this.snsMsgService.sendMsg(new CommentMsgCommand(sender, parentCommentAuthor, store, comment.getId(), content));
		}
	}

}
