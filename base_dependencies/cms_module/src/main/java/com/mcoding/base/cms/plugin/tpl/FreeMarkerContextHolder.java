package com.mcoding.base.cms.plugin.tpl;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

public class FreeMarkerContextHolder {
	
	private static final String lock = "lock";
	private static FreeMarkerContextHolder contextHolder;
	private Configuration cfg;
	
	private FreeMarkerContextHolder(){
		super();
	}
	
	public static FreeMarkerContextHolder getInstance() throws IOException{
		if (contextHolder != null) {
			return contextHolder;
		}
		synchronized (lock) {
			if (contextHolder == null) {
				contextHolder = new FreeMarkerContextHolder();
				contextHolder.init();
			}
		}
		
		return contextHolder;
	}
	
	private void init() throws IOException{
		this.cfg = new Configuration(Configuration.VERSION_2_3_25);
		this.cfg.setDirectoryForTemplateLoading(new File("/Users/hzy/work/code/mcoding/code/trunk/base_dependencies/cms_module/src/main/webapp/template"));
		this.cfg.setDefaultEncoding("UTF-8");
		this.cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}

	public Configuration getCfg() {
		return cfg;
	}
	
	public static void main(String[] args) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		Template temp = FreeMarkerContextHolder.getInstance().getCfg().getTemplate("index.html");
		
		Writer out = new OutputStreamWriter(System.out);
		
		Map<String, String> root = new HashMap<>();
		root.put("user", "admin");
		root.put("url", "http://www.jd.com/abc/2/3/a.html");
		root.put("name", "productA");
		temp.process(root, out);
	}
	
	

}
