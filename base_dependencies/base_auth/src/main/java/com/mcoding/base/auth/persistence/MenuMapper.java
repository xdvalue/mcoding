package com.mcoding.base.auth.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mcoding.base.auth.bean.Menu;
import com.mcoding.base.auth.bean.MenuExample;

public interface MenuMapper {
    int countByExample(MenuExample example);

    int deleteByExample(MenuExample example);

    int deleteByPrimaryKey(Integer menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    List<Menu> selectByExample(MenuExample example);

    Menu selectByPrimaryKey(Integer menuId);

    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    
    List<Menu> selectByExampleByPage(MenuExample example);
}