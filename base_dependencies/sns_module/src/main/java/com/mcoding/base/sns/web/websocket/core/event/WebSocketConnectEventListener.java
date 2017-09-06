package com.mcoding.base.sns.web.websocket.core.event;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageHeaders;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Configuration
public class WebSocketConnectEventListener implements ApplicationListener<SessionConnectedEvent> {
	private static final Logger logger = LoggerFactory.getLogger(WebSocketConnectEventListener.class);
	
	@Override
	public void onApplicationEvent(SessionConnectedEvent event) {
		MessageHeaders headers = event.getMessage().getHeaders();
//		Map<String, Object> sessionAttributes = (Map<String, Object>) headers.get("simpSessionAttributes");
		
		int memberId = 0;
		String sessionId = (String) headers.get("simpSessionId");
		
		logger.info("User{} with sessionId {} connected",memberId, sessionId);
	}
}
