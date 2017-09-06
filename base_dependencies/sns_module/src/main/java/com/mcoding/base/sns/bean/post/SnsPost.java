package com.mcoding.base.sns.bean.post;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.sns.service.post.SnsPostExtInfoService;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="微社区帖子表")
public class SnsPost implements Serializable {
	
//	###############  一下不是自动生成，请不要覆盖 #########################
	// 普通帖
	public final static int TYPE_FLAG_COMMON = 1;
	// 活动贴
	public final static int TYPE_FLAG_ACTIVITY = 2;
	// 精品贴
	public final static int TYPE_FLAG_BOUTIQUE = 3;
	
	@ApiModelProperty("发帖人的详细信息")
	private Member member;

	@ApiModelProperty("图片数组")
	private List<SnsPostImg> postImgs;
	
	@ApiModelProperty("帖子拓展消息")
	private SnsPostExtInfo snsPostExtInfo;
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	public List<SnsPostImg> getPostImgs() {
		return postImgs;
	}

	public void setPostImgs(List<SnsPostImg> postImgs) {
		this.postImgs = postImgs;
	}
	
	public SnsPostExtInfo getSnsPostExtInfo() {
		if (this.getId()!=null) {
			snsPostExtInfo = SpringContextHolder.getOneBean(SnsPostExtInfoService.class).queryObjByPostId(this.getId());
		}
		return snsPostExtInfo;
	}

	public void setSnsPostExtInfo(SnsPostExtInfo snsPostExtInfo) {
		this.snsPostExtInfo = snsPostExtInfo;
	}
	
	// ###############  end #########################
	
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("openId")
    private String openId;

    @ApiModelProperty("会员ID")
    private Integer memeberId;

    @ApiModelProperty("会员名称")
    private String memberName;

    @ApiModelProperty("会员头像")
    private String memberImgUrl;

    @ApiModelProperty("帖子标题")
    private String title;

    @ApiModelProperty("模块类型ID")
    private Integer moduleId;

    @ApiModelProperty("模块名称")
    private String moduleName;

    @ApiModelProperty("评论数")
    private Integer commentNum;

    @ApiModelProperty("点赞数")
    private Integer likeNum;

    @ApiModelProperty("点衰数")
    private Integer dislikeNum;

    @ApiModelProperty("帖子类型标记,1为普通贴，2为精品贴，3为活动贴")
    private Integer typeFlag;

    @ApiModelProperty("商店ID")
    private Integer storeId;

    @ApiModelProperty("商店名称")
    private String storeName;

    @ApiModelProperty("显示状态，0为屏蔽，1为显示")
    private Integer isEnable;

    @ApiModelProperty("审核状态，0为未审核，1为已审核")
    private Integer isCheck;

    @ApiModelProperty(" 排序号,用于置顶，0为普通帖子，大于0为置顶贴")
    private Integer orderNum;

    @ApiModelProperty("排序顺序，用于首页的置顶")
    private Integer orderInHome;

    @ApiModelProperty("评论更新时间")
    private Date replyTime;

    @ApiModelProperty("帖子创建时间")
    private Date createTime;

    @ApiModelProperty("帖子更新时间")
    private Date updateTime;

    @ApiModelProperty("帖子内容")
    private String content;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Integer getMemeberId() {
        return memeberId;
    }

    public void setMemeberId(Integer memeberId) {
        this.memeberId = memeberId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
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

    public Integer getDislikeNum() {
        return dislikeNum;
    }

    public void setDislikeNum(Integer dislikeNum) {
        this.dislikeNum = dislikeNum;
    }

    public Integer getTypeFlag() {
        return typeFlag;
    }

    public void setTypeFlag(Integer typeFlag) {
        this.typeFlag = typeFlag;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getOrderInHome() {
        return orderInHome;
    }

    public void setOrderInHome(Integer orderInHome) {
        this.orderInHome = orderInHome;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}