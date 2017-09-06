package com.mcoding.base.wechat.web.controller.material;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews.WxMpMaterialNewsArticle;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem;

public class MaterialNews implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String url;
	private String title1;
	private String title2;
	private String title3;
	private String title4;
	private String title5;
	private String title6;
	private String title7;
	private String title8;
	private String updateTime;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle1() {
		return title1;
	}
	public void setTitle1(String title1) {
		this.title1 = title1;
	}
	public String getTitle2() {
		return title2;
	}
	public void setTitle2(String title2) {
		this.title2 = title2;
	}
	public String getTitle3() {
		return title3;
	}
	public void setTitle3(String title3) {
		this.title3 = title3;
	}
	public String getTitle4() {
		return title4;
	}
	public void setTitle4(String title4) {
		this.title4 = title4;
	}
	public String getTitle5() {
		return title5;
	}
	public void setTitle5(String title5) {
		this.title5 = title5;
	}
	public String getTitle6() {
		return title6;
	}
	public void setTitle6(String title6) {
		this.title6 = title6;
	}
	public String getTitle7() {
		return title7;
	}
	public void setTitle7(String title7) {
		this.title7 = title7;
	}
	public String getTitle8() {
		return title8;
	}
	public void setTitle8(String title8) {
		this.title8 = title8;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public static List<MaterialNews> transform(List<WxMaterialNewsBatchGetNewsItem> items){
		List<MaterialNews> list = new ArrayList<>();
		
		if (CollectionUtils.isEmpty(items)) {
			return list;
		}
		
		for(int i=0; i<items.size(); i++){
			list.add(transform(items.get(i)));
		}
		return list;
	}
	
	public static MaterialNews transform(WxMaterialNewsBatchGetNewsItem item){
		MaterialNews news = new MaterialNews();
		
		List<WxMpMaterialNewsArticle> articles = item.getContent().getArticles();
		if (CollectionUtils.isEmpty(articles)) {
			return news;
		}
		
//		news.setUrl(item.getContent().getArticles().get(0).get);
		
		if (articles.size()>=1 && articles.get(0)!=null) {
			news.setTitle1(articles.get(0).getTitle());
		}
		if (articles.size()>=2 && articles.get(1)!=null) {
			news.setTitle2(articles.get(1).getTitle());
		}
		if (articles.size()>=3 && articles.get(2)!=null) {
			news.setTitle3(articles.get(2).getTitle());
		}
		if (articles.size()>=4 && articles.get(3)!=null) {
			news.setTitle4(articles.get(3).getTitle());
		}
		if (articles.size()>=5 && articles.get(4)!=null) {
			news.setTitle5(articles.get(4).getTitle());
		}
		if (articles.size()>=6 && articles.get(5)!=null) {
			news.setTitle6(articles.get(5).getTitle());
		}
		if (articles.size()>=7 && articles.get(6)!=null) {
			news.setTitle7(articles.get(6).getTitle());
		}
		if (articles.size()>=8 && articles.get(7)!=null) {
			news.setTitle8(articles.get(7).getTitle());
		}
		
		return news;
	}
	
}
