package com.mcoding.base.ui.bean.member;

import java.io.Serializable;
import java.util.Date;

public interface IMember extends Serializable {
	
	public Integer getId();

    public void setId(Integer id);

    public String getName() ;
    
    public void setName(String name) ;

    public String getTrueName() ;

    public void setTrueName(String trueName) ;

    public String getHeadImgUrl() ;

    public void setHeadImgUrl(String headImgUrl) ;

    public String getLoginName() ;

    public void setLoginName(String loginName) ;

    public String getPassword() ;

    public void setPassword(String password) ;

    public String getPayPassword() ;

    public void setPayPassword(String payPassword);

    public Integer getIsEnable() ;

    public void setIsEnable(Integer isEnable);

    public Integer getLoginNum() ;

    public void setLoginNum(Integer loginNum) ;

    public Date getLastLoginTime() ;

    public void setLastLoginTime(Date lastLoginTime);

    public Integer getWeiboId() ;
    
    public void setWeiboId(Integer weiboId) ;

    public Integer getQqId() ;

    public void setQqId(Integer qqId) ;

    public Integer getWechatId() ;

    public void setWechatId(Integer wechatId) ;

    public Date getCreateTime() ;

    public void setCreateTime(Date createTime);

    public Date getUpdateTime() ;

    public void setUpdateTime(Date updateTime) ;
	
	public IMemberExtInfo getMemberExtInfo() ;

	public void setMemberExtInfo(IMemberExtInfo memberExtInfo) ;
	
}
