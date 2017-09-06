package com.mcoding.base.sns.bean.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="消息")
public class SnsMsg implements Serializable {
	
//	############以下非自动生成 ，请不要回复############
	/**消息类型，系统消息**/
	public final static Integer MSG_TYPE_SYSTEM = 100;
	
	/**消息类型，评论提醒消息**/
	public final static Integer MSG_TYPE_COMMENT = 200;
//	#######################
	
    @ApiModelProperty("消息主键")
    private Integer id;

    private Integer storeId;

    @ApiModelProperty("类型，100系统消息，200评论消息")
    private Integer type;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("摘要")
    private String summary;

    @ApiModelProperty("消息内容")
    private String content;

    @ApiModelProperty("外链的连接")
    private String url;

    @ApiModelProperty(" 接收人id")
    private Integer receiverId;

    @ApiModelProperty("接收人姓名")
    private String receiverName;

    @ApiModelProperty("发送者id")
    private Integer senderId;

    @ApiModelProperty("发送者名称")
    private String senderName;

    @ApiModelProperty("关联资源id，如果是评论消息，就是评论的id")
    private Integer refId;

    @ApiModelProperty("是否有效，0无效，1有效")
    private Integer isEnable;

    @ApiModelProperty("消息创建时间")
    private Date createTime;

    @ApiModelProperty("有效截止时间")
    private Date enableTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName == null ? null : senderName.trim();
    }

    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEnableTime() {
        return enableTime;
    }

    public void setEnableTime(Date enableTime) {
        this.enableTime = enableTime;
    }
}