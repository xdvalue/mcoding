package com.mcoding.base.sns.persistence.post;

import com.mcoding.base.sns.bean.post.SnsPost;
import com.mcoding.base.sns.bean.post.SnsPostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SnsPostMapper {
    int countByExample(SnsPostExample example);

    int deleteByExample(SnsPostExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SnsPost record);

    int insertSelective(SnsPost record);

    List<SnsPost> selectByExampleWithBLOBs(SnsPostExample example);

    List<SnsPost> selectByExample(SnsPostExample example);

    SnsPost selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SnsPost record, @Param("example") SnsPostExample example);

    int updateByExampleWithBLOBs(@Param("record") SnsPost record, @Param("example") SnsPostExample example);

    int updateByExample(@Param("record") SnsPost record, @Param("example") SnsPostExample example);

    int updateByPrimaryKeySelective(SnsPost record);

    int updateByPrimaryKeyWithBLOBs(SnsPost record);

    int updateByPrimaryKey(SnsPost record);
    
    List<SnsPost> selectByExampleByPage(SnsPostExample example);

	void addOrRemoveLike(@Param("postId") int postId, @Param("isLike") boolean isLike);

	void addOrRemoveDislike(@Param("postId") int postId, @Param("isLike") boolean isDisLike);

//	void addOrRemoveComment(@Param("postId") int postId, @Param("isAdd") boolean isAdd);

	void updateReplyTimeByPostId(@Param("postId") int postId);
	
	void updateOrderNumAsMax(@Param("postId") int postId, @Param("storeId") int storeId);
	
	void updateOrderNumInHomeAsMax(@Param("postId") int postId, @Param("storeId") int storeId);
	
}