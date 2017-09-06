package com.mcoding.base.cms.bean.comment;

import java.util.List;

/**
 * 返回给前台的数据结构
 * 
 * @author acer
 * 
 */
public class CommentData {

	private Comment comment;

	private List<CommentData> commentList;

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public List<CommentData> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentData> commentList) {
		this.commentList = commentList;
	}
}
