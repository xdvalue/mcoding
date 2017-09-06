package com.mcoding.base.sns.service.message.command;

import org.apache.commons.lang.StringUtils;

import com.mcoding.base.sns.bean.message.SnsMsg;
import com.mcoding.base.sns.bean.message.SnsMsgInbox;
import com.mcoding.base.sns.bean.post.SnsPost;
import com.mcoding.base.sns.service.message.SnsMsgCommand;
import com.mcoding.base.sns.utils.SnsConstant;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.service.store.StoreService;
import com.mcoding.base.ui.utils.common.Constant;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

/***
 * 帖子加精 或者 帖子置顶的消息提醒
 * @author hzy
 *
 */
public class TopOrEssencePostMsgCommand extends SnsMsgCommand{
	
	public static final String ACTION_UP_POST = "up_post";
	public static final String ACTION_UP_POST_IN_HOME = "up_post_in_home";
	public static final String ACTION_ESSENCE = "essence_post";
	
	private SnsPost snsPost;
	private String action;
	
	public TopOrEssencePostMsgCommand(SnsPost snsPost, String action) {
		super();
		this.snsPost = snsPost;
		this.action = action;
	}

	@Override
	public boolean beforeSend() {
		return true;
	}

	@Override
	public void execute() throws Exception {
        String content = getContent();
		if (StringUtils.isBlank(content)) {
			throw new NullPointerException("消息内容为空，请检查消息的参数是否正确");
		}
		
		String domain = this.getStore().getStoreDomain().split(";")[0];
		String url = domain + SnsConstant.POST_DETAIL_URL + "?articleId=" + this.getSnsPost().getId();
		
		SnsMsg snsMsg = new SnsMsg();
		
		snsMsg.setTitle(this.getMsgTitle());
		snsMsg.setSummary(content);
		snsMsg.setReceiverId(this.getSnsPost().getMemeberId());
		snsMsg.setReceiverName(this.getSnsPost().getMemberName());
		snsMsg.setStoreId(this.getSnsPost().getStoreId());
		snsMsg.setContent(content);
		snsMsg.setRefId(this.getSnsPost().getId());
		snsMsg.setType(SnsMsg.MSG_TYPE_SYSTEM);
		snsMsg.setUrl(url);
		getSnsMsgService().addObj(snsMsg);
		
		SnsMsgInbox snsMsgInbox = new SnsMsgInbox();
		snsMsgInbox.setStoreId(snsMsg.getStoreId());
		snsMsgInbox.setMsgId(snsMsg.getId());
		snsMsgInbox.setMsgType(SnsMsg.MSG_TYPE_SYSTEM);
		snsMsgInbox.setReceiverId(snsMsg.getReceiverId());
		snsMsgInbox.setIsRead(Constant.NO_INT);
		getSnsMsgInboxService().addObj(snsMsgInbox);
	}
	
	private String getMsgTitle() {
		switch (getAction()) {
		case ACTION_UP_POST:
			return "帖子置顶通知";
		case ACTION_UP_POST_IN_HOME:
			return "帖子置顶通知";
		case ACTION_ESSENCE:
			return "帖子加精通知";
		}
		return "通知";
	}

	private String getContent(){
		switch (getAction()) {
		case ACTION_UP_POST:
			return "您发的帖子["+getSnsPost().getTitle()+"] 刚刚被小编置顶了，快去看看吧！";
		case ACTION_UP_POST_IN_HOME:
			return "您发的帖子["+getSnsPost().getTitle()+"] 刚刚被小编置顶了，快去看看吧！";
		case ACTION_ESSENCE:
			return "您发的帖子["+getSnsPost().getTitle()+"] 刚刚被小编加精了，快去看看吧！";
		}
		return "";
	}

	public SnsPost getSnsPost() {
		return snsPost;
	}

	public void setSnsPost(SnsPost snsPost) {
		this.snsPost = snsPost;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public Store getStore(){
		StoreService storeService = SpringContextHolder.getOneBean(StoreService.class);
		return storeService.queryObjById(snsPost.getStoreId());
	}

	@Override
	public void afterComplete() {
		// TODO Auto-generated method stub
		
	}

}
