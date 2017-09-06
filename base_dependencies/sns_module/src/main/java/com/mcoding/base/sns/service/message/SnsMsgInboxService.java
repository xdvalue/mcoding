package com.mcoding.base.sns.service.message;

import java.util.List;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.sns.bean.message.CommentMsgInbox;
import com.mcoding.base.sns.bean.message.SnsMsgInbox;
import com.mcoding.base.sns.bean.message.SnsMsgInboxExample;

public interface SnsMsgInboxService extends BaseService<SnsMsgInbox, SnsMsgInboxExample> {
	
    public CommentMsgInbox transform(SnsMsgInbox msgInbox);
	
	public List<CommentMsgInbox>  transform(List<SnsMsgInbox> msgInboxList);

	public int countMsg(int memberId, int storeId, int msgTypeComment);
	
//	public PageView<SnsMsg> querySystemMsg(int pageNo, int pageSize, int member, int storeId);
//	
//	public PageView<SnsMsg> querySystemMsg(int pageNo, int pageSize, int member, int storeId);
}