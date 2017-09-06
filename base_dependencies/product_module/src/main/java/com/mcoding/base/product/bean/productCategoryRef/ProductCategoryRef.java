package com.mcoding.base.product.bean.productCategoryRef;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="产品-分类-关联")
public class ProductCategoryRef implements Serializable {
    @ApiModelProperty("商品ID")
    private Integer id;

    @ApiModelProperty("分类id")
    private Integer productId;

    @ApiModelProperty("商品分类名称")
    private String categoryName;

    @ApiModelProperty("类目id")
    private Integer categoryId;

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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}