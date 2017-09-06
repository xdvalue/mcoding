package com.mcoding.base.dst.bean.level;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel(value="分销商级别")
public class DstLevel implements Serializable {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("分销商级别名称")
    private String levelName;

    @ApiModelProperty("分销商级别")
    private String levelGrade;

    @ApiModelProperty("上级级别id")
    private Integer parentLevelId;

    @ApiModelProperty("是否可用")
    private Integer isEnable;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    public String getLevelGrade() {
        return levelGrade;
    }

    public void setLevelGrade(String levelGrade) {
        this.levelGrade = levelGrade == null ? null : levelGrade.trim();
    }

    public Integer getParentLevelId() {
        return parentLevelId;
    }

    public void setParentLevelId(Integer parentLevelId) {
        this.parentLevelId = parentLevelId;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }
}