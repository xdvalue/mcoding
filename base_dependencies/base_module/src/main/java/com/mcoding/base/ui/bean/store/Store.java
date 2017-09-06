package com.mcoding.base.ui.bean.store;

import java.io.Serializable;
import java.math.BigDecimal;

public class Store implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String storeName;

    private Boolean storeAuth;

    private Boolean nameAuth;

    private Integer gradeId;

    private Integer memberId;

    private String memberName;

    private String sellerName;

    private String storeOwnerCard;

    private Integer scId;

    private String storeCompanyName;

    private Integer areaId;

    private String areaInfo;

    private String storeAddress;

    private String storeZip;

    private String storeTel;

    private String storeImage;

    private String storeImage1;

    private Boolean storeState;

    private String storeCloseInfo;

    private Integer storeSort;

    private String storeTime;

    private String storeEndTime;

    private String storeLabel;

    private String storeBanner;

    private String storeKeywords;

    private String storeDescription;

    private String storeQq;

    private String storeWw;

    private String description;

    private String storeZy;

    private String storeDomain;

    private Boolean storeDomainTimes;

    private Boolean storeRecommend;

    private String storeTheme;

    private Integer storeCredit;

    private Float praiseRate;

    private Float storeDesccredit;

    private Float storeServicecredit;

    private Float storeDeliverycredit;

    private Integer storeCollect;

    private String storeSlide;

    private String storeSlideUrl;

    private String storeStamp;

    private String storePrintdesc;

    private Integer storeSales;

    private String storePresales;

    private String storeAftersales;

    private String storeWorkingtime;

    private BigDecimal storeFreePrice;

    private Byte storeStorageAlarm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public Boolean getStoreAuth() {
        return storeAuth;
    }

    public void setStoreAuth(Boolean storeAuth) {
        this.storeAuth = storeAuth;
    }

    public Boolean getNameAuth() {
        return nameAuth;
    }

    public void setNameAuth(Boolean nameAuth) {
        this.nameAuth = nameAuth;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName == null ? null : sellerName.trim();
    }

    public String getStoreOwnerCard() {
        return storeOwnerCard;
    }

    public void setStoreOwnerCard(String storeOwnerCard) {
        this.storeOwnerCard = storeOwnerCard == null ? null : storeOwnerCard.trim();
    }

    public Integer getScId() {
        return scId;
    }

    public void setScId(Integer scId) {
        this.scId = scId;
    }

    public String getStoreCompanyName() {
        return storeCompanyName;
    }

    public void setStoreCompanyName(String storeCompanyName) {
        this.storeCompanyName = storeCompanyName == null ? null : storeCompanyName.trim();
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaInfo() {
        return areaInfo;
    }

    public void setAreaInfo(String areaInfo) {
        this.areaInfo = areaInfo == null ? null : areaInfo.trim();
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress == null ? null : storeAddress.trim();
    }

    public String getStoreZip() {
        return storeZip;
    }

    public void setStoreZip(String storeZip) {
        this.storeZip = storeZip == null ? null : storeZip.trim();
    }

    public String getStoreTel() {
        return storeTel;
    }

    public void setStoreTel(String storeTel) {
        this.storeTel = storeTel == null ? null : storeTel.trim();
    }

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage == null ? null : storeImage.trim();
    }

    public String getStoreImage1() {
        return storeImage1;
    }

    public void setStoreImage1(String storeImage1) {
        this.storeImage1 = storeImage1 == null ? null : storeImage1.trim();
    }

    public Boolean getStoreState() {
        return storeState;
    }

    public void setStoreState(Boolean storeState) {
        this.storeState = storeState;
    }

    public String getStoreCloseInfo() {
        return storeCloseInfo;
    }

    public void setStoreCloseInfo(String storeCloseInfo) {
        this.storeCloseInfo = storeCloseInfo == null ? null : storeCloseInfo.trim();
    }

    public Integer getStoreSort() {
        return storeSort;
    }

    public void setStoreSort(Integer storeSort) {
        this.storeSort = storeSort;
    }

    public String getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(String storeTime) {
        this.storeTime = storeTime == null ? null : storeTime.trim();
    }

    public String getStoreEndTime() {
        return storeEndTime;
    }

    public void setStoreEndTime(String storeEndTime) {
        this.storeEndTime = storeEndTime == null ? null : storeEndTime.trim();
    }

    public String getStoreLabel() {
        return storeLabel;
    }

    public void setStoreLabel(String storeLabel) {
        this.storeLabel = storeLabel == null ? null : storeLabel.trim();
    }

    public String getStoreBanner() {
        return storeBanner;
    }

    public void setStoreBanner(String storeBanner) {
        this.storeBanner = storeBanner == null ? null : storeBanner.trim();
    }

    public String getStoreKeywords() {
        return storeKeywords;
    }

    public void setStoreKeywords(String storeKeywords) {
        this.storeKeywords = storeKeywords == null ? null : storeKeywords.trim();
    }

    public String getStoreDescription() {
        return storeDescription;
    }

    public void setStoreDescription(String storeDescription) {
        this.storeDescription = storeDescription == null ? null : storeDescription.trim();
    }

    public String getStoreQq() {
        return storeQq;
    }

    public void setStoreQq(String storeQq) {
        this.storeQq = storeQq == null ? null : storeQq.trim();
    }

    public String getStoreWw() {
        return storeWw;
    }

    public void setStoreWw(String storeWw) {
        this.storeWw = storeWw == null ? null : storeWw.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getStoreZy() {
        return storeZy;
    }

    public void setStoreZy(String storeZy) {
        this.storeZy = storeZy == null ? null : storeZy.trim();
    }

    public String getStoreDomain() {
        return storeDomain;
    }

    public void setStoreDomain(String storeDomain) {
        this.storeDomain = storeDomain == null ? null : storeDomain.trim();
    }

    public Boolean getStoreDomainTimes() {
        return storeDomainTimes;
    }

    public void setStoreDomainTimes(Boolean storeDomainTimes) {
        this.storeDomainTimes = storeDomainTimes;
    }

    public Boolean getStoreRecommend() {
        return storeRecommend;
    }

    public void setStoreRecommend(Boolean storeRecommend) {
        this.storeRecommend = storeRecommend;
    }

    public String getStoreTheme() {
        return storeTheme;
    }

    public void setStoreTheme(String storeTheme) {
        this.storeTheme = storeTheme == null ? null : storeTheme.trim();
    }

    public Integer getStoreCredit() {
        return storeCredit;
    }

    public void setStoreCredit(Integer storeCredit) {
        this.storeCredit = storeCredit;
    }

    public Float getPraiseRate() {
        return praiseRate;
    }

    public void setPraiseRate(Float praiseRate) {
        this.praiseRate = praiseRate;
    }

    public Float getStoreDesccredit() {
        return storeDesccredit;
    }

    public void setStoreDesccredit(Float storeDesccredit) {
        this.storeDesccredit = storeDesccredit;
    }

    public Float getStoreServicecredit() {
        return storeServicecredit;
    }

    public void setStoreServicecredit(Float storeServicecredit) {
        this.storeServicecredit = storeServicecredit;
    }

    public Float getStoreDeliverycredit() {
        return storeDeliverycredit;
    }

    public void setStoreDeliverycredit(Float storeDeliverycredit) {
        this.storeDeliverycredit = storeDeliverycredit;
    }

    public Integer getStoreCollect() {
        return storeCollect;
    }

    public void setStoreCollect(Integer storeCollect) {
        this.storeCollect = storeCollect;
    }

    public String getStoreSlide() {
        return storeSlide;
    }

    public void setStoreSlide(String storeSlide) {
        this.storeSlide = storeSlide == null ? null : storeSlide.trim();
    }

    public String getStoreSlideUrl() {
        return storeSlideUrl;
    }

    public void setStoreSlideUrl(String storeSlideUrl) {
        this.storeSlideUrl = storeSlideUrl == null ? null : storeSlideUrl.trim();
    }

    public String getStoreStamp() {
        return storeStamp;
    }

    public void setStoreStamp(String storeStamp) {
        this.storeStamp = storeStamp == null ? null : storeStamp.trim();
    }

    public String getStorePrintdesc() {
        return storePrintdesc;
    }

    public void setStorePrintdesc(String storePrintdesc) {
        this.storePrintdesc = storePrintdesc == null ? null : storePrintdesc.trim();
    }

    public Integer getStoreSales() {
        return storeSales;
    }

    public void setStoreSales(Integer storeSales) {
        this.storeSales = storeSales;
    }

    public String getStorePresales() {
        return storePresales;
    }

    public void setStorePresales(String storePresales) {
        this.storePresales = storePresales == null ? null : storePresales.trim();
    }

    public String getStoreAftersales() {
        return storeAftersales;
    }

    public void setStoreAftersales(String storeAftersales) {
        this.storeAftersales = storeAftersales == null ? null : storeAftersales.trim();
    }

    public String getStoreWorkingtime() {
        return storeWorkingtime;
    }

    public void setStoreWorkingtime(String storeWorkingtime) {
        this.storeWorkingtime = storeWorkingtime == null ? null : storeWorkingtime.trim();
    }

    public BigDecimal getStoreFreePrice() {
        return storeFreePrice;
    }

    public void setStoreFreePrice(BigDecimal storeFreePrice) {
        this.storeFreePrice = storeFreePrice;
    }

    public Byte getStoreStorageAlarm() {
        return storeStorageAlarm;
    }

    public void setStoreStorageAlarm(Byte storeStorageAlarm) {
        this.storeStorageAlarm = storeStorageAlarm;
    }
}