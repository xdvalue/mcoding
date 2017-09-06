package com.mcoding.base.cms.bean.article;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mcoding.base.cms.bean.comment.Comment;
import com.mcoding.base.cms.bean.label.ArticleLabelRef;
import com.mcoding.base.cms.bean.recommendarticle.RecommendArticle;
import com.mcoding.base.cms.utils.json.ArticleExtInfoDeserializer;
import com.mcoding.base.cms.utils.json.ArticleExtInfoSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("文章")
public class Article implements IArticle{
	private static final long serialVersionUID = 1L;
	
	/**文章状态 草稿**/
	public static final int STATE_DRAFT = 1;
	/**文章状态  待审核**/
	public static final int STATE_AUDIT = 2;
	/**文章状态  已发布**/
	public static final int STATE_PUBLISHED = 3;
	/**文章状态  删除到回收站**/
	public static final int STATE_RECYCLE = 4;

	private Integer id;

	@ApiModelProperty("文章标题")
    private String title;

	@ApiModelProperty("文章副标题")
    private String subTitle;

	@ApiModelProperty("文章显示类型,1普通文章,2首页轮播图")
    private Integer typeId;

	@ApiModelProperty("文章所属模块")
    private Integer moduleId;

	@ApiModelProperty("文章来源名称")
    private String origin;

	@ApiModelProperty("文章来源链接")
    private String originAddress;

	@ApiModelProperty("文章作者")
    private String author;

	@ApiModelProperty("文章摘要")
    private String summary;

	@ApiModelProperty("文章封面")
    private String coverImage;

	@ApiModelProperty("文章缩略图")
    private String thumbnail;

	@ApiModelProperty("文章发布时间")
    private Date publishTime;

	@ApiModelProperty("文章点击率")
    private Integer clickCount;

	@ApiModelProperty("文章点赞数")
    private Integer likeCount;

	@ApiModelProperty("文章点衰数")
    private Integer dislikeCount;

	@ApiModelProperty("文章排序号码，倒序排序，越到越靠前")
    private Integer sortNo;

	@ApiModelProperty("是否允许评论")
    private Integer commentFlag;

	@ApiModelProperty("审核的管理员名称")
    private String verifyAdminName;

	@ApiModelProperty("审核时间作者")
    private Date verifyTime;

	@ApiModelProperty("文章状态，1草稿，2待审核，3已发布，4回收站")
    private Integer articleState;

	@ApiModelProperty("文章发布类型，1平台自发布，2引用外连接")
    private Integer publishType;

    private Date createTime;

    private Date updateTime;

    @ApiModelProperty("文章标签")
    private String tag;

    @ApiModelProperty("文章的链接")
    private String url;

    @ApiModelProperty("评论数")
    private Integer commentCount;

    @ApiModelProperty("分享数")
    private Integer shareCount;

    @ApiModelProperty("审核不通过原因")
    private String unverifyReason;

    @ApiModelProperty("所属公司")
    private Integer storeId;

    @ApiModelProperty("文章内容")
    private String content;
    
    @ApiModelProperty("文章拓展信息")
    private IArticleExtInfo articleExtInfo;
	
    @ApiModelProperty("关联的推荐文章")
	private List<RecommendArticle> recommendArticleList;
	
    @ApiModelProperty("文章的标签")
	private List<ArticleLabelRef> labelList;
	
    @ApiModelProperty("文章的评论")
	private List<Comment> commentList;
	
	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commenteList) {
		this.commentList = commenteList;
	}

	@JsonSerialize(using=ArticleExtInfoSerializer.class)
	public void setArticleExtInfo(IArticleExtInfo articleExtInfo) {
		this.articleExtInfo = articleExtInfo;
	}
	
	@JsonDeserialize(using=ArticleExtInfoDeserializer.class)
	public IArticleExtInfo getArticleExtInfo() {
		return articleExtInfo;
	}

	public List<RecommendArticle> getRecommendArticleList() {
		return recommendArticleList;
	}

	public void setRecommendArticleList(List<RecommendArticle> recommendArticleList) {
		this.recommendArticleList = recommendArticleList;
	}
	
	public List<ArticleLabelRef> getLabelList() {
		return labelList;
	}

	public void setLabelList(List<ArticleLabelRef> labelList) {
		this.labelList = labelList;
	}

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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle == null ? null : subTitle.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    public String getOriginAddress() {
        return originAddress;
    }

    public void setOriginAddress(String originAddress) {
        this.originAddress = originAddress == null ? null : originAddress.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage == null ? null : coverImage.trim();
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail == null ? null : thumbnail.trim();
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(Integer dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Integer getCommentFlag() {
        return commentFlag;
    }

    public void setCommentFlag(Integer commentFlag) {
        this.commentFlag = commentFlag;
    }

    public String getVerifyAdminName() {
        return verifyAdminName;
    }

    public void setVerifyAdminName(String verifyAdminName) {
        this.verifyAdminName = verifyAdminName == null ? null : verifyAdminName.trim();
    }

    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    public Integer getArticleState() {
        return articleState;
    }

    public void setArticleState(Integer articleState) {
        this.articleState = articleState;
    }

    public Integer getPublishType() {
        return publishType;
    }

    public void setPublishType(Integer publishType) {
        this.publishType = publishType;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public String getUnverifyReason() {
        return unverifyReason;
    }

    public void setUnverifyReason(String unverifyReason) {
        this.unverifyReason = unverifyReason == null ? null : unverifyReason.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}