package com.mcoding.base.sns.web.websocket.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.support.AbstractSubscribableChannel;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.DelegatingWebSocketMessageBrokerConfiguration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.StompWebSocketEndpointRegistration;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.mcoding.base.sns.web.websocket.core.interceptor.MemberInitChannelInterceptorAdapter;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfigure extends AbstractWebSocketMessageBrokerConfigurer  {
	
	@Autowired 
	DelegatingWebSocketMessageBrokerConfiguration configuration;
	
	@Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        StompWebSocketEndpointRegistration endPoint = registry.addEndpoint("/sns_ws");
        //允许所有域名连进来
        endPoint.setAllowedOrigins("*");
        endPoint.addInterceptors(new HttpSessionHandshakeInterceptor());
        endPoint.withSockJS();
        
        AbstractSubscribableChannel clientOutboundChannel = configuration.clientOutboundChannel();
		clientOutboundChannel.addInterceptor(new MemberInitChannelInterceptorAdapter());
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }
	
}
