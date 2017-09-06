package com.mcoding.base.cms.bean.recommendarticle;

import java.io.Serializable;
import java.util.Date;

/**
 * 推荐文章
 * 
 * @author acer
 * 
 */
public class RecommendArticle implements Serializable {

	private static final long serialVersionUID = 2970881300561426211L;

	private Integer id;

	private Integer refId;

	private Integer articleId;

	private String articleTitle;

	private String articleStitle;

	private String articleLabel;

	private String articleAuthor;

	private Date publishTime;

	private String articleAbstract;

	private Integer clickCount;

	private String articleUrl;

    private Date createTime;

    private String coverImage;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRefId() {
		return refId;
	}

	public void setRefId(Integer refId) {
		this.refId = refId;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle == null ? null : articleTitle.trim();
	}

	public String getArticleStitle() {
		return articleStitle;
	}

    public void setArticleStitle(String articleStitle) {
        this.articleStitle = articleStitle == null ? null : articleStitle.trim();
    }

	public String getArticleLabel() {
		return articleLabel;
	}

	public void setArticleLabel(String articleLabel) {
		this.articleLabel = articleLabel == null ? null : articleLabel.trim();
	}

	public String getArticleAuthor() {
		return articleAuthor;
	}

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor == null ? null : articleAuthor.trim();
    }

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getArticleAbstract() {
		return articleAbstract;
	}

    public void setArticleAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract == null ? null : articleAbstract.trim();
    }

	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	public String getArticleUrl() {
		return articleUrl;
	}

	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl == null ? null : articleUrl.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage == null ? null : coverImage.trim();
    }
}