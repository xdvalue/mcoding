package com.mcoding.base.auth.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mcoding.base.utils.json.CustomDateSerializer;

public class RoleRight implements Serializable{
	private static final long serialVersionUID = -8984662738166914498L;

	private Integer roleId;

    private Integer menuId;

    private Integer operId;

    private Date createTime;
    
    private String menuName;  //非字段值
    
    private String menuCode;  //非字段值
    
    private String operName;  //非字段值
    
    private String operCode;  //非字段值

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getOperId() {
        return operId;
    }

    public void setOperId(Integer operId) {
        this.operId = operId;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getOperCode() {
		return operCode;
	}

	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}
}