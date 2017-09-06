package com.mcoding.base.member.bean.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="会员粉丝表")
public class MemberFollower implements Serializable {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("会员id")
    private Integer parentId;

    @ApiModelProperty("会员名称")
    private String parentName;

    @ApiModelProperty("会员头像")
    private String parentHeadImgUrl;

    @ApiModelProperty("粉丝id")
    private Integer followerId;

    @ApiModelProperty("粉丝名称")
    private String followerName;

    @ApiModelProperty("粉丝头像")
    private String followerHeadImgUrl;

    @ApiModelProperty("关注时间")
    private Date followTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName == null ? null : parentName.trim();
    }

    public String getParentHeadImgUrl() {
        return parentHeadImgUrl;
    }

    public void setParentHeadImgUrl(String parentHeadImgUrl) {
        this.parentHeadImgUrl = parentHeadImgUrl == null ? null : parentHeadImgUrl.trim();
    }

    public Integer getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Integer followerId) {
        this.followerId = followerId;
    }

    public String getFollowerName() {
        return followerName;
    }

    public void setFollowerName(String followerName) {
        this.followerName = followerName == null ? null : followerName.trim();
    }

    public String getFollowerHeadImgUrl() {
        return followerHeadImgUrl;
    }

    public void setFollowerHeadImgUrl(String followerHeadImgUrl) {
        this.followerHeadImgUrl = followerHeadImgUrl == null ? null : followerHeadImgUrl.trim();
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }
}