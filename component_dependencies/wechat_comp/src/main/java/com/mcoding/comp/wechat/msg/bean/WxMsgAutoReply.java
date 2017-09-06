package com.mcoding.comp.wechat.msg.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="自动回复的消息规则")
public class WxMsgAutoReply implements Serializable {
	
//	###################
	public static Integer REPLY_TYPE_TEXT = 1;
	public static Integer REPLY_TYPE_NEWS = 2;
	
//	###################
	
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("微信公众号原始id")
    private String wxAccountOriginId;

    @ApiModelProperty("关键字")
    private String keywords;

    @ApiModelProperty("回复类型，1文本，2图文")
    private Integer replyType;

    @ApiModelProperty("自动回复的内容")
    private String replyContent;

    @ApiModelProperty("匹配模式，100完全匹配，200模糊匹配")
    private String matchType;

    @ApiModelProperty("优先级")
    private Integer priority;

    @ApiModelProperty("是否默认回复")
    private Integer isDefault;

    @ApiModelProperty("创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWxAccountOriginId() {
        return wxAccountOriginId;
    }

    public void setWxAccountOriginId(String wxAccountOriginId) {
        this.wxAccountOriginId = wxAccountOriginId == null ? null : wxAccountOriginId.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public Integer getReplyType() {
        return replyType;
    }

    public void setReplyType(Integer replyType) {
        this.replyType = replyType;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType == null ? null : matchType.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}