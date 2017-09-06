package com.mcoding.base.sns.service.statistical;

import java.util.Date;
import java.util.HashMap;

import com.mcoding.base.core.PageView;

/**
 * 报表统计的数据查询
 * @author hzy
 *
 */
public interface SnsStatisticalService {
	
	/**
	 * 每天新登录用户
	 * @param startTime
	 * @param endTime
	 * @param storeId
	 * @param iDisplayLength 
	 * @param iDisplayStart 
	 * @return
	 */
	public PageView<HashMap<String, Object>> newUserForDay(Date startTime, Date endTime, int storeId, String iDisplayStart, String iDisplayLength);
	
	/**
	 * 每天登录的用户数
	 * @param startTime
	 * @param endTime
	 * @param storeId
	 * @param iDisplayLength 
	 * @param iDisplayStart 
	 * @return
	 */
	public PageView<HashMap<String, Object>> loginedUserForDay(Date startTime, Date endTime, int storeId, String iDisplayStart, String iDisplayLength);
	
	/**
	 * 每天发帖量
	 * @param startTime
	 * @param endTime
	 * @param storeId
	 * @param iDisplayLength 
	 * @param iDisplayStart 
	 * @return
	 */
	public PageView<HashMap<String, Object>> postCountForDay(Date startTime, Date endTime, int storeId, String iDisplayStart, String iDisplayLength);
	
	/**
	 * 每天的评论数
	 * @param startTime
	 * @param endTime
	 * @param storeId
	 * @param iDisplayLength 
	 * @param iDisplayStart 
	 * @return
	 */
	public PageView<HashMap<String, Object>> commentCountForDay(Date startTime, Date endTime, int storeId, String iDisplayStart, String iDisplayLength);

	/**
	 * 帖子当前总浏览数
	 * @param id
	 * @return
	 */
	public int postViewCount(Integer storeId);

}
