package com.mcoding.base.sns.web.controller.statistical;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.sns.service.statistical.SnsStatisticalService;
import com.mcoding.base.ui.bean.store.Store;

import io.swagger.annotations.ApiParam;

@Controller("snsStatisticalController")
@RequestMapping("snsStatistical")
public class StatisticalController {
	
	@Resource
	private SnsStatisticalService snsStatisticalService;
	
	@RequestMapping("service/toNewUserByDayView")
	public ModelAndView toNewUserByDayView(){
		return new ModelAndView("sns/snsStatistical/toNewUserByDayView");
	}
	
	@RequestMapping("service/toLoginedUserByDayView")
	public ModelAndView toLoginedUserByDayView(){
		return new ModelAndView("sns/snsStatistical/toLoginedUserByDayView");
	}
	
	@RequestMapping("service/toPostCountByDayView")
	public ModelAndView toPostCountByDayView(){
		return new ModelAndView("sns/snsStatistical/toPostCountByDayView");
	}
	
	@RequestMapping("service/toCommentCountForDayView")
	public ModelAndView toCommentCountForDayView(){
		return new ModelAndView("sns/snsStatistical/toCommentCountForDayView");
	}
	
	@RequestMapping("service/newUserByDay")
	@ResponseBody
	public PageView<HashMap<String, Object>> newUserByDay(
			@ApiParam(name = "分页索引", defaultValue = "0") @RequestParam(defaultValue = "0") String iDisplayStart,
			@ApiParam(name = "每页的数量", defaultValue = "10") @RequestParam(defaultValue = "10") String iDisplayLength,
			@ApiParam(name = "开始时间") Long startTime,
			@ApiParam(name = "结束时间") Long endTime, HttpSession session) {
		
		if (startTime!=null && endTime!=null && startTime > endTime) {
			throw new IllegalArgumentException("开始时间 大于 结束时间，请重新设置。");
		}
		
		Store store = (Store) session.getAttribute("store");
		
		Date startTimeDate = null;
		Date endTimeDate = null;
		if (startTime != null && startTime >= 0) {
			startTimeDate = new Date(startTime);
		}
		if (endTime != null && endTime >= 0) {
			endTimeDate = new Date(endTime);
		}
		return snsStatisticalService.newUserForDay(startTimeDate, endTimeDate, store.getId(), iDisplayStart, iDisplayLength);
	}
	
	@RequestMapping("service/loginedUserByDay")
	@ResponseBody
	public PageView<HashMap<String, Object>> loginedUserByDay(
			@ApiParam(name = "分页索引", defaultValue = "0") @RequestParam(defaultValue = "0") String iDisplayStart,
			@ApiParam(name = "每页的数量", defaultValue = "10") @RequestParam(defaultValue = "10") String iDisplayLength,
			@ApiParam(name = "开始时间") Long startTime,
			@ApiParam(name = "结束时间") Long endTime, HttpSession session) {
		
		if (startTime!=null && endTime!=null && startTime > endTime) {
			throw new IllegalArgumentException("开始时间 大于 结束时间，请重新设置。");
		}
		
		Store store = (Store) session.getAttribute("store");
		
		Date startTimeDate = null;
		Date endTimeDate = null;
		if (startTime != null && startTime >= 0) {
			startTimeDate = new Date(startTime);
		}
		if (endTime != null && endTime >= 0) {
			endTimeDate = new Date(endTime);
		}
		return snsStatisticalService.loginedUserForDay(startTimeDate, endTimeDate, store.getId(), iDisplayStart, iDisplayLength);
	}
	
	@RequestMapping("service/postCountByDay")
	@ResponseBody
	public PageView<HashMap<String, Object>> postCountByDay(
			@ApiParam(name = "分页索引", defaultValue = "0") @RequestParam(defaultValue = "0") String iDisplayStart,
			@ApiParam(name = "每页的数量", defaultValue = "10") @RequestParam(defaultValue = "10") String iDisplayLength,
			@ApiParam(name = "开始时间") Long startTime,
			@ApiParam(name = "结束时间") Long endTime, HttpSession session) {
		
		if (startTime!=null && endTime!=null && startTime > endTime) {
			throw new IllegalArgumentException("开始时间 大于 结束时间，请重新设置。");
		}
		
		Store store = (Store) session.getAttribute("store");
		
		Date startTimeDate = null;
		Date endTimeDate = null;
		if (startTime != null && startTime >= 0) {
			startTimeDate = new Date(startTime);
		}
		if (endTime != null && endTime >= 0) {
			endTimeDate = new Date(endTime);
		}
		return snsStatisticalService.postCountForDay(startTimeDate, endTimeDate, store.getId(), iDisplayStart, iDisplayLength);
	}
	
	@RequestMapping("service/commentCountForDay")
	@ResponseBody
	public PageView<HashMap<String, Object>> commentCountForDay(
			@ApiParam(name = "分页索引", defaultValue = "0") @RequestParam(defaultValue = "0") String iDisplayStart,
			@ApiParam(name = "每页的数量", defaultValue = "10") @RequestParam(defaultValue = "10") String iDisplayLength,
			@ApiParam(name = "开始时间") Long startTime,
			@ApiParam(name = "结束时间") Long endTime, HttpSession session) {
		
		if (startTime!=null && endTime!=null && startTime > endTime) {
			throw new IllegalArgumentException("开始时间 大于 结束时间，请重新设置。");
		}
		
		Store store = (Store) session.getAttribute("store");
		
		Date startTimeDate = null;
		Date endTimeDate = null;
		if (startTime != null && startTime >= 0) {
			startTimeDate = new Date(startTime);
		}
		if (endTime != null && endTime >= 0) {
			endTimeDate = new Date(endTime);
		}
		return snsStatisticalService.commentCountForDay(startTimeDate, endTimeDate, store.getId(), iDisplayStart, iDisplayLength);
	}
	
	@RequestMapping("service/postViewCount")
	@ResponseBody
	public JsonResult<Integer> postViewCount(HttpSession session) {
		
		Store store = (Store) session.getAttribute("store");
		int postViewCount = snsStatisticalService.postViewCount(store.getId());
		
		JsonResult<Integer> result = new JsonResult<>();
		result.setData(postViewCount);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}
}
