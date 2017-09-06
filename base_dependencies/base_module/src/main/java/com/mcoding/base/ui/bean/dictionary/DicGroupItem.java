package com.mcoding.base.ui.bean.dictionary;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("字典组子项")
public class DicGroupItem implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	@ApiModelProperty("字典组Id")
    private Integer groupId;

	@ApiModelProperty("编码")
    private String code;

	@ApiModelProperty("代表的值")
    private String value;

	@ApiModelProperty("显示名称")
    private String name;

	@ApiModelProperty("描述")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}