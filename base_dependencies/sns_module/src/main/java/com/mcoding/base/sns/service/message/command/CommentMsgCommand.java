package com.mcoding.base.sns.service.message.command;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.bean.setting.MemberSettingValue;
import com.mcoding.base.member.service.setting.MemberSettingValueService;
import com.mcoding.base.member.service.wechat.StoreWxRefService;
import com.mcoding.base.sns.bean.message.SnsMsg;
import com.mcoding.base.sns.bean.message.SnsMsgInbox;
import com.mcoding.base.sns.service.message.SnsMsgCommand;
import com.mcoding.base.sns.utils.SnsConstant;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.utils.common.Constant;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

public class CommentMsgCommand extends SnsMsgCommand{
	
	private Member sender;
	private Member receiver; 
	private Store store;
	private int commentId;
	private String content;
	
	private CommentMsgCommand(){
		super();
	}
	
	public CommentMsgCommand(Member sender, Member receiver, Store store, int commentId, String content) {
		this.sender = sender;
		this.receiver = receiver;
		this.store = store;
		this.commentId = commentId;
		this.content = content;
	}

	@Override
	public void execute() throws Exception {
		if (sender.getId().equals(receiver.getId())) {
			return;
		}
		String memberName = StringUtils.defaultIfBlank(sender.getName(), "神秘人");
		
		SnsMsg snsMsg = new SnsMsg();
		snsMsg.setSenderId(sender.getId());
		snsMsg.setSenderName(sender.getName());
		snsMsg.setReceiverId(receiver.getId());
		snsMsg.setReceiverName(receiver.getName());
		snsMsg.setStoreId(store.getId());
		snsMsg.setContent(memberName + "给你的帖子评论了，赶快去看看吧！");
		snsMsg.setRefId(commentId);
		snsMsg.setType(SnsMsg.MSG_TYPE_COMMENT);
		getSnsMsgService().addObj(snsMsg);
		
		SnsMsgInbox snsMsgInbox = new SnsMsgInbox();
		snsMsgInbox.setStoreId(snsMsg.getStoreId());
		snsMsgInbox.setMsgId(snsMsg.getId());
		snsMsgInbox.setMsgType(SnsMsg.MSG_TYPE_COMMENT);
		snsMsgInbox.setReceiverId(snsMsg.getReceiverId());
		snsMsgInbox.setIsRead(Constant.NO_INT);
		getSnsMsgInboxService().addObj(snsMsgInbox);
		
		MemberSettingValueService memberSettingValueService = SpringContextHolder.getOneBean(MemberSettingValueService.class);
		MemberSettingValue settingValue = memberSettingValueService.queryObjByMemberId(SnsConstant.MEMBER_SETTING_KEY_TPL_MSG_IS_ENABLE, receiver.getId(), store.getId(), SnsConstant.CODE_MODULE_TYPE_SNS);
		if (StringUtils.isBlank(settingValue.getSettingValue()) || settingValue.getSettingValue().equals(Constant.NO_INT.toString())) {
			return;
		}
		
		String domain = store.getStoreDomain().split(";")[0];
		String url = domain + SnsConstant.COMMENT_MSG_WECHAT_URL ;
		
		StoreWxRefService storeWxRefService = SpringContextHolder.getOneBean(StoreWxRefService.class);
		String dateStr = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		String msg = StringUtils.abbreviate(content, 15);
		storeWxRefService.sendWxMpTemplateMessage(store.getId(), "SnsReceiveCommentMsg", receiver.getWxMember().getWxOpenid(), url, memberName, dateStr, msg, null, null);
	}

	@Override
	public boolean beforeSend() {
		return true;
	}

	@Override
	public void afterComplete() {
		// TODO Auto-generated method stub
		
	}

	

}
