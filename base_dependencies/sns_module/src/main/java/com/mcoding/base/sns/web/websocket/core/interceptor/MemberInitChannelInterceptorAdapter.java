package com.mcoding.base.sns.web.websocket.core.interceptor;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;

import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;

public class MemberInitChannelInterceptorAdapter extends ChannelInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(MemberInitChannelInterceptorAdapter.class);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		try {
			MessageHeaders headers = message.getHeaders();
			SimpMessageType messageType = (SimpMessageType) headers.get("simpMessageType");
			if(SimpMessageType.CONNECT_ACK.equals(messageType)) {
				GenericMessage connectMsg = (GenericMessage) headers.get("simpConnectMessage");
				
				Map<String, Object> session = (Map<String, Object>) connectMsg.getHeaders().get("simpSessionAttributes");
				Member member = (Member) session.get("member");
				
				if (member != null) {
					message = MessageBuilder.createMessage(JsonUtilsForMcoding.writeValueAsString(member).getBytes(), headers);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return super.preSend(message, channel);
	}
}