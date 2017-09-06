package com.mcoding.base.cms.utils.json;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.mcoding.base.cms.bean.article.IArticleExtInfo;
import com.mcoding.base.cms.bean.article.serializer.IArticleExtInfoSerializer;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

public class ArticleExtInfoDeserializer extends JsonDeserializer<IArticleExtInfo> {

	private static Logger logger = LoggerFactory.getLogger(ArticleExtInfoDeserializer.class);

	@Override
	public IArticleExtInfo deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		
		IArticleExtInfoSerializer serializer = SpringContextHolder.getBean("articleExtInfoSerializer");
		if (serializer == null) {
			serializer = SpringContextHolder.getOneBean(IArticleExtInfoSerializer.class);
		}
		
		if (serializer == null) {
			logger.warn("还没有配置文章拓展信息的序列化方式");
		}
		
		if(serializer !=null && ctxt != null){
			return serializer.deserializerFormJson(ctxt.toString());
		}else{
			return null;
		}
		
	}

}