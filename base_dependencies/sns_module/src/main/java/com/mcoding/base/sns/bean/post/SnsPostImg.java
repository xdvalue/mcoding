package com.mcoding.base.sns.bean.post;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class SnsPostImg implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("图片Id")
	private Integer id;

	@ApiModelProperty("帖子Id")
	private Integer postId;

	@ApiModelProperty(value = "图片url", required = true)
	private String imgUrl;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl == null ? null : imgUrl.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}