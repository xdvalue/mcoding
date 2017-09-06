package com.mcoding.base.sns.web.websocket.core.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageHeaders;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Configuration
public class WebSocketDisconnectEventListener implements ApplicationListener<SessionDisconnectEvent> {
	private static final Logger logger = LoggerFactory.getLogger(WebSocketDisconnectEventListener.class);
	
	@Override
	public void onApplicationEvent(SessionDisconnectEvent event) {
		MessageHeaders headers = event.getMessage().getHeaders();
		String sessionId = (String) headers.get("simpSessionId");
//		
//		Map<String, String> sessionAttributes = (Map<String, String>) headers.get("simpSessionAttributes");
//		String userId = sessionAttributes.get("login");
		
		logger.info("User with sessionId {} disconnected", sessionId);
		
		
	}
}
