package com.mcoding.base.ui.plugin.schedule;

import java.text.ParseException;
import java.util.List;

import org.quartz.SchedulerException;

import com.mcoding.base.ui.bean.schedule.ScheduleJob;

public interface QuartzManager {
	
	/**
	 * 立即执行 job
	 * @param job
	 * @throws SchedulerException 
	 * @throws NoSuchMethodException 
	 * @throws ClassNotFoundException 
	 */
	public void runJobNow(ScheduleJob job) throws SchedulerException, ClassNotFoundException, NoSuchMethodException;
	
	/**
	 * 暂停 job
	 * @param job
	 * @throws SchedulerException 
	 */
	public void pauseJob(ScheduleJob job) throws SchedulerException;
	
	/**
	 * 恢复运行job
	 * @param job
	 * @throws SchedulerException 
	 * @throws NoSuchMethodException 
	 * @throws ClassNotFoundException 
	 * @throws ParseException 
	 */
	public void resumeJob(ScheduleJob job) throws SchedulerException, ClassNotFoundException, NoSuchMethodException, ParseException;
	
	/**
	 * 删除job
	 * @param job
	 * @throws SchedulerException 
	 */
	public void deleteJob(ScheduleJob job) throws SchedulerException;
	
	/**
	 * 更新 job 运行时间
	 * @param job
	 * @throws SchedulerException 
	 */
	public void updateJobCron(ScheduleJob job) throws SchedulerException;
	
	/**
	 * 获取所有job
	 * @return
	 * @throws SchedulerException 
	 */
	public List<ScheduleJob> getAllJob() throws SchedulerException;
	
	/**
	 * 获取所有在运行的job
	 * @return
	 * @throws SchedulerException 
	 */
	public List<ScheduleJob> getRunningJob() throws SchedulerException;
	
	/**
	 * 时间表达式是否正确
	 * @param cronStr
	 * @return
	 */
	public boolean isCronCorrect(String cronStr);

}
