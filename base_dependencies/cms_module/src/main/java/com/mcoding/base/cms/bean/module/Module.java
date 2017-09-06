package com.mcoding.base.cms.bean.module;

import java.io.Serializable;
import java.util.Date;

/**
 * 模块
 * 
 * @author acer
 * 
 */
public class Module implements Serializable {

	private static final long serialVersionUID = -4692387992760257753L;

	private Integer id;

	private String name;

	private String code;

	private Integer status;

	private String style;

	private String link;

	private String event;

	private Integer seqNum;

	private Integer storeId;

	private Integer parentId;

	private Date createTime;

    private Date updateTime;

    private String imgUrl;

    private String description;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style == null ? null : style.trim();
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link == null ? null : link.trim();
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event == null ? null : event.trim();
	}

	public Integer getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}