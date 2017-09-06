package com.mcoding.base.auth.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mcoding.base.utils.json.CustomDateSerializer;

public class UserRole implements Serializable{
	private static final long serialVersionUID = 3618418769951684473L;

	private Integer rightId;

    private Integer userId;

    private Integer roleId;

    private Date createTime;
    
    private String roleName; //非字段值(角色名称)
    
    private String roleCode; //非字段值(角色标示)
    
    private String loginName; //非字段值(用户登录名称)

    public Integer getRightId() {
        return rightId;
    }

    public void setRightId(Integer rightId) {
        this.rightId = rightId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
}