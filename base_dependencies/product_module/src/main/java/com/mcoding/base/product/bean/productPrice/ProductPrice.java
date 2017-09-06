package com.mcoding.base.product.bean.productPrice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="产品价格")
public class ProductPrice implements Serializable {
	
	//#########这是后期添加的，请不要覆盖###################
	@ApiModelProperty("积分")
	private Integer point;
	
	public Integer getPoint() {
		return point;
	}
	
	public void setPoint(Integer point) {
		this.point = point;
	}
	
	//#################################
	@ApiModelProperty("价格id")
    private Integer id;

    @ApiModelProperty("商品id")
    private Integer productId;

    @ApiModelProperty("价格")
    private Integer value;

    @ApiModelProperty("价格类型  1.原价 2.销售价  3.活动价")
    private Integer type;

    @ApiModelProperty("规则id")
    private Integer sceneId;

    private String sceneName;

    @ApiModelProperty("关联id 1.默认 2.微商渠道id  3.活动id")
    private Integer referId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSceneId() {
        return sceneId;
    }

    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName == null ? null : sceneName.trim();
    }

    public Integer getReferId() {
        return referId;
    }

    public void setReferId(Integer referId) {
        this.referId = referId;
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