package com.mcoding.base.ui.bean.dictionary;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="数据字典组")
public class DicGroup implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("id")
	private Integer id;

	@ApiModelProperty("名称")
    private String name;

	@ApiModelProperty("编码")
    private String code;

	@ApiModelProperty("描述")
    private String description;

	@ApiModelProperty("父字典组")
    private Integer parentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}