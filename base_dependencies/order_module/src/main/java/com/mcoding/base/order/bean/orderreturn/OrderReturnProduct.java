package com.mcoding.base.order.bean.orderreturn;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel(value="退换货的申请明细")
public class OrderReturnProduct implements Serializable {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("店铺id")
    private Integer storeId;

    @ApiModelProperty("退换货单id")
    private Integer orderReturnId;

    @ApiModelProperty("产品id")
    private Integer productId;

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("产品封面")
    private String productImg;

    @ApiModelProperty("产品退货数量")
    private Integer nums;

    @ApiModelProperty("产品退款金额")
    private String returnFee;

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

    public Integer getOrderReturnId() {
        return orderReturnId;
    }

    public void setOrderReturnId(Integer orderReturnId) {
        this.orderReturnId = orderReturnId;
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

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg == null ? null : productImg.trim();
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public String getReturnFee() {
        return returnFee;
    }

    public void setReturnFee(String returnFee) {
        this.returnFee = returnFee == null ? null : returnFee.trim();
    }
}