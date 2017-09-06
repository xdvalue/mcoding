package com.mcoding.base.sns.persistence.post;

import com.mcoding.base.sns.bean.post.SnsPostImg;
import com.mcoding.base.sns.bean.post.SnsPostImgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SnsPostImgMapper {
    int countByExample(SnsPostImgExample example);

    int deleteByExample(SnsPostImgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SnsPostImg record);

    int insertSelective(SnsPostImg record);

    List<SnsPostImg> selectByExample(SnsPostImgExample example);

    SnsPostImg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SnsPostImg record, @Param("example") SnsPostImgExample example);

    int updateByExample(@Param("record") SnsPostImg record, @Param("example") SnsPostImgExample example);

    int updateByPrimaryKeySelective(SnsPostImg record);

    int updateByPrimaryKey(SnsPostImg record);
    
    List<SnsPostImg> selectByPostId(int postId);
}