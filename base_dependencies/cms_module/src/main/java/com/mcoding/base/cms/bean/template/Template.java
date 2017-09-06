package com.mcoding.base.cms.bean.template;

import java.io.Serializable;
import java.util.Date;

/**
 * 模板
 * 
 * @author acer
 * 
 */
public class Template implements Serializable {

	private static final long serialVersionUID = 5937363476952339468L;

	private Integer id;

	private String templateName;

	private String templateUrl;

	private Integer status;

	private Integer operater;

	private String description;

	private Date createTime;

    private Date updateTime;

    private Integer storeId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName == null ? null : templateName.trim();
	}

	public String getTemplateUrl() {
		return templateUrl;
	}

	public void setTemplateUrl(String templateUrl) {
		this.templateUrl = templateUrl == null ? null : templateUrl.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOperater() {
		return operater;
	}

	public void setOperater(Integer operater) {
		this.operater = operater;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
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

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
}