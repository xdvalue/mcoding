package com.mcoding.base.sns.service.message;

import com.mcoding.base.ui.utils.spring.SpringContextHolder;

public abstract class SnsMsgCommand {
	
	public abstract boolean beforeSend();
	
	public abstract void execute() throws Exception;
	
	public abstract void afterComplete();
	
	protected SnsMsgService getSnsMsgService(){
		return SpringContextHolder.getOneBean(SnsMsgService.class);
	}
	
	protected SnsMsgInboxService getSnsMsgInboxService(){
		return SpringContextHolder.getOneBean(SnsMsgInboxService.class);
	}

}
