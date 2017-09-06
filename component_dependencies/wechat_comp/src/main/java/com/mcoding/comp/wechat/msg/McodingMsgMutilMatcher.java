package com.mcoding.comp.wechat.msg;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;

import me.chanjar.weixin.mp.api.WxMpMessageMatcher;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;

/**
 * 多条消息的匹配
 * @author hzy
 *
 */
public class McodingMsgMutilMatcher implements WxMpMessageMatcher {

	private List<String> contentList;
	
	@SuppressWarnings("unchecked")
	public McodingMsgMutilMatcher(String contents) {
		try {
			if (StringUtils.isBlank(contents)) {
				return;
			}
			this.contentList = JsonUtilsForMcoding.convertCollection(contents, List.class, null, String.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean match(WxMpXmlMessage message) {
		if (CollectionUtils.isEmpty(contentList)) {
			return true;
		}
		
		String content = message.getContent();
		for(int i=0; i<this.contentList.size(); i++){
			if (contentList.get(i).equals(content)) {
				return true;
			}
		}
		
		return false;
	}
	
//	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
//		 List<String> abcd = JsonUtilsForMcoding.convertCollection("[\"你好\", \"6868\"]", List.class, null, String.class);
//		 System.out.println(abcd);
//	}

}
