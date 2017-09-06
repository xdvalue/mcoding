package com.mcoding.base.cms.bean.label;

import java.io.Serializable;

public class ArticleLabelRef implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer storeId;
	
    private Integer articleId;

    private Integer labelId;

    private String lableMark;

    private String articleTitle;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getLableMark() {
        return lableMark;
    }

    public void setLableMark(String lableMark) {
        this.lableMark = lableMark == null ? null : lableMark.trim();
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }
}