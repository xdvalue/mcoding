package com.mcoding.base.sns.service.message.event;

import org.springframework.context.ApplicationEvent;

import com.mcoding.base.sns.service.message.SnsMsgCommand;

public class SendMsgEvent extends ApplicationEvent {

	public SendMsgEvent(SnsMsgCommand source) {
		super(source);
	}

	private static final long serialVersionUID = -8989981227758859869L;

}
