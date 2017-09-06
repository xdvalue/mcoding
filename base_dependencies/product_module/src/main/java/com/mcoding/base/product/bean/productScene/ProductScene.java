package com.mcoding.base.product.bean.productScene;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="产品场景")
public class ProductScene implements Serializable {
	
	//############以下是后期添加的，请不要覆盖########
	/**默认类型**/
	public static final Integer TYPE_DEFAULT = 1;
	//####################
	
    @ApiModelProperty("价格规则id")
    private Integer id;

    @ApiModelProperty("场景名称")
    private String sceneName;

    @ApiModelProperty("唯一场景编码")
    private String code;

    @ApiModelProperty("场景类型 1.默认 2.微商 3.内购活动")
    private Integer type;

    @ApiModelProperty("场景优先级 从大到小")
    private Integer priority;

    @ApiModelProperty("关联的方法")
    private String method;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("有效状态 0无效 1.有效")
    private Integer status;

    @ApiModelProperty("创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName == null ? null : sceneName.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}