package com.mcoding.base.sns.bean.comment;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.mcoding.base.member.bean.member.Member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="微社区评论")
public class SnsComment implements Serializable {

//	###############  一下不是自动生成，请不要覆盖 #########################
	
	@ApiModelProperty("会员资料")
	private Member member;
	
	@ApiModelProperty("回复")
	private List<SnsComment> replies;
	
	@ApiModelProperty("图片数组")
	private List<SnsCommentImg> postImgs;
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<SnsComment> getReplies() {
		return replies;
	}

	public void setReplies(List<SnsComment> replies) {
		this.replies = replies;
	}
	
	public List<SnsCommentImg> getPostImgs() {
		return postImgs;
	}

	public void setPostImgs(List<SnsCommentImg> postImgs) {
		this.postImgs = postImgs;
	}

	// ###############  end #########################

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("帖子ID")
    private Integer postId;

    @ApiModelProperty("openId")
    private String openId;

    @ApiModelProperty("商店ID")
    private Integer storeId;

    @ApiModelProperty("会员ID")
    private Integer memberId;

    @ApiModelProperty("会员名称")
    private String memberName;

    @ApiModelProperty("会员头像")
    private String memberImgUrl;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("操作类型,1为评论，2为回复")
    private Integer operType;

    @ApiModelProperty("软删除标记，1为正常，0为删除")
    private Boolean isEnabled;

    @ApiModelProperty("审核标识，1为已审核,0为未审核")
    private Boolean isChecked;

    @ApiModelProperty("回复数")
    private Integer commentNum;

    @ApiModelProperty("点赞数")
    private Integer likeNum;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("回复的上一条评论的ID")
    private Integer parentCommentId;

    @ApiModelProperty("回复的上一条评论的会员id")
    private Integer parentMemberId;

    @ApiModelProperty("回复的上一条评论的会员名称")
    private String parentMemberName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public String getMemberImgUrl() {
        return memberImgUrl;
    }

    public void setMemberImgUrl(String memberImgUrl) {
        this.memberImgUrl = memberImgUrl == null ? null : memberImgUrl.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
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

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public Integer getParentMemberId() {
        return parentMemberId;
    }

    public void setParentMemberId(Integer parentMemberId) {
        this.parentMemberId = parentMemberId;
    }

    public String getParentMemberName() {
        return parentMemberName;
    }

    public void setParentMemberName(String parentMemberName) {
        this.parentMemberName = parentMemberName == null ? null : parentMemberName.trim();
    }
}