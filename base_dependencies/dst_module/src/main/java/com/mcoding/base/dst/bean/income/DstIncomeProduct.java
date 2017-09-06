package com.mcoding.base.dst.bean.income;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel(value="产品-分销商级别关联表")
public class DstIncomeProduct implements Serializable {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("级别id")
    private Integer levelId;

    @ApiModelProperty("产品id")
    private Integer productId;

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("产品分销收益")
    private Integer income;

    @ApiModelProperty("分销商返利积分")
    private Integer point;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
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

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}