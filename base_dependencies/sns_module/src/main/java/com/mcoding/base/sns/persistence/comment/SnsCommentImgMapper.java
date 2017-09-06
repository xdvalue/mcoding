package com.mcoding.base.sns.persistence.comment;

import java.util.List;

import com.mcoding.base.core.IMapper;
import com.mcoding.base.sns.bean.comment.SnsCommentImg;
import com.mcoding.base.sns.bean.comment.SnsCommentImgExample;

public interface SnsCommentImgMapper extends IMapper<SnsCommentImg, SnsCommentImgExample> {
	
	List<SnsCommentImg> selectByCommentId(int commentId);
}