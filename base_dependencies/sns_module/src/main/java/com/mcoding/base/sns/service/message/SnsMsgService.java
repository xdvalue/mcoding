package com.mcoding.base.sns.service.message;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.sns.bean.message.SnsMsg;
import com.mcoding.base.sns.bean.message.SnsMsgExample;

public interface SnsMsgService extends BaseService<SnsMsg, SnsMsgExample> {

//	public void addCommentMsg(SnsMsg snsMsg);
	
	public boolean sendMsg(SnsMsgCommand msgCommand) throws Exception;
}