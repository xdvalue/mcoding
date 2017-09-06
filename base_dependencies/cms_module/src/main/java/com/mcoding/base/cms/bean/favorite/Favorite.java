package com.mcoding.base.cms.bean.favorite;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 赞或者衰
 * 
 * @author acer
 * 
 */
public class Favorite implements Serializable {

	private static final long serialVersionUID = -8345647478419049932L;

	private Integer id;

	private Integer memberId;

	private Date createTime;

	private Integer type;

	private Integer status;

    private Date updateTime;

    private Integer articleId;

    private Integer storeId;

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
}