package com.mcoding.base.member.bean.wechat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel(value="店铺-公众号-关联表")
public class StoreWxRef implements Serializable {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("店铺主键")
    private Integer storeId;

    @ApiModelProperty("店铺名称")
    private String storeName;

    @ApiModelProperty("微信账号id")
    private Integer wxAccountId;

    @ApiModelProperty("微信账号名称")
    private String wxAccountName;

    @ApiModelProperty("微信帐号 原始id")
    private String wxAccountOriginId;

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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public Integer getWxAccountId() {
        return wxAccountId;
    }

    public void setWxAccountId(Integer wxAccountId) {
        this.wxAccountId = wxAccountId;
    }

    public String getWxAccountName() {
        return wxAccountName;
    }

    public void setWxAccountName(String wxAccountName) {
        this.wxAccountName = wxAccountName == null ? null : wxAccountName.trim();
    }

    public String getWxAccountOriginId() {
        return wxAccountOriginId;
    }

    public void setWxAccountOriginId(String wxAccountOriginId) {
        this.wxAccountOriginId = wxAccountOriginId == null ? null : wxAccountOriginId.trim();
    }
}