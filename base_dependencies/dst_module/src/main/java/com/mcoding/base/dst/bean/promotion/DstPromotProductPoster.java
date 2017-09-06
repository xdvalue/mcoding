package com.mcoding.base.dst.bean.promotion;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="产品推广二维码")
public class DstPromotProductPoster implements Serializable {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("产品id")
    private Integer productId;

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("产品推广海报id")
    private Integer templateImgId;

    @ApiModelProperty("是否可用")
    private Integer isEnable;

    @ApiModelProperty("创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Integer getTemplateImgId() {
        return templateImgId;
    }

    public void setTemplateImgId(Integer templateImgId) {
        this.templateImgId = templateImgId;
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
}