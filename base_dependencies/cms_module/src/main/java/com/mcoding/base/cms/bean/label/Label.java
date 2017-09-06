package com.mcoding.base.cms.bean.label;

import java.io.Serializable;
import java.util.Date;

/**
 * 标签
 * 
 * @author acer
 * 
 */
public class Label implements Serializable {

	private static final long serialVersionUID = -1585977807180524115L;

	private Integer id;

	private Integer type;

	private String mark;

	private Integer hit;

	private Integer seqNum;

	private Date createTime;

    private Date updateTime;

    private Integer storeId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark == null ? null : mark.trim();
	}

	public Integer getHit() {
		return hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}

	public Integer getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
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