package com.mcoding.base.sns.bean.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="微社区板块表")
public class SnsModule implements Serializable {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("模块名")
    private String name;

    @ApiModelProperty("排序")
    private Integer seqNum;

    @ApiModelProperty("状态码，0为关闭，1为开启")
    private Integer statusCode;

    @ApiModelProperty("图片url")
    private String imgUrl;

    @ApiModelProperty("商店ID")
    private Integer storeId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("模块父ID")
    private Integer parentId;

    @ApiModelProperty("帖子总数")
    private Integer postCount;

    private static final long serialVersionUID = 1L;

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

    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }
}