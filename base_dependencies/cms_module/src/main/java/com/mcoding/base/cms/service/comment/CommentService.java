package com.mcoding.base.cms.service.comment;

import java.util.List;

import com.mcoding.base.cms.bean.comment.Comment;
import com.mcoding.base.cms.bean.comment.CommentData;
import com.mcoding.base.cms.bean.comment.CommentExample;
import com.mcoding.base.core.BaseService;

/**
 * CommentService
 * 
 * @author acer
 * 
 */
public interface CommentService extends BaseService<Comment, CommentExample> {

	List<CommentData> getCommentsByArticleId(Integer articleId,
			Integer storeId, Integer memberId);
}
