package com.mcoding.mybatis.generator.plugins;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class PageExamplePlugin extends PluginAdapter {

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

		this.addIExampleInterface(topLevelClass, introspectedTable);

		this.addPageView(topLevelClass, introspectedTable);

		this.addToJsonMethod(topLevelClass, introspectedTable);

		return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
	}
	
	private void addToJsonMethod(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		/*
		@Override
		public String toJson() throws JsonProcessingException {
			return JsonUtilsForMcoding.writeValueAsString(this);
		}
		*/
		
		Method toJsonMethod = new Method();
		toJsonMethod.setVisibility(JavaVisibility.PUBLIC);
		toJsonMethod.setName("toJson");
		toJsonMethod.setReturnType(new FullyQualifiedJavaType("String"));
		toJsonMethod.addException(new FullyQualifiedJavaType("JsonProcessingException"));
		toJsonMethod.addBodyLine("return JsonUtilsForMcoding.writeValueAsString(this);");
		
		topLevelClass.addMethod(toJsonMethod);
		topLevelClass.addImportedType("com.fasterxml.jackson.core.JsonProcessingException");
		topLevelClass.addImportedType("com.mcoding.base.utils.json.JsonUtilsForMcoding");
		
	}

	private void addPageView(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        /*	    
        protected PageView<DicGroup> pageView;
        
        @Override
		public PageView<DicGroup> getPageView() {
			return pageView;
		}

		@Override
		public void setPageView(PageView<DicGroup> pageView) {
			this.pageView = pageView;
		}
        */		
		String className = introspectedTable.getTableConfiguration().getDomainObjectName();
		String pageViewTypeStr = "PageView<"+ className +">";
		
		FullyQualifiedJavaType pageViewType = new FullyQualifiedJavaType(pageViewTypeStr);
		Field pageViewField = new Field("pageView", pageViewType);
		pageViewField.setVisibility(JavaVisibility.PROTECTED);
		
		Method getPageViewMethod = new Method();
		getPageViewMethod.setVisibility(JavaVisibility.PUBLIC);
		getPageViewMethod.setName("getPageView");
		getPageViewMethod.setReturnType(pageViewType);
		getPageViewMethod.addAnnotation("@Override");
		getPageViewMethod.addBodyLine("return pageView;");
		
		Method setPageViewMethod = new Method();
		setPageViewMethod.setVisibility(JavaVisibility.PUBLIC);
		setPageViewMethod.setName("setPageView");
		setPageViewMethod.addParameter(new Parameter(pageViewType, "pageView"));
		setPageViewMethod.addAnnotation("@Override");
		setPageViewMethod.addBodyLine("this.pageView = pageView;");
		
		topLevelClass.addField(pageViewField);
		topLevelClass.addMethod(getPageViewMethod);
		topLevelClass.addMethod(setPageViewMethod);
		topLevelClass.addImportedType("com.mcoding.base.core.PageView");
	}

	private void addIExampleInterface(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		String className = introspectedTable.getTableConfiguration().getDomainObjectName();
		String interfaceOrExample = "IExample<" + className + ">";

		topLevelClass.addSuperInterface(new FullyQualifiedJavaType(interfaceOrExample));
		topLevelClass.addImportedType("com.mcoding.base.core.IExample");
	}

}
