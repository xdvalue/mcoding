package com.mcoding.base.member.bean.member;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mcoding.base.member.bean.level.MemberLevel;
import com.mcoding.base.member.utils.json.MemberExtInfoDeserializer;
import com.mcoding.base.member.utils.json.MemberExtInfoSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="会员")
public class Member implements Serializable {

	//###################以下是非自动生成的###############################//
	
	/**会员来源 微信**/
	public static final Integer SOURCE_WEIXIN = 1;
	/**会员来源 微博**/
	public static final Integer SOURCE_WEIBO = 2;
	/**会员来源 QQ**/
	public static final Integer SOURCE_QQ = 3;
	
	/**会员类型 马甲**/
	public static final Integer TYPE_MARK = 3;

	@ApiModelProperty(name = "扩展信息")
	private MemberExtInfo memberExtInfo;

	@ApiModelProperty(name = "微信会员信息")
	private WxMember wxMember;
	
	@ApiModelProperty(name = "会员级别信息")
	private List<MemberLevel> levelList;
	
	@JsonSerialize(using = MemberExtInfoSerializer.class)
	public MemberExtInfo getMemberExtInfo() {
		return memberExtInfo;
	}

	@JsonDeserialize(using = MemberExtInfoDeserializer.class)
	public void setMemberExtInfo(MemberExtInfo memberExtInfo) {
		this.memberExtInfo = memberExtInfo;
	}

	public List<MemberLevel> getLevelList() {
		return levelList;
	}

	public void setLevelList(List<MemberLevel> levelList) {
		this.levelList = levelList;
	}

	public WxMember getWxMember() {
		return wxMember;
	}

	public void setWxMember(WxMember wxMember) {
		this.wxMember = wxMember;
	}
	
	//###################以下是非自动生成的， 结束###############################//
	
	private Integer id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("真实名称")
    private String trueName;

    @ApiModelProperty("会员类型，1普通会员，2认证会员")
    private Integer type;

    @ApiModelProperty("头像")
    private String headImgUrl;

    @ApiModelProperty("登录账号")
    private String loginName;

    @ApiModelProperty("登录密码")
    private String password;

    @ApiModelProperty("支付密码")
    private String payPassword;

    @ApiModelProperty("手机号码")
    private String mobilephone;

    @ApiModelProperty("性别 ，1是男，2是女")
    private Integer gender;

    @ApiModelProperty("生日")
    private String birthday;

    @ApiModelProperty("省")
    private String province;

    @ApiModelProperty("市")
    private String city;

    @ApiModelProperty("区")
    private String district;

    @ApiModelProperty("具体地址")
    private String address;

    @ApiModelProperty("与来源关联的id，如果是来源是公众号，就是公众号的id，")
    private Integer sourceId;

    @ApiModelProperty("会员的来源，1微信扫码会员，2qq会员，3 微博会员")
    private Integer source;

    private String remark;

    @ApiModelProperty("启用1,禁用0")
    private Integer isEnable;

    @ApiModelProperty("录登次数")
    private Integer loginNum;

    @ApiModelProperty("最后一次登录时间")
    private Date lastLoginTime;

    @ApiModelProperty("后台用户id")
    private Integer userId;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl == null ? null : headImgUrl.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword == null ? null : payPassword.trim();
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone == null ? null : mobilephone.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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