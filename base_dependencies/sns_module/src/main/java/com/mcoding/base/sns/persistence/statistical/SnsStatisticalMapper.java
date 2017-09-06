package com.mcoding.base.sns.persistence.statistical;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mcoding.base.core.PageView;

public interface SnsStatisticalMapper {
	
	/**
	 * 每天新登录用户
	 * @param startTime
	 * @param endTime
	 * @param storeId
	 * @return
	 */
	public List<HashMap<String, Object>> newUserForDayByPage(
			@Param("pageView")PageView<HashMap<String, Object>> pageView, 
			@Param("startTime")Date startTime, 
			@Param("endTime")Date endTime, 
			@Param("storeId")int storeId);

	/**
	 * 每天登录的用户数
	 * @param startTime
	 * @param endTime
	 * @param storeId
	 * @return
	 */
	public List<HashMap<String, Object>> loginedUserForDayByPage(
			@Param("pageView")PageView<HashMap<String, Object>> pageView, 
			@Param("startTime")Date startTime,
			@Param("endTime")Date endTime, 
			@Param("storeId")int storeId);

	/**
	 * 每天发帖量
	 * @param startTime
	 * @param endTime
	 * @param storeId
	 * @return
	 */
	public List<HashMap<String, Object>> postCountForDayByPage(
			@Param("pageView")PageView<HashMap<String, Object>> pageView,
            @Param("startTime")Date startTime, 
            @Param("endTime")Date endTime, 
            @Param("storeId")int storeId);

	/**
	 * 每天的评论数
	 * @param startTime
	 * @param endTime
	 * @param storeId
	 * @return
	 */
	public List<HashMap<String, Object>> commentCountForDayByPage(
			@Param("pageView")PageView<HashMap<String, Object>> pageView, 
			@Param("startTime")Date startTime, 
			@Param("endTime")Date endTime, 
			@Param("storeId")int storeId);

	/**
	 * 统计帖子的总浏览量
	 * @param storeId
	 * @return
	 */
	public int postViewCount(@Param("storeId")int storeId);

}
