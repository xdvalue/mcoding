package com.mcoding.comp.wechat.common.utils;

import com.thoughtworks.xstream.XStream;

import me.chanjar.weixin.common.util.xml.XStreamInitializer;

public class XmlUtils {
	
	@SuppressWarnings("unchecked")
	public static <T> T parseXMl(String xml, Class<T> classType){
		XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(classType);
        Object obj = xstream.fromXML(xml);
		return (T) obj;
	}
	
	public static String toXml(Object object) {
		XStream xstream = XStreamInitializer.getInstance();
	    xstream.processAnnotations(object.getClass());
	    return xstream.toXML(object).replaceAll("__", "_");
	}

}
