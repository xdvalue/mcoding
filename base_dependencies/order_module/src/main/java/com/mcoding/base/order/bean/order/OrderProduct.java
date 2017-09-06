package com.mcoding.base.order.bean.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel(value="订单--商品--关联")
public class OrderProduct implements Serializable {
    @ApiModelProperty("订单商品关联ID")
    private Integer id;

    @ApiModelProperty("订单id")
    private Integer storeId;

    @ApiModelProperty("订单id")
    private Integer orderId;

    @ApiModelProperty("商品id")
    private Integer productId;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("产品图片")
    private String productImg;

    @ApiModelProperty("商品单价或加钱购金额")
    private Integer productPrice;

    @ApiModelProperty("单个商品数量")
    private Integer nums;

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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }
}