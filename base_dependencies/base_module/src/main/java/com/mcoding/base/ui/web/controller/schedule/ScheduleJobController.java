package com.mcoding.base.ui.web.controller.schedule;

import java.text.ParseException;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.ui.bean.schedule.ScheduleJob;
import com.mcoding.base.ui.bean.schedule.ScheduleJobExample;
import com.mcoding.base.ui.plugin.schedule.QuartzManager;
import com.mcoding.base.ui.service.schedule.ScheduleJobService;
import com.mcoding.base.ui.utils.common.Constant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="定时任务")
@Controller
@RequestMapping("scheduleJob")
public class ScheduleJobController {
    @Resource
    protected ScheduleJobService scheduleJobService;
    
    @Resource
    protected QuartzManager quartzManager;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("base/scheduleJob/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("base/scheduleJob/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        ScheduleJob scheduleJob = this.scheduleJobService.queryObjById(id);
        view.addObject("scheduleJob", scheduleJob);
        view.setViewName("base/scheduleJob/toAddView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建定时任务")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody ScheduleJob scheduleJob) {
    	
    	
        this.scheduleJobService.addObj(scheduleJob);
        JsonResult<String> result = new JsonResult<>();
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑定时任务")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody ScheduleJob scheduleJob) throws SchedulerException {
        if (scheduleJob.getId() == null || scheduleJob.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        
        ScheduleJob tmp = this.scheduleJobService.queryObjById(scheduleJob.getId());
        if (ScheduleJob.JOB_STATE_NORMAL.equals(tmp.getJobState())) {
        	throw new CommonException("job 已启动，无法修改");
		}
        
        if (!this.quartzManager.isCronCorrect(scheduleJob.getCronExpression())) {
			throw new CommonException("cron 表达式错误");
		}
        
        if(StringUtils.isBlank(scheduleJob.getCronExpression()) || StringUtils.isBlank(scheduleJob.getJobCode())){
        	throw new CommonException("cron表达式 与 jobcode 都不能为空");
        }
        
        if (!scheduleJob.getCronExpression().equals(tmp.getCronExpression()) || 
        		!scheduleJob.getJobCode().equals(tmp.getJobCode()) ||
        		!scheduleJob.getIsConcurrent().equals(tmp.getIsConcurrent())) {
			this.quartzManager.deleteJob(tmp);
		}
        
        this.scheduleJobService.modifyObj(scheduleJob);
        
        JsonResult<String> result = new JsonResult<>();
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除定时任务")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        this.scheduleJobService.deleteObjById(id);
        
        JsonResult<String> result = new JsonResult<>();
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询定时任务")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<ScheduleJob> findByPage(@ApiParam(name="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(name="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<ScheduleJob> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        ScheduleJobExample example = new ScheduleJobExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.scheduleJobService.queryObjByPage(example);
    }
    
    @ApiOperation(httpMethod="GET", value="现在执行任务")
    @RequestMapping("service/runJobNow")
	@ResponseBody
	public JsonResult<String> runJobNow(int id){
		JsonResult<String> result = new JsonResult<>();
		ScheduleJob job = this.scheduleJobService.queryObjById(id);
		
		try {
			this.quartzManager.runJobNow(job);
		} catch (ClassNotFoundException e) {
			
			throw new CommonException("找不到该bean");
		} catch (NoSuchMethodException e) {
			
			throw new CommonException("找不到该方法");
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		result.setStatus("00");
		result.setMsg("ok");
		
		return result;
	}
    
    @ApiOperation(httpMethod="GET", value="停止任务")
    @RequestMapping("service/pauseJob")
	@ResponseBody
	public JsonResult<String> pauseJob(int id){
        JsonResult<String> result = new JsonResult<>();
		
        ScheduleJob job = this.scheduleJobService.queryObjById(id);
		try {
			this.quartzManager.pauseJob(job);
			
			ScheduleJob tmp = new ScheduleJob();
			tmp.setId(id);
			tmp.setIsEnable(Constant.NO_INT);
			this.scheduleJobService.modifyObj(tmp);
			
		} catch (SchedulerException e) {
			throw new CommonException("job 停止任务失败");
		}
		
		result.setStatus("00");
		result.setMsg("ok");
		
		return result;
	}
	
    @ApiOperation(httpMethod="GET", value="启动任务")
	@RequestMapping("service/resumeJob")
	@ResponseBody
	public JsonResult<String> resumeJob(int id){
        JsonResult<String> result = new JsonResult<>();
        
        ScheduleJob job = this.scheduleJobService.queryObjById(id);
		try {
			this.quartzManager.resumeJob(job);
			
			ScheduleJob tmp = new ScheduleJob();
			tmp.setId(id);
			tmp.setIsEnable(Constant.YES_INT);
			this.scheduleJobService.modifyObj(tmp);
			
		} catch (ClassNotFoundException | NoSuchMethodException | SchedulerException | ParseException e) {
			throw new CommonException("job 启动任务失败");
		}
		
		result.setStatus("00");
		result.setMsg("ok");
		
		return result;
	}
}