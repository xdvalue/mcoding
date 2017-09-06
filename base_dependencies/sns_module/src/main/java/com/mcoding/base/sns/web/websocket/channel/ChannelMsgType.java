package com.mcoding.base.sns.web.websocket.channel;

public enum ChannelMsgType {
	
	SYS_MSG("/msgNotify/sysMsg"),
	COMMENT_MSG("/msgNotify/comment"),
	POINT_MSG("/msgNotify/point");
	
	private String channelSuffix;
	private ChannelMsgType(String channelSuffix){
		this.channelSuffix = channelSuffix;
	}
	
	public String getChannelSuffix(){
		return this.channelSuffix;
	}
	

}
