package com.mcoding.base.sns.persistence.post;

import org.apache.ibatis.annotations.Param;

import com.mcoding.base.core.IMapper;
import com.mcoding.base.sns.bean.post.SnsPostExtInfo;
import com.mcoding.base.sns.bean.post.SnsPostExtInfoExample;

public interface SnsPostExtInfoMapper extends IMapper<SnsPostExtInfo, SnsPostExtInfoExample> {
	public void addCommentNumByPostId(int postId);
	
	public void addViewNumByPostId(@Param("postId")int postId, @Param("viewNum") int viewNum);
	
	public void addLikeNumByPostId(@Param("postId") int postId, @Param("isLike") boolean isLike);
	
	public void addDislikeNumByPostId(@Param("postId") int postId, @Param("isLike") boolean isLike);
}