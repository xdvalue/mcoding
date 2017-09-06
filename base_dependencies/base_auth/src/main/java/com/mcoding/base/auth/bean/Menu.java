package com.mcoding.base.auth.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mcoding.base.utils.json.CustomDateSerializer;

public class Menu implements Serializable{
	private static final long serialVersionUID = 6905730756693232259L;
	
//	##########################
	 //以下非字段值，仅仅提供给前台js使用
    
    public Integer getId() {
		return this.menuId;
	}

	public String getText() {
		return this.menuName;
	}
    
//	#######################
	

	private Integer menuId;

    private Integer parentId;

    private String menuType;

    private String menuName;

    private String menuCode;

    private String menuURL;

    private Integer sortNo;

    private Date createTime;

    private Date updateTime;

    private String menuLogo;
    
    private List<Operator> operators;
    
    private List<Menu> children;
    
    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType == null ? null : menuType.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    public String getMenuURL() {
        return menuURL;
    }

    public void setMenuURL(String menuURL) {
        this.menuURL = menuURL == null ? null : menuURL.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
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

    public String getMenuLogo() {
        return menuLogo;
    }

    public void setMenuLogo(String menuLogo) {
        this.menuLogo = menuLogo == null ? null : menuLogo.trim();
    }

	public List<Operator> getOperators() {
		return operators;
	}

	public void setOperators(List<Operator> operators) {
		this.operators = operators;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

}