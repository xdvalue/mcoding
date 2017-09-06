package com.mcoding.base.cms.bean.collect;

import java.io.Serializable;
import java.util.Date;

/**
 * 收藏
 * 
 * @author acer
 * 
 */
public class Collect implements Serializable {

	private static final long serialVersionUID = -8439115621874535598L;

	private Integer id;

	private Integer memberId;

	private Integer articleId;

	private Integer storeId;

    private Date createTime;

    private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Date getCreateTime() {
		return createTime;
	}

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}