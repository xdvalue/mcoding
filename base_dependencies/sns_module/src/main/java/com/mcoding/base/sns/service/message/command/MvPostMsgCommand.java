package com.mcoding.base.sns.service.message.command;

import com.mcoding.base.sns.bean.message.SnsMsg;
import com.mcoding.base.sns.bean.message.SnsMsgInbox;
import com.mcoding.base.sns.bean.module.SnsModule;
import com.mcoding.base.sns.bean.post.SnsPost;
import com.mcoding.base.sns.service.message.SnsMsgCommand;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.utils.common.Constant;

public class MvPostMsgCommand extends SnsMsgCommand {
	
	private SnsPost snsPost;
	private SnsModule preSnsModule;
	private SnsModule curSnsModule;
	private Store store;

	public MvPostMsgCommand(SnsPost snsPost, SnsModule preSnsModule, SnsModule curSnsModule, Store store) {
		super();
		this.snsPost = snsPost;
		this.preSnsModule = preSnsModule;
		this.curSnsModule = curSnsModule;
		this.store = store;
	}

	@Override
	public boolean beforeSend() {
		return true;
	}

	@Override
	public void execute() throws Exception {
		String content = "您好，管理员已经转移了你的帖子，从版块["+this.getPreSnsModule().getName()+"] 移到 版块["+this.getCurSnsModule().getName()+"]";
		
		SnsMsg snsMsg = new SnsMsg();
		snsMsg.setTitle("帖子转移通知");
		snsMsg.setSummary(content);
		snsMsg.setReceiverId(this.getSnsPost().getMemeberId());
		snsMsg.setReceiverName(this.getSnsPost().getMemberName());
		snsMsg.setStoreId(this.getSnsPost().getStoreId());
		snsMsg.setContent(content);
		snsMsg.setRefId(this.getSnsPost().getId());
		snsMsg.setType(SnsMsg.MSG_TYPE_SYSTEM);
		getSnsMsgService().addObj(snsMsg);
		
		SnsMsgInbox snsMsgInbox = new SnsMsgInbox();
		snsMsgInbox.setStoreId(snsMsg.getStoreId());
		snsMsgInbox.setMsgId(snsMsg.getId());
		snsMsgInbox.setMsgType(SnsMsg.MSG_TYPE_SYSTEM);
		snsMsgInbox.setReceiverId(snsMsg.getReceiverId());
		snsMsgInbox.setIsRead(Constant.NO_INT);
		getSnsMsgInboxService().addObj(snsMsgInbox);
		
	}

	@Override
	public void afterComplete() {
	}
	
	public SnsPost getSnsPost() {
		return snsPost;
	}

	public void setSnsPost(SnsPost snsPost) {
		this.snsPost = snsPost;
	}

	public SnsModule getPreSnsModule() {
		return preSnsModule;
	}

	public void setPreSnsModule(SnsModule preSnsModule) {
		this.preSnsModule = preSnsModule;
	}

	public SnsModule getCurSnsModule() {
		return curSnsModule;
	}

	public void setCurSnsModule(SnsModule curSnsModule) {
		this.curSnsModule = curSnsModule;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
}
