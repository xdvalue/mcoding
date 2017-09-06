package com.mcoding.base.auth.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mcoding.base.utils.json.CustomDateSerializer;

public class Operator implements Serializable{
	private static final long serialVersionUID = -8090573819344697217L;

	private Integer operId;

    private String operName;

    private String operCode;

    private String operURL;

    private Integer menuId;

    private Date createTime;

    private Date updateTime;
    
    private String menuName; //非字段值,栏目名称，仅仅用于前台显示
    
    private boolean authorized; //非字段值,授权信息，仅仅用于前台显示

    public Integer getOperId() {
        return operId;
    }

    public void setOperId(Integer operId) {
        this.operId = operId;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName == null ? null : operName.trim();
    }

    public String getOperCode() {
        return operCode;
    }

    public void setOperCode(String operCode) {
        this.operCode = operCode == null ? null : operCode.trim();
    }

    public String getOperURL() {
        return operURL;
    }

    public void setOperURL(String operURL) {
        this.operURL = operURL == null ? null : operURL.trim();
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
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

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public boolean isAuthorized() {
		return authorized;
	}

	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}
}