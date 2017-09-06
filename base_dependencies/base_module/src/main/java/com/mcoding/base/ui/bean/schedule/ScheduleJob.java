package com.mcoding.base.ui.bean.schedule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="定时任务")
public class ScheduleJob implements Serializable {
	
//	############以下不是自动生成，请不要覆盖##################
	/**正常运行**/
	public static final Integer JOB_STATE_NORMAL = 1;
	/**停止运行**/
	public static final Integer JOB_STATE_PAUSE = 0;
	/**运行异常**/
	public static final Integer JOB_STATE_EXCEPTION = -1;
//	##############         end               ################
	
    @ApiModelProperty("定时任务Id")
    private Integer id;

    @ApiModelProperty("定时任务名称")
    private String jobName;

    @ApiModelProperty("job唯一代码")
    private String jobCode;

    private String description;

    @ApiModelProperty("定时任务完整类名称")
    private String jobClass;

    @ApiModelProperty("定时任务方法名称")
    private String jobMethod;

    @ApiModelProperty("job状态,1正常运行，2运行异常")
    private Integer jobState;

    @ApiModelProperty("定时任务的Corn表达式")
    private String cronExpression;

    @ApiModelProperty("是否启用，1启用，0禁用")
    private Integer isEnable;

    @ApiModelProperty("能否并发运行，1可以，0不可以")
    private Integer isConcurrent;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode == null ? null : jobCode.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass == null ? null : jobClass.trim();
    }

    public String getJobMethod() {
        return jobMethod;
    }

    public void setJobMethod(String jobMethod) {
        this.jobMethod = jobMethod == null ? null : jobMethod.trim();
    }

    public Integer getJobState() {
        return jobState;
    }

    public void setJobState(Integer jobState) {
        this.jobState = jobState;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression == null ? null : cronExpression.trim();
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getIsConcurrent() {
        return isConcurrent;
    }

    public void setIsConcurrent(Integer isConcurrent) {
        this.isConcurrent = isConcurrent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}