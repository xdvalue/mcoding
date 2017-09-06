package com.mcoding.base.product.bean.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel(value="产品分类")
public class ProductCategory implements Serializable {
	
	//##########后期添加的，请不要覆盖##############
    @ApiModelProperty("子类")
    private List<ProductCategory> childern;
    
    public List<ProductCategory> getChildern() {
    	return childern;
    }
    
    public void setChildern(List<ProductCategory> childern) {
    	this.childern = childern;
    }
    
//	/##################
	

	@ApiModelProperty("类目id")
    private Integer id;

    @ApiModelProperty("店铺id")
    private Integer storeId;

    @ApiModelProperty("类目名称")
    private String categoryName;

    @ApiModelProperty("父类id 0.顶级类目")
    private Integer categoryParentId;

    @ApiModelProperty("创建时间")
    private Date createTime;

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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Integer getCategoryParentId() {
        return categoryParentId;
    }

    public void setCategoryParentId(Integer categoryParentId) {
        this.categoryParentId = categoryParentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}