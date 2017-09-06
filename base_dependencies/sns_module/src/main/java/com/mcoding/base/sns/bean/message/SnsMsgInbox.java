package com.mcoding.base.sns.bean.message;

import java.io.Serializable;
import java.util.Date;

import com.mcoding.base.sns.service.message.SnsMsgService;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="收信箱")
public class SnsMsgInbox implements Serializable {
	
//	########## 以下不是自动生成，请不要覆盖 ################3
	private SnsMsg snsMsg;
	
	public SnsMsg getSnsMsg() {
		if (this.getMsgId() != null) {
			snsMsg = SpringContextHolder.getOneBean(SnsMsgService.class).queryObjById(this.getMsgId());
		}
		return snsMsg;
	}

	public void setSnsMsg(SnsMsg snsMsg) {
		this.snsMsg = snsMsg;
	}
//	########################3
	
	@ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("店铺id")
    private Integer storeId;

    @ApiModelProperty("消息id")
    private Integer msgId;

    @ApiModelProperty("类型，100系统消息，200评论消息")
    private Integer msgType;

    @ApiModelProperty("收件人id")
    private Integer receiverId;

    @ApiModelProperty("是否已读,0未读，1已读")
    private Integer isRead;

    @ApiModelProperty("是否显示，1显示，2不显示")
    private Integer isEnable;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("阅读时间")
    private Date readTime;

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

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
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

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }
}