package com.mcoding.comp.wechat.qrcode.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="微信二维码")
public class WxQrcode implements Serializable {
	
//	#################################
	/**该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）**/
	public static final Integer QRCODE_MAX_EXPIRED_SECOND = 2592000;
	
	/**临时二维码**/
	public static final Integer QRCODE_TYPE_LIMIT = 1;
	
	/**永久二维码**/
	public static final Integer QRCODE_TYPE_UNLIMIT = 2;
	
	public static final Integer PREFIX_FOR_LIMIT = 10000000;
	public static final String PREFIX_FOR_UNLIMIT = "UNLIMIT_";
	
	private Integer validHours;
	
	public Integer getValidHours() {
		return validHours;
	}
	
	public void setValidHours(Integer validHours) {
		this.validHours = validHours;
	}
	
//	#################################

    private Integer id;

    @ApiModelProperty("微信公众号id")
    private Integer accountId;

    @ApiModelProperty("公众号的原始id")
    private String accountOriginId;

    @ApiModelProperty("二维码名字")
    private String name;

    @ApiModelProperty("二维码类型:1临时二维码，2永久二维码，3自定义二维码")
    private Integer type;

    @ApiModelProperty("二维码的存储地址")
    private String imgUrl;

    @ApiModelProperty("场景id，临时是整型，长久是字符串")
    private String sceneStr;

    @ApiModelProperty("过期时间")
    private Date validTime;

    @ApiModelProperty("获取的二维码ticket")
    private String ticket;

    @ApiModelProperty("二维码图片解析后的地址")
    private String content;

    @ApiModelProperty("扫码的次数")
    private Integer scanCount;

    @ApiModelProperty("处理器")
    private Integer handlerId;

    @ApiModelProperty("回复类型")
    private String replyType;

    @ApiModelProperty("回复内容")
    private String replyContent;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountOriginId() {
        return accountOriginId;
    }

    public void setAccountOriginId(String accountOriginId) {
        this.accountOriginId = accountOriginId == null ? null : accountOriginId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getSceneStr() {
        return sceneStr;
    }

    public void setSceneStr(String sceneStr) {
        this.sceneStr = sceneStr == null ? null : sceneStr.trim();
    }

    public Date getValidTime() {
        return validTime;
    }

    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket == null ? null : ticket.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getScanCount() {
        return scanCount;
    }

    public void setScanCount(Integer scanCount) {
        this.scanCount = scanCount;
    }

    public Integer getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Integer handlerId) {
        this.handlerId = handlerId;
    }

    public String getReplyType() {
        return replyType;
    }

    public void setReplyType(String replyType) {
        this.replyType = replyType == null ? null : replyType.trim();
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }
}