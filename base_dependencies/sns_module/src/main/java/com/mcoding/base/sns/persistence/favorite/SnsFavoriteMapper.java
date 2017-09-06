package com.mcoding.base.sns.persistence.favorite;

import com.mcoding.base.sns.bean.favorite.SnsFavorite;
import com.mcoding.base.sns.bean.favorite.SnsFavoriteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SnsFavoriteMapper {
	int countByExample(SnsFavoriteExample example);

	int deleteByExample(SnsFavoriteExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(SnsFavorite record);

	int insertSelective(SnsFavorite record);

	List<SnsFavorite> selectByExample(SnsFavoriteExample example);

	SnsFavorite selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") SnsFavorite record, @Param("example") SnsFavoriteExample example);

	int updateByExample(@Param("record") SnsFavorite record, @Param("example") SnsFavoriteExample example);

	int updateByPrimaryKeySelective(SnsFavorite record);

	int updateByPrimaryKey(SnsFavorite record);

	List<SnsFavorite> selectByExampleByPage(SnsFavoriteExample example);
}