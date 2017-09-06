package com.mcoding.base.member.bean.setting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="会员配置")
public class MemberSettingKey implements Serializable {
    private Integer id;

    @ApiModelProperty("模块类型，100 sns模块，200会员模块")
    private Integer moduleType;

    @ApiModelProperty("配置名称")
    private String settingName;

    @ApiModelProperty("配置代码")
    private String settingCode;

    @ApiModelProperty("配置默认值")
    private String settingDefaultValue;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModuleType() {
        return moduleType;
    }

    public void setModuleType(Integer moduleType) {
        this.moduleType = moduleType;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName == null ? null : settingName.trim();
    }

    public String getSettingCode() {
        return settingCode;
    }

    public void setSettingCode(String settingCode) {
        this.settingCode = settingCode == null ? null : settingCode.trim();
    }

    public String getSettingDefaultValue() {
        return settingDefaultValue;
    }

    public void setSettingDefaultValue(String settingDefaultValue) {
        this.settingDefaultValue = settingDefaultValue == null ? null : settingDefaultValue.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}