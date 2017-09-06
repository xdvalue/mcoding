package com.mcoding.base.sns.bean.message;

import com.mcoding.base.sns.bean.comment.SnsComment;
import com.mcoding.base.sns.bean.post.SnsPost;

public class CommentMsgInbox extends SnsMsgInbox {

	private static final long serialVersionUID = 1L;
	
	private SnsComment snsComment;
	private SnsPost snsPost;

	public SnsComment getSnsComment() {
		return snsComment;
	}

	public void setSnsComment(SnsComment snsComment) {
		this.snsComment = snsComment;
	}

	public SnsPost getSnsPost() {
		return snsPost;
	}

	public void setSnsPost(SnsPost snsPost) {
		this.snsPost = snsPost;
	}

}
