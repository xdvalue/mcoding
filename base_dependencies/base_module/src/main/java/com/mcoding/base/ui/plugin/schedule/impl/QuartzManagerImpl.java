package com.mcoding.base.ui.plugin.schedule.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.ui.bean.schedule.ScheduleJob;
import com.mcoding.base.ui.bean.schedule.ScheduleJobExample;
import com.mcoding.base.ui.plugin.schedule.QuartzManager;
import com.mcoding.base.ui.service.schedule.ScheduleJobService;
import com.mcoding.base.ui.utils.common.Constant;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

public class QuartzManagerImpl implements QuartzManager{
	
	@Autowired
//	protected ScheduleJobMapper scheduleJobMapper;
	protected ScheduleJobService scheduleJobService;
	
	@Value("#{sysConfig['job.is.enable']}")
	protected String isJobEnalbe;
	
	private static StdScheduler scheduler = null;
	
//	@Resource(name="customSchedulerFactoryBean")
	public void setScheduler(StdScheduler stdScheduler){
		scheduler = stdScheduler;
	}
	
	public StdScheduler getScheduler(){
		return scheduler;
	}
	
	@Override
	public void runJobNow(ScheduleJob job) throws SchedulerException, ClassNotFoundException, NoSuchMethodException {
		
		try {
			ClassLoader classLoader = this.getClass().getClassLoader();
			Class<?> clazz = classLoader.loadClass(job.getJobClass());
			Object excutionBean = SpringContextHolder.getOneBean(clazz);
			Method method = clazz.getDeclaredMethod(job.getJobMethod());
			method.invoke(excutionBean);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void pauseJob(ScheduleJob job) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(job.getJobCode(), Scheduler.DEFAULT_GROUP);
		if (scheduler.checkExists(jobKey)) {
			scheduler.pauseJob(jobKey);
		}
		
		ScheduleJob tmp = new ScheduleJob();
		tmp.setId(job.getId());
		tmp.setJobState(ScheduleJob.JOB_STATE_PAUSE);
//		this.scheduleJobMapper.updateByPrimaryKeySelective(tmp);
		this.scheduleJobService.modifyObj(tmp);
	}

	@Override
	public void resumeJob(ScheduleJob job) throws SchedulerException, ClassNotFoundException, NoSuchMethodException, ParseException {
		if (!this.checkSwitch()) {
			throw new CommonException("本应用，没有开启定时任务功能，请在 conf.properties文件中配置开启");
		}
		
		JobKey jobKey = JobKey.jobKey(job.getJobCode(), Scheduler.DEFAULT_GROUP);
		if (scheduler.checkExists(jobKey)) {
			scheduler.resumeJob(jobKey);
		}else {
			this.addJob(job);
		}
		
		ScheduleJob tmp = new ScheduleJob();
		tmp.setId(job.getId());
		tmp.setJobState(ScheduleJob.JOB_STATE_NORMAL);
//		this.scheduleJobMapper.updateByPrimaryKeySelective(tmp);
		this.scheduleJobService.modifyObj(tmp);
		
	}

	@Override
	public void deleteJob(ScheduleJob job) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(job.getJobCode(), Scheduler.DEFAULT_GROUP);
		scheduler.deleteJob(jobKey);
		
		ScheduleJob tmp = new ScheduleJob();
		tmp.setId(job.getId());
		tmp.setJobState(ScheduleJob.JOB_STATE_NORMAL);
//		this.scheduleJobMapper.updateByPrimaryKeySelective(tmp);
		this.scheduleJobService.modifyObj(tmp);
	}

	@Override
	public void updateJobCron(ScheduleJob job) throws SchedulerException {
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobCode(), Scheduler.DEFAULT_GROUP);
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());  
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        
        scheduler.rescheduleJob(triggerKey, trigger);
	}

	@Override
	public List<ScheduleJob> getAllJob() throws SchedulerException {
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		
		List<String> jobCodeList = new ArrayList<>();
		for(JobKey jobKey : jobKeys){
			
			jobCodeList.add(jobKey.getName());
		}
		
		ScheduleJobExample jobExample = new ScheduleJobExample();
        jobExample.createCriteria().andJobCodeIn(jobCodeList);
        
//		return this.scheduleJobMapper.selectByExample(jobExample);
        return this.scheduleJobService.queryAllObjByExample(jobExample);
		
	}

	@Override
	public List<ScheduleJob> getRunningJob() throws SchedulerException {
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
        
        List<String> jobCodeList = new ArrayList<>(executingJobs.size());
        
        for (JobExecutionContext executingJob : executingJobs) {  
            JobDetail jobDetail = executingJob.getJobDetail();  
            JobKey jobKey = jobDetail.getKey();
            
            jobCodeList.add(jobKey.getName());
        }
        
        ScheduleJobExample jobExample = new ScheduleJobExample();
        jobExample.createCriteria().andJobCodeIn(jobCodeList);
        
//		return this.scheduleJobMapper.selectByExample(jobExample);
        return this.scheduleJobService.queryAllObjByExample(jobExample);
	}

	@Override
	public boolean isCronCorrect(String cronStr) {
		return CronExpression.isValidExpression(cronStr);
	}
	
	//添加运行的job
	public void addJob(ScheduleJob job) throws ClassNotFoundException, NoSuchMethodException, SchedulerException, ParseException{
		if (!this.checkSwitch()) {
			throw new CommonException("本应用，没有开启定时任务功能，请在 conf.properties文件中配置开启");
		}
		
		ClassLoader classLoader = this.getClass().getClassLoader();
		Class<?> objClass = classLoader.loadClass(job.getJobClass());
		Object excutionBean = SpringContextHolder.getOneBean(objClass);
		
		MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
		jobDetail.setName(job.getJobCode());
		jobDetail.setGroup(Scheduler.DEFAULT_GROUP);
		jobDetail.setTargetClass(objClass);
		jobDetail.setTargetObject(excutionBean);
		jobDetail.setTargetMethod(job.getJobMethod());
		jobDetail.setConcurrent(Constant.YES_INT.equals(job.getIsConcurrent()));
		
		jobDetail.afterPropertiesSet();
		jobDetail.getObject().getKey();
		
		CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
		trigger.setBeanName(job.getJobCode());
		trigger.setGroup(Scheduler.DEFAULT_GROUP);
        trigger.setJobDetail(jobDetail.getObject());
        trigger.setCronExpression(job.getCronExpression());
        trigger.afterPropertiesSet();
		
        scheduler.scheduleJob(jobDetail.getObject(), trigger.getObject());
	}
	
	@PostConstruct
	protected void init(){
		if (!checkSwitch()) {
			return;
		}
		
		ScheduleJobExample scheduleJobExample = new ScheduleJobExample();
		scheduleJobExample.createCriteria().andIsEnableEqualTo(Constant.YES_INT);
		
//		List<ScheduleJob> list = this.scheduleJobMapper.selectByExample(scheduleJobExample);
		List<ScheduleJob> list = this.scheduleJobService.queryAllObjByExample(scheduleJobExample);
		
		for(int i=0; i<list.size(); i++){
			ScheduleJob job = list.get(i);
			if (!Constant.YES_INT.equals(job.getIsEnable())) {
				//如果是禁用的，就跳过
				continue;
			}
			
			try {
				this.addJob(job);
				
				ScheduleJob tmp = new ScheduleJob();
				tmp.setId(job.getId());
				tmp.setJobState(ScheduleJob.JOB_STATE_NORMAL);
//				this.scheduleJobMapper.updateByPrimaryKeySelective(tmp);
				this.scheduleJobService.modifyObj(tmp);
				
			} catch (Exception e) {
				e.printStackTrace();
				
				ScheduleJob tmp = new ScheduleJob();
				tmp.setId(job.getId());
				tmp.setJobState(ScheduleJob.JOB_STATE_EXCEPTION);
//				this.scheduleJobMapper.updateByPrimaryKeySelective(tmp);
				this.scheduleJobService.modifyObj(tmp);
			}
			
		}
	}
	
	/**
	 * 检查开关是否开启,开启返回true,没有开启返回false
	 * @return
	 */
	public boolean checkSwitch(){
		return "true".equals(this.isJobEnalbe);
	}
	
//	public static void main(String[] args) {
//		
//	}

}
