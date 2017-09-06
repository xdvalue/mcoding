package com.mcoding.base.member.bean.department;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel(value="公司地址")
public class DepartmentAddress implements Serializable {
    @ApiModelProperty("地址id")
    private Integer id;

    @ApiModelProperty("公司id")
    private Integer companyId;

    @ApiModelProperty("收货人姓名")
    private String receiver;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("省市县地区 空格隔开")
    private String regson;

    @ApiModelProperty("详细地址")
    private String address;

    @ApiModelProperty("是否默认地址，0不是，1是")
    private Integer isDefault;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getRegson() {
        return regson;
    }

    public void setRegson(String regson) {
        this.regson = regson == null ? null : regson.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}