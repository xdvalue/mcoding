package com.mcoding.comp.wechat.member.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="微信会员信息")
public class WxMember implements Serializable {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("关联的会员id")
    private Integer memberId;

    @ApiModelProperty("open_id")
    private String wxOpenid;

    @ApiModelProperty("称昵")
    private String wxNickname;

    @ApiModelProperty("用户的性别，值为1时是男性，值为2时是女性，值为0时是未知")
    private Integer wxSex;

    @ApiModelProperty("用户所在城市")
    private String wxCity;

    @ApiModelProperty("用户所在国家")
    private String wxCountry;

    @ApiModelProperty("用户所在省份")
    private String wxProvince;

    @ApiModelProperty("用户的语言，简体中文为zh_CN")
    private String wxLanguage;

    @ApiModelProperty("用户头像")
    private String wxHeadimgurl;

    @ApiModelProperty("是否关注公众号，1已关注，0未关注")
    private Integer wxSubscribe;

    @ApiModelProperty("用户关注时间")
    private Date wxSubscribeTime;

    @ApiModelProperty("管理多个公众号时，作为唯一吗")
    private String wxUnionid;

    @ApiModelProperty("公众号运营者对粉丝的备注")
    private String wxRemark;

    @ApiModelProperty("用户所在的分组ID")
    private Integer wxGroupid;

    @ApiModelProperty("第一次关注的时间")
    private Date wxFirstSubscribeTime;

    @ApiModelProperty("第一次关注的key")
    private String wxSubscribeKey;

    private String wxAccountOriginId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid == null ? null : wxOpenid.trim();
    }

    public String getWxNickname() {
        return wxNickname;
    }

    public void setWxNickname(String wxNickname) {
        this.wxNickname = wxNickname == null ? null : wxNickname.trim();
    }

    public Integer getWxSex() {
        return wxSex;
    }

    public void setWxSex(Integer wxSex) {
        this.wxSex = wxSex;
    }

    public String getWxCity() {
        return wxCity;
    }

    public void setWxCity(String wxCity) {
        this.wxCity = wxCity == null ? null : wxCity.trim();
    }

    public String getWxCountry() {
        return wxCountry;
    }

    public void setWxCountry(String wxCountry) {
        this.wxCountry = wxCountry == null ? null : wxCountry.trim();
    }

    public String getWxProvince() {
        return wxProvince;
    }

    public void setWxProvince(String wxProvince) {
        this.wxProvince = wxProvince == null ? null : wxProvince.trim();
    }

    public String getWxLanguage() {
        return wxLanguage;
    }

    public void setWxLanguage(String wxLanguage) {
        this.wxLanguage = wxLanguage == null ? null : wxLanguage.trim();
    }

    public String getWxHeadimgurl() {
        return wxHeadimgurl;
    }

    public void setWxHeadimgurl(String wxHeadimgurl) {
        this.wxHeadimgurl = wxHeadimgurl == null ? null : wxHeadimgurl.trim();
    }

    public Integer getWxSubscribe() {
        return wxSubscribe;
    }

    public void setWxSubscribe(Integer wxSubscribe) {
        this.wxSubscribe = wxSubscribe;
    }

    public Date getWxSubscribeTime() {
        return wxSubscribeTime;
    }

    public void setWxSubscribeTime(Date wxSubscribeTime) {
        this.wxSubscribeTime = wxSubscribeTime;
    }

    public String getWxUnionid() {
        return wxUnionid;
    }

    public void setWxUnionid(String wxUnionid) {
        this.wxUnionid = wxUnionid == null ? null : wxUnionid.trim();
    }

    public String getWxRemark() {
        return wxRemark;
    }

    public void setWxRemark(String wxRemark) {
        this.wxRemark = wxRemark == null ? null : wxRemark.trim();
    }

    public Integer getWxGroupid() {
        return wxGroupid;
    }

    public void setWxGroupid(Integer wxGroupid) {
        this.wxGroupid = wxGroupid;
    }

    public Date getWxFirstSubscribeTime() {
        return wxFirstSubscribeTime;
    }

    public void setWxFirstSubscribeTime(Date wxFirstSubscribeTime) {
        this.wxFirstSubscribeTime = wxFirstSubscribeTime;
    }

    public String getWxSubscribeKey() {
        return wxSubscribeKey;
    }

    public void setWxSubscribeKey(String wxSubscribeKey) {
        this.wxSubscribeKey = wxSubscribeKey == null ? null : wxSubscribeKey.trim();
    }

    public String getWxAccountOriginId() {
        return wxAccountOriginId;
    }

    public void setWxAccountOriginId(String wxAccountOriginId) {
        this.wxAccountOriginId = wxAccountOriginId == null ? null : wxAccountOriginId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}