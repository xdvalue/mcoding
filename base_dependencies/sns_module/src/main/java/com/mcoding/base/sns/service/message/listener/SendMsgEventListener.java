package com.mcoding.base.sns.service.message.listener;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.mcoding.base.sns.service.message.SnsMsgCommand;
import com.mcoding.base.sns.service.message.SnsMsgService;
import com.mcoding.base.sns.service.message.event.SendMsgEvent;

@Component
public class SendMsgEventListener implements ApplicationListener<SendMsgEvent> {
	
	private static Logger logger = LoggerFactory.getLogger(SendMsgEventListener.class);
	
	@Resource
	protected SnsMsgService snsMsgService;

	@Override
	public void onApplicationEvent(SendMsgEvent event) {
		try {
			SnsMsgCommand msgCommand = (SnsMsgCommand) event.getSource();
			
			if (!msgCommand.beforeSend()) {
				logger.warn("before send msg , it failed");
				return;
			}
			msgCommand.execute();
			msgCommand.afterComplete();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}


}
