package com.mcoding.base.member.bean.setting;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="会员配置的具体值")
public class MemberSettingValue implements Serializable {
    private Integer id;

    private Integer storeId;

    private Integer memberId;

    private Integer settingKeyId;

    private String settingKeyCode;

    private String settingValue;

    private Date createTime;

    private Date updateTime;

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

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getSettingKeyId() {
        return settingKeyId;
    }

    public void setSettingKeyId(Integer settingKeyId) {
        this.settingKeyId = settingKeyId;
    }

    public String getSettingKeyCode() {
        return settingKeyCode;
    }

    public void setSettingKeyCode(String settingKeyCode) {
        this.settingKeyCode = settingKeyCode == null ? null : settingKeyCode.trim();
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue == null ? null : settingValue.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}