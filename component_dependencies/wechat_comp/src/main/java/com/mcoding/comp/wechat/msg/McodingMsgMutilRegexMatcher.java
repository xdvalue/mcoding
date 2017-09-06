package com.mcoding.comp.wechat.msg;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;

import me.chanjar.weixin.mp.api.WxMpMessageMatcher;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;

public class McodingMsgMutilRegexMatcher implements WxMpMessageMatcher {
	
//	private String rEventKey;
	private List<String> regexList;

	@SuppressWarnings("unchecked")
	public McodingMsgMutilRegexMatcher(String contents) {
//		this.rEventKey = eventKey;
		try {
			this.regexList = JsonUtilsForMcoding.convertCollection(contents, List.class, null, String.class);
			System.out.println(this.regexList);
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
		if (CollectionUtils.isEmpty(this.regexList)) {
			return true;
		}
		
		String content = message.getContent();
		for(int i=0; i<this.regexList.size(); i++){
			if (Pattern.matches(regexList.get(i), StringUtils.defaultIfBlank(content, ""))) {
				return true;
			}
		}
		return false;
	}
	
//	public static void main(String[] args) {
//		McodingMsgMutilRegexMatcher regexMatcher = new McodingMsgMutilRegexMatcher("[\"abc\",\"bcde\"]");
//	}

}
