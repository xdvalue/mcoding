package com.mcoding.comp.wechat.redpack.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel(value="微信红包")
public class WxRedpack implements Serializable {
    private Integer id;

    @ApiModelProperty("公众号id")
    private Integer accoutId;

    @ApiModelProperty("公众号名称")
    private String accountName;

    @ApiModelProperty("红包代码，唯一标记")
    private String redpackCode;

    @ApiModelProperty("场景id")
    private String sceneId;

    @ApiModelProperty("活动名称，必填")
    private String actName;

    @ApiModelProperty("商户名称(发送者)，空的话设置为公众号名称")
    private String sendName;

    @ApiModelProperty("红包金额是否随机，1是，0否。必填")
    private String wishing;

    @ApiModelProperty("是否可用，1可用，0禁用")
    private Integer isEnable;

    @ApiModelProperty("是否随机，1是，0否")
    private Integer isRandom;

    @ApiModelProperty("金额额度上限")
    private Integer quotaLimitUp;

    @ApiModelProperty("金额额度下限")
    private Integer quotaLimitDown;

    @ApiModelProperty("每人每日领取红包的限制")
    private Integer receiveLimit;

    @ApiModelProperty("备注，必填")
    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccoutId() {
        return accoutId;
    }

    public void setAccoutId(Integer accoutId) {
        this.accoutId = accoutId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getRedpackCode() {
        return redpackCode;
    }

    public void setRedpackCode(String redpackCode) {
        this.redpackCode = redpackCode == null ? null : redpackCode.trim();
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

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getIsRandom() {
        return isRandom;
    }

    public void setIsRandom(Integer isRandom) {
        this.isRandom = isRandom;
    }

    public Integer getQuotaLimitUp() {
        return quotaLimitUp;
    }

    public void setQuotaLimitUp(Integer quotaLimitUp) {
        this.quotaLimitUp = quotaLimitUp;
    }

    public Integer getQuotaLimitDown() {
        return quotaLimitDown;
    }

    public void setQuotaLimitDown(Integer quotaLimitDown) {
        this.quotaLimitDown = quotaLimitDown;
    }

    public Integer getReceiveLimit() {
        return receiveLimit;
    }

    public void setReceiveLimit(Integer receiveLimit) {
        this.receiveLimit = receiveLimit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}