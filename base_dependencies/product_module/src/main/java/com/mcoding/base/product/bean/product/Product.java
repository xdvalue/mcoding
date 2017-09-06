package com.mcoding.base.product.bean.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.mcoding.base.product.bean.productPrice.ProductPrice;
import com.mcoding.base.product.bean.productSet.ProductSet;

@ApiModel(value="产品")
public class Product implements Serializable {
	
//	############后期添加的，请不要覆盖####################
	
	/**商品**/
	public static final Integer PRODUCT_TYPE_SALE = 1;
	
	/**赠品**/
	public static final Integer PRODUCT_TYPE_GIVE = 2;
	
	@ApiModelProperty("商品价格")
	private List<ProductPrice> priceList;
    
    @ApiModelProperty("商品详细内容")
    private String content;

    @ApiModelProperty("商品功能介绍")
    private String introduce;

    @ApiModelProperty("商品摘要")
    private String summary;

    @ApiModelProperty("商品分类ID")
    private Integer categoryId;
    
    @ApiModelProperty("商品图片")
    private List<ProductImg> imgList;
    
    @ApiModelProperty("套餐产品列表")
    private List<ProductSet> productSet;
    
    public List<ProductSet> getProductSet() {
		return productSet;
	}

	public void setProductSet(List<ProductSet> productSet) {
		this.productSet = productSet;
	}

	public List<ProductImg> getImgList() {
		return imgList;
	}

	public void setImgList(List<ProductImg> imgList) {
		this.imgList = imgList;
	}

	public List<ProductPrice> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<ProductPrice> priceList) {
		this.priceList = priceList;
	}

	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    //	################################
	
    @ApiModelProperty("商品id")
    private Integer id;

    @ApiModelProperty("店铺ID")
    private Integer storeId;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("商品条形码 69开头")
    private String barCode;

    @ApiModelProperty("4位数的商品防伪编码，适用于防伪积分使用")
    private String fakeCode;

    @ApiModelProperty("商品编码")
    private String numberCode;

    @ApiModelProperty("商品类型 1.商品  2.赠品")
    private Integer type;

    @ApiModelProperty("商品标签 1.普通商品  2.内购商品")
    private Integer label;

    @ApiModelProperty("商品主题广告语")
    private String slogan;

    @ApiModelProperty("商品封面图")
    private String coverImg;

    @ApiModelProperty("商品排序")
    private Integer sequence;

    @ApiModelProperty("上架状态  0,下架  1,上架")
    private Integer saleStatus;

    @ApiModelProperty("积分商城是否兑换状态  0,下架  1,上架")
    private String giftExchangeStatus;

    @ApiModelProperty("商品是否设定为套餐 0,非套餐  1,设为套餐")
    private Integer setStatus;

    @ApiModelProperty("商品是否限购 0.不限购 1限购")
    private Integer limitStatus;

    @ApiModelProperty("商品限购总数量")
    private Integer limitQuota;

    @ApiModelProperty("每人限购件数")
    private Integer limitBuyQuota;

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

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode == null ? null : barCode.trim();
    }

    public String getFakeCode() {
        return fakeCode;
    }

    public void setFakeCode(String fakeCode) {
        this.fakeCode = fakeCode == null ? null : fakeCode.trim();
    }

    public String getNumberCode() {
        return numberCode;
    }

    public void setNumberCode(String numberCode) {
        this.numberCode = numberCode == null ? null : numberCode.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan == null ? null : slogan.trim();
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg == null ? null : coverImg.trim();
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(Integer saleStatus) {
        this.saleStatus = saleStatus;
    }

    public String getGiftExchangeStatus() {
        return giftExchangeStatus;
    }

    public void setGiftExchangeStatus(String giftExchangeStatus) {
        this.giftExchangeStatus = giftExchangeStatus == null ? null : giftExchangeStatus.trim();
    }

    public Integer getSetStatus() {
        return setStatus;
    }

    public void setSetStatus(Integer setStatus) {
        this.setStatus = setStatus;
    }

    public Integer getLimitStatus() {
        return limitStatus;
    }

    public void setLimitStatus(Integer limitStatus) {
        this.limitStatus = limitStatus;
    }

    public Integer getLimitQuota() {
        return limitQuota;
    }

    public void setLimitQuota(Integer limitQuota) {
        this.limitQuota = limitQuota;
    }

    public Integer getLimitBuyQuota() {
        return limitBuyQuota;
    }

    public void setLimitBuyQuota(Integer limitBuyQuota) {
        this.limitBuyQuota = limitBuyQuota;
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