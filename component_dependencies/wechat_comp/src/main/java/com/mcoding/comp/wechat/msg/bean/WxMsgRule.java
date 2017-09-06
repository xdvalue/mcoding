package com.mcoding.comp.wechat.msg.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="微信消息规则")
public class WxMsgRule implements Serializable, Cloneable {
	
//	#################以下非自动生成，请不要覆盖#####
	/**完全匹配**/
	public static final Integer MATCH_TYPE_ALL = 100;
	/**正则匹配**/
	public static final Integer MATCH_TYPE_REGEX = 200;
	
	@Override
	public WxMsgRule clone() throws CloneNotSupportedException {
		return (WxMsgRule) super.clone();
	}

//	##############################################
	
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("规则名称")
    private String name;

    @ApiModelProperty("微信公众号id")
    private Integer wxAccountId;

    @ApiModelProperty("微信公众号编码")
    private String wxAccountOriginId;

    @ApiModelProperty("规则处理器")
    private String handlers;

    @ApiModelProperty("是否启用")
    private Integer isEnable;

    @ApiModelProperty("是否默认处理器")
    private Integer isDefault;

    @ApiModelProperty("是否异步操作，1是，0否")
    private Integer isSycn;

    @ApiModelProperty("消息来源openid")
    private String fromUserName;

    @ApiModelProperty("消息类型")
    private String msgType;

    @ApiModelProperty("消息内容")
    private String content;

    @ApiModelProperty("消息的有效开始时间")
    private Date msgStartTime;

    @ApiModelProperty("消息有效的截止时间")
    private Date msgEndTime;

    @ApiModelProperty("事件类型")
    private String event;

    @ApiModelProperty("事件KEY值")
    private String eventKey;

    @ApiModelProperty("匹配类型,100完全匹配，200正则匹配")
    private Integer matchType;

    @ApiModelProperty("回复内容的外键id")
    private Integer replyContentRefId;

    @ApiModelProperty("优先级")
    private Integer priority;

    private Date createTime;

    @ApiModelProperty("回复的内容")
    private String replyContent;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getWxAccountId() {
        return wxAccountId;
    }

    public void setWxAccountId(Integer wxAccountId) {
        this.wxAccountId = wxAccountId;
    }

    public String getWxAccountOriginId() {
        return wxAccountOriginId;
    }

    public void setWxAccountOriginId(String wxAccountOriginId) {
        this.wxAccountOriginId = wxAccountOriginId == null ? null : wxAccountOriginId.trim();
    }

    public String getHandlers() {
        return handlers;
    }

    public void setHandlers(String handlers) {
        this.handlers = handlers == null ? null : handlers.trim();
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getIsSycn() {
        return isSycn;
    }

    public void setIsSycn(Integer isSycn) {
        this.isSycn = isSycn;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName == null ? null : fromUserName.trim();
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getMsgStartTime() {
        return msgStartTime;
    }

    public void setMsgStartTime(Date msgStartTime) {
        this.msgStartTime = msgStartTime;
    }

    public Date getMsgEndTime() {
        return msgEndTime;
    }

    public void setMsgEndTime(Date msgEndTime) {
        this.msgEndTime = msgEndTime;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event == null ? null : event.trim();
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey == null ? null : eventKey.trim();
    }

    public Integer getMatchType() {
        return matchType;
    }

    public void setMatchType(Integer matchType) {
        this.matchType = matchType;
    }

    public Integer getReplyContentRefId() {
        return replyContentRefId;
    }

    public void setReplyContentRefId(Integer replyContentRefId) {
        this.replyContentRefId = replyContentRefId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }
}