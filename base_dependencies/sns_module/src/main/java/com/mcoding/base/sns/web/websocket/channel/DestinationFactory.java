package com.mcoding.base.sns.web.websocket.channel;

public class DestinationFactory {
	
	public String build(ChannelMsgType msgType){
		return "/msgNotify".concat(msgType.getChannelSuffix());
	}
	
	private DestinationFactory(){
		super();
	}

}
