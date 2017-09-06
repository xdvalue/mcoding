package com.mcoding.base.sns.web.websocket.core.interceptor;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class SyncHttpSessionInterceptor implements HandshakeInterceptor{

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
        if(request instanceof ServletServerHttpRequest){
        	ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession();
            if (session != null) {
               merge(session, attributes);
            }
		}
		return true;
	}
	
	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
	}

	/*@Override
	public boolean doHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws HandshakeFailureException {
		if(request instanceof ServletServerHttpRequest){
        	ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession();
            if (session != null) {
               merge(session, attributes);
            }
		}
		return super.doHandshake(request, response, wsHandler, attributes);
	}*/
	
	private void merge(HttpSession session, Map<String, Object> attributes) {
		Enumeration<String> attributeInSession = session.getAttributeNames();
		while (attributeInSession.hasMoreElements()) {
			String key = attributeInSession.nextElement();
			
			if(!attributes.containsKey(key))
			    attributes.put(key, session.getAttribute(key));
		}
	}

}
