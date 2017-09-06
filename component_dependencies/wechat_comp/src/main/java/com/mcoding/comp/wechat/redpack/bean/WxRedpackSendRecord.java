package com.mcoding.comp.wechat.redpack.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="微信红包")
public class WxRedpackSendRecord implements Serializable {
	
//	############以下非自动生成，请不要覆盖##############

	/**待发送**/
	public static final String STATUS_UN_SENT = "UNSENT";
	/**已发送**/
	public static final String STATUS_SENT = "SENT";
	/**发送失败**/
	public static final String STATUS_FAILED = "FAILED";
	/**已领取**/
	public static final String STATUS_RECEIVED = "RECEIVED";
	/**退款中**/
	public static final String STATUS_REFUNDING = "REFUNDING";
	/**已退款**/
	public static final String STATUS_REFUND = "REFUND";
	
//	##########################
	
    private Integer id;

    @ApiModelProperty("公众号id")
    private Integer accountId;

    @ApiModelProperty("公众号名称")
    private String accountName;

    @ApiModelProperty("红包订单号")
    private String billNo;

    @ApiModelProperty("接受人的openid")
    private String openid;

    @ApiModelProperty("接受人的微信昵称")
    private String memberName;

    @ApiModelProperty("红包id")
    private Integer redpackId;

    @ApiModelProperty("红包代码，唯一标记")
    private String redpackCode;

    @ApiModelProperty("发送金额，单位分")
    private Integer totalAmount;

    @ApiModelProperty("场景id")
    private String sceneId;

    @ApiModelProperty("活动名称")
    private String actName;

    @ApiModelProperty("商户名称(发送者)")
    private String sendName;

    @ApiModelProperty("红包金额是否随机，1是，0否")
    private String wishing;

    @ApiModelProperty("红包状态：SENDING:发放中 ，SENT:已发放待领取 ，FAILED：发放失败 ，RECEIVED:已领取 ，RFUND_ING:退款中 ，REFUND:已退款")
    private String status;

    @ApiModelProperty("发送时间")
    private Date sendTime;

    @ApiModelProperty("领取红包的时间")
    private Date receiveTime;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("备注")
    private String remark;

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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo == null ? null : billNo.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public Integer getRedpackId() {
        return redpackId;
    }

    public void setRedpackId(Integer redpackId) {
        this.redpackId = redpackId;
    }

    public String getRedpackCode() {
        return redpackCode;
    }

    public void setRedpackCode(String redpackCode) {
        this.redpackCode = redpackCode == null ? null : redpackCode.trim();
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId == null ? null : sceneId.trim();
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName == null ? null : actName.trim();
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName == null ? null : sendName.trim();
    }

    public String getWishing() {
        return wishing;
    }

    public void setWishing(String wishing) {
        this.wishing = wishing == null ? null : wishing.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}