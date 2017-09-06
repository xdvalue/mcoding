package com.mcoding.base.cms.bean.comment;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论
 * 
 * @author acer
 * 
 */
public class Comment implements Serializable {

	private static final long serialVersionUID = 2003694872884448793L;

	private Integer id;

	private Integer articleId;

	private String openid;

	private Integer memberId;

	private String comment;

	private Date createTime;

    private Integer operType;

    private String nickname;

    private Integer storeId;

    private Integer commentId;

	private String headimgurl;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid == null ? null : openid.trim();
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment == null ? null : comment.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

    public Integer getOperType() {
        return operType;
    }

	public void setOperType(Integer operType) {
		this.operType = operType;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl == null ? null : headimgurl.trim();
	}
}