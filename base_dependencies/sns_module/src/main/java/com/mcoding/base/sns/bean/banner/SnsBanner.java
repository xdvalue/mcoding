package com.mcoding.base.sns.bean.banner;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="轮播图")
public class SnsBanner implements Serializable {
    private Integer id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("提示文字")
    private String imgAlt;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("图片访问地址")
    private String imgUrl;

    @ApiModelProperty("超链接访问地址")
    private String aUrl;

    @ApiModelProperty("是否启用,启用1,禁用0")
    private Integer isEnable;

    @ApiModelProperty("创建时间")
    private Date createtime;

    @ApiModelProperty("编辑时间")
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImgAlt() {
        return imgAlt;
    }

    public void setImgAlt(String imgAlt) {
        this.imgAlt = imgAlt == null ? null : imgAlt.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getaUrl() {
        return aUrl;
    }

    public void setaUrl(String aUrl) {
        this.aUrl = aUrl == null ? null : aUrl.trim();
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}