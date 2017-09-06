package com.mcoding.base.cms.bean.article.serializer;

import com.mcoding.base.cms.bean.article.IArticleExtInfo;

public interface IArticleExtInfoSerializer {
	
	public String serializerToJson(IArticleExtInfo articleExtInfo);
	
	public IArticleExtInfo deserializerFormJson(String json);
}
