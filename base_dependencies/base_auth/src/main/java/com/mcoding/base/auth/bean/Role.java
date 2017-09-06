package com.mcoding.base.auth.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.security.access.ConfigAttribute;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mcoding.base.utils.json.CustomDateSerializer;

public class Role implements Serializable, ConfigAttribute{
	private static final long serialVersionUID = 3319926134353066546L;
	
	private Integer roleId;

    private String roleName;

    private Integer roleLevel;

    private String roleCode;

    private Integer createUserId;

    private Date createTime;

    private Date updateTime;
    
    private String selected; //非字段,是否已经选择,提供给前台使用
    
    private String nickName; //非字段,是否已经选择,提供给前台使用
    
    public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(Integer roleId, String roleName, Integer roleLevel, String roleCode) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleLevel = roleLevel;
		this.roleCode = roleCode;
	}

	public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String getAttribute() {
		return this.roleId.toString();
	}
}