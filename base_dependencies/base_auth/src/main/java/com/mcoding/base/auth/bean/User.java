package com.mcoding.base.auth.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mcoding.base.core.Constant;

public class User implements UserDetails, Serializable {
	
	private static final long serialVersionUID = 1L;
	/**后台用户**/
    public static final String USER_TYPE_ADMIN = "admin";
    /**前台用户**/
    public static final String USER_TYPE_FRONT = "front";
    /**过期时长 10天**/
    public static final long EXPIRE_TIME = 10 * 24 * 60 * 60 *1000;
    
    private Integer userId;

    private String loginName;

    private String nickName;

    private String password;

    private String gender;

    private String email;

    private String mobilePhone;

    private Date createTime;

    private Date updateTime;

    private Integer isDisabled;

    private String userType;

	private Collection<GrantedAuthority> authorities;

    private Date lastLoginTime;

    private Date expireTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
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

    public Integer getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(Integer isDisabled) {
        this.isDisabled = isDisabled;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getUsername() {
		return this.getLoginName();
	}

	@Override
	public boolean isAccountNonExpired() {
		if(this.expireTime != null){
			return expireTime.getTime() < System.currentTimeMillis();
		}
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return Constant.NO_INT.equals(this.getIsDisabled());
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return Constant.NO_INT.equals(this.getIsDisabled());
	}

	@Override
	public boolean isEnabled() {
		return Constant.NO_INT.equals(this.getIsDisabled());
	}
}