package com.mcoding.base.sns.persistence.comment;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mcoding.base.sns.bean.comment.SnsComment;
import com.mcoding.base.sns.bean.comment.SnsCommentExample;

public interface SnsCommentMapper {
	int countByExample(SnsCommentExample example);

	int deleteByExample(SnsCommentExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(SnsComment record);

	int insertSelective(SnsComment record);

	List<SnsComment> selectByExample(SnsCommentExample example);

	SnsComment selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") SnsComment record, @Param("example") SnsCommentExample example);

	int updateByExample(@Param("record") SnsComment record, @Param("example") SnsCommentExample example);

	int updateByPrimaryKeySelective(SnsComment record);

	int updateByPrimaryKey(SnsComment record);

	List<SnsComment> selectByExampleByPage(SnsCommentExample example);

	List<SnsComment> selectByParentId(int parentId);

	void addOrRemoveLike(@Param("commentId") int commentId, @Param("isLike") boolean isLike);

	void addOrRemoveComment(@Param("commentId") int commentId, @Param("isAdd") boolean isAdd);

}