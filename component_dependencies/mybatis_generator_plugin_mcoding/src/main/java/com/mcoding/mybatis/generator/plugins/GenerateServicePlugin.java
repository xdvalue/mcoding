package com.mcoding.mybatis.generator.plugins;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import com.mcoding.mybatis.generator.utils.ServiceGenerateDataStorage;

public class GenerateServicePlugin extends PluginAdapter{
	
	private String targetPackage;
	private String targetProject;
	private boolean isRedisEnable;

	@Override
	public boolean validate(List<String> warnings) {
		String targetPackage = this.properties.getProperty("targetPackage");
		if (StringUtils.isBlank(targetPackage)) {
			warnings.add("Service 生成失败， targetPackage 配置失败");
			return false;
		}
		
		if (!targetPackage.matches("^(\\w+)(\\.\\w*)*\\w$")) {
			warnings.add("Service 生成失败， targetPackage["+targetPackage+"] 格式错误");
			return false;
		}
		
		String targetProject = this.properties.getProperty("targetProject");
		if (StringUtils.isBlank(targetProject)) {
			if (StringUtils.isBlank(targetPackage)) {
				warnings.add("Service 生成失败， targetProject 配置失败");
				return false;
			}
		}
		
		String isRedisEnableStr = this.properties.getProperty("isRedisEnable");
		if (StringUtils.isBlank(isRedisEnableStr) || 
				(!"false".equals(isRedisEnableStr) && !"true".equals(isRedisEnableStr))) {
			isRedisEnableStr = "true";
		}
		
		this.isRedisEnable = Boolean.valueOf(isRedisEnableStr);
		this.targetPackage = targetPackage;
		this.targetProject = targetProject;
		return true;
	}

	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		String modelName = introspectedTable.getTableConfiguration().getDomainObjectName();
		
		GeneratedJavaFile service = new GeneratedJavaFile(
				this.createService(introspectedTable, modelName), 
				this.targetProject, 
				this.context.getJavaFormatter());
		
		GeneratedJavaFile serviceImpl = new GeneratedJavaFile(
				this.createServiceImpl(introspectedTable, modelName), 
				this.targetProject, 
				this.context.getJavaFormatter());
		
		List<GeneratedJavaFile> list = new ArrayList<>(2);
		list.add(service);
		list.add(serviceImpl);
		
		String tableName = introspectedTable.getTableConfiguration().getTableName();
		ServiceGenerateDataStorage.getInstance().setServicePackage(tableName, this.targetPackage);
		
		return list;
	}
	
	
	public Interface createService(IntrospectedTable introspectedTable, String modelClassName){
		String serviceName = this.targetPackage + "." + modelClassName + "Service";
		
		FullyQualifiedJavaType serviceType = new FullyQualifiedJavaType(serviceName);
		Interface service = new Interface(serviceType);
		service.setVisibility(JavaVisibility.PUBLIC);
		
		this.addBaseService(service, introspectedTable, modelClassName);
		return service;
	}
	
	public void addBaseService(Interface service, IntrospectedTable introspectedTable, String modelClassName){
		service.addImportedType(new FullyQualifiedJavaType("com.mcoding.base.core.BaseService"));
		
		FullyQualifiedJavaType modelType = this.getModelType(introspectedTable, modelClassName);
		service.addImportedType(modelType);
		
		FullyQualifiedJavaType exampleType = this.getExampleType(introspectedTable, modelClassName);
		service.addImportedType(exampleType);
		
		String baseServiceStr = "BaseService<"+ modelClassName +", "+ modelClassName+"Example>";
		FullyQualifiedJavaType baseService = new FullyQualifiedJavaType(baseServiceStr);
		
		service.addSuperInterface(baseService);
	}
	
	private FullyQualifiedJavaType getModelType(IntrospectedTable introspectedTable, String modelClassName){
		
		String beanPackageStr = introspectedTable.getContext().getJavaModelGeneratorConfiguration().getTargetPackage();
		String fullModelClassName = beanPackageStr + "." + modelClassName;
		
		FullyQualifiedJavaType modelType = new FullyQualifiedJavaType(fullModelClassName);
		return modelType;
	}
	
	private FullyQualifiedJavaType getExampleType(IntrospectedTable introspectedTable, String modelClassName){
		String beanPackageStr = introspectedTable.getContext().getJavaModelGeneratorConfiguration().getTargetPackage();
        String exampleName = modelClassName + "Example";
		
		String fullExampleNameStr = beanPackageStr + "." + exampleName;
		FullyQualifiedJavaType exampleType = new FullyQualifiedJavaType(fullExampleNameStr);
		return exampleType;
	}
	
	private FullyQualifiedJavaType getMapperType(IntrospectedTable introspectedTable, String modelClassName){
		String mapperPackage = introspectedTable.getContext().getSqlMapGeneratorConfiguration().getTargetPackage();
		String mapperFullName = mapperPackage + "." + modelClassName + "Mapper";
		
		FullyQualifiedJavaType mapperType = new FullyQualifiedJavaType(mapperFullName);
		return mapperType;
	}
	
	public TopLevelClass createServiceImpl(IntrospectedTable introspectedTable, String className){
		String serviceImplName = targetPackage + ".impl." + className + "ServiceImpl";
		
		FullyQualifiedJavaType serviceImplType = new FullyQualifiedJavaType(serviceImplName);
		TopLevelClass serviceImpl = new TopLevelClass(serviceImplType);
		serviceImpl.setVisibility(JavaVisibility.PUBLIC);
		
		String serviceName = targetPackage + "." + className + "Service";
		FullyQualifiedJavaType serviceType = new FullyQualifiedJavaType(serviceName);
		serviceImpl.addImportedType(serviceType);
		
		serviceImpl.addSuperInterface(new FullyQualifiedJavaType(className + "Service"));
		
		serviceImpl.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Service"));
		serviceImpl.addAnnotation("@Service(\""+ StringUtils.uncapitalize(className) +"Service\")");
		
		serviceImpl.addImportedType(new FullyQualifiedJavaType("org.springframework.cache.annotation.CacheEvict"));
		serviceImpl.addImportedType(new FullyQualifiedJavaType("org.springframework.cache.annotation.Cacheable"));
		serviceImpl.addImportedType(new FullyQualifiedJavaType("com.mcoding.base.core.PageView"));
		serviceImpl.addImportedType(new FullyQualifiedJavaType("java.util.List"));
		
		this.addServiceOverrideMethod(serviceImpl, introspectedTable, className);
		return serviceImpl;
	}

	private void addServiceOverrideMethod(TopLevelClass serviceImpl, IntrospectedTable introspectedTable,
			String modelClassName) {
		
		FullyQualifiedJavaType  mapperType = this.getMapperType(introspectedTable, modelClassName);
		serviceImpl.addImportedType(mapperType);
		
		String mapperName = StringUtils.uncapitalize(modelClassName) +"Mapper";
		Field mapper = new Field(mapperName, mapperType);
		mapper.setVisibility(JavaVisibility.PROTECTED);
		
		mapper.addAnnotation("@Resource");
		serviceImpl.addImportedType(new FullyQualifiedJavaType("javax.annotation.Resource"));
		serviceImpl.addField(mapper);
		
		serviceImpl.addImportedType(this.getModelType(introspectedTable, modelClassName));
		serviceImpl.addImportedType(this.getExampleType(introspectedTable, modelClassName));
		
		serviceImpl.addMethod(this.methodAddObj(introspectedTable, modelClassName, mapperName));
		serviceImpl.addMethod(this.methodDeleteById(introspectedTable, modelClassName, mapperName));
		serviceImpl.addMethod(this.methodModifyObj(introspectedTable, modelClassName, mapperName));
		serviceImpl.addMethod(this.methodQueryObjById(introspectedTable, modelClassName, mapperName));
		serviceImpl.addMethod(this.methodQueryAllObjByExample(introspectedTable, modelClassName, mapperName));
		serviceImpl.addMethod(this.methodQueryObjByPage(introspectedTable, modelClassName, mapperName));
		
	}

	private Method methodAddObj(IntrospectedTable introspectedTable, String modelClassName, String mapperName) {
		/*
		@CacheEvict(value={"dicGroup"}, allEntries=true)
		@Override
		public void addObj(DicGroup t) {
			this.dicGroupMapper.insertSelective(t);
		}*/
		
		Method addObj = new Method();
		addObj.setName("addObj");
		addObj.setVisibility(JavaVisibility.PUBLIC);
		addObj.addParameter(new Parameter(this.getModelType(introspectedTable, modelClassName), "t"));
		
		String bodyLine = "this."+mapperName+".insertSelective(t);";
		addObj.addBodyLine(bodyLine);
		if (this.isRedisEnable) {
			addObj.addAnnotation("@CacheEvict(value={\""+ StringUtils.uncapitalize(modelClassName)+"\"}, allEntries=true)");
		}
		addObj.addAnnotation("@Override");
		return addObj;
	}
	
	private Method methodDeleteById(IntrospectedTable introspectedTable,String modelClassName, String mapperName) {
		/*
		@CacheEvict(value={"dicGroup"}, allEntries=true)
		@Override
		public void deleteObjById(int id) {
			this.dicGroupMapper.deleteByPrimaryKey(id);
		}*/
		
		Method method = new Method();
		method.setName("deleteObjById");
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addParameter(new Parameter(new FullyQualifiedJavaType("int"), "id"));
		
		String bodyLine = "this."+mapperName+".deleteByPrimaryKey(id);";
		method.addBodyLine(bodyLine);
		if (this.isRedisEnable) {
			method.addAnnotation("@CacheEvict(value={\""+ StringUtils.uncapitalize(modelClassName)+"\"}, allEntries=true)");
		}
		method.addAnnotation("@Override");
		return method;
	}
	
	private Method methodModifyObj(IntrospectedTable introspectedTable,String modelClassName, String mapperName){
		/*
		@CacheEvict(value={"dicGroup"}, allEntries=true)
		@Override
		public void modifyObj(DicGroup t) {
		    if (t.getId() == null || t.getId() ==0) {
			    throw new NullPointerException("id 为空，无法更新");
		    }
			this.dicGroupMapper.updateByPrimaryKeySelective(t);
		}*/
		
		Method method = new Method();
		method.setName("modifyObj");
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addParameter(new Parameter(this.getModelType(introspectedTable, modelClassName), "t"));
		
		method.addBodyLine("if (t.getId() == null || t.getId() ==0) {");
		method.addBodyLine("throw new NullPointerException(\"id 为空，无法更新\");");
		method.addBodyLine("}");
		method.addBodyLine("this."+mapperName+".updateByPrimaryKeySelective(t);");
		if (this.isRedisEnable) {
			method.addAnnotation("@CacheEvict(value={\""+ StringUtils.uncapitalize(modelClassName)+"\"}, allEntries=true)");
		}
		method.addAnnotation("@Override");
		
		return method;
	}
	
	private Method methodQueryObjById(IntrospectedTable introspectedTable,String modelClassName, String mapperName){
		/*
		@Cacheable(value="dicGroup", key="'DicGroupService_' + #root.methodName + '_' +#id")
		@Override
		public DicGroup queryObjById(int id) {
			return this.dicGroupMapper.selectByPrimaryKey(id);
		}*/
		
		Method method = new Method();
		method.setName("queryObjById");
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addParameter(new Parameter(new FullyQualifiedJavaType("int"), "id"));
		method.setReturnType(this.getModelType(introspectedTable, modelClassName));
		
		String bodyLine = "return this."+mapperName+".selectByPrimaryKey(id);";
		method.addBodyLine(bodyLine);
		if (this.isRedisEnable) {
			method.addAnnotation("@Cacheable(value=\""+StringUtils.uncapitalize(modelClassName)+"\", key=\"'"+modelClassName+"Service_' + #root.methodName + '_' +#id\")");
		}
		method.addAnnotation("@Override");
		return method;
	}
	
	private Method methodQueryAllObjByExample(IntrospectedTable introspectedTable,String modelClassName, String mapperName){
		/*
		@Cacheable(value="dicGroup", key="'DicGroupService_' + #root.methodName + '_'+ #example.toJson()")
		@Override
		public List<DicGroup> queryAllObjByExample(DicGroupExample example) {
			return this.dicGroupMapper.selectByExample(example);
		}*/
		
		Method method = new Method();
		method.setName("queryAllObjByExample");
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addParameter(new Parameter(this.getExampleType(introspectedTable, modelClassName), "example"));
		method.setReturnType(new FullyQualifiedJavaType("List<"+modelClassName+">"));
		
		String bodyLine = "return this."+mapperName+".selectByExample(example);";
		method.addBodyLine(bodyLine);
		if (this.isRedisEnable) {
			method.addAnnotation("@Cacheable(value=\""+StringUtils.uncapitalize(modelClassName)+"\", key=\"'"+modelClassName+"Service_' + #root.methodName + '_'+ #example.toJson()\")");
		}
		method.addAnnotation("@Override");
		return method;
	}
	
	private Method methodQueryObjByPage(IntrospectedTable introspectedTable,String modelClassName, String mapperName){
		/*
		@Cacheable(value="dicGroup", key="'DicGroupService_' + #root.methodName + '_'+ #example.toJson()")
		@Override
		public PageView<DicGroup> queryObjByPage(DicGroupExample example) {
			PageView<DicGroup> pageView = example.getPageView();
			if (pageView == null) {
				pageView = new PageView<>(1, 10);
				example.setPageView(pageView);
			}
			pageView.setQueryResult(this.dicGroupMapper.selectByExampleByPage(example));
			return pageView;
		}*/
		
		Method method = new Method();
		method.setName("queryObjByPage");
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addParameter(new Parameter(this.getExampleType(introspectedTable, modelClassName), "example"));
		method.setReturnType(new FullyQualifiedJavaType("PageView<"+modelClassName+">"));
		
		method.addBodyLine("PageView<"+modelClassName+"> pageView = example.getPageView();");
		method.addBodyLine("if (pageView == null) {");
		method.addBodyLine("pageView = new PageView<>(1, 10);");
		method.addBodyLine("example.setPageView(pageView);");
		method.addBodyLine("}");
		method.addBodyLine("pageView.setQueryResult(this."+mapperName+".selectByExampleByPage(example));");
		method.addBodyLine("return pageView;");
		if (this.isRedisEnable) {
			method.addAnnotation("@Cacheable(value=\""+StringUtils.uncapitalize(modelClassName)+"\", key=\"'"+modelClassName+"Service_' + #root.methodName + '_'+ #example.toJson()\")");
		}
		method.addAnnotation("@Override");
		return method;
	}

}
