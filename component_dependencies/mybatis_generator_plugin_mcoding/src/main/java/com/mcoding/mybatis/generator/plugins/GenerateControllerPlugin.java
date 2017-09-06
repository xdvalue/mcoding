package com.mcoding.mybatis.generator.plugins;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import com.mcoding.mybatis.generator.utils.ServiceGenerateDataStorage;
import com.mcoding.mybatis.generator.utils.TableCommentStorage;

public class GenerateControllerPlugin extends PluginAdapter {

	private String targetPackage;
	private String targetProject;
	private String serviceTargetPackage;
	private String baseUrlPath;
	private String moduleName;

	@Override
	public boolean validate(List<String> warnings) {
		String targetPackage = this.properties.getProperty("targetPackage");
		if (StringUtils.isBlank(targetPackage)) {
			warnings.add("Controller 生成失败， targetPackage 配置失败");
			return false;
		}

		if (!targetPackage.matches("^(\\w+)(\\.\\w*)*\\w$")) {
			warnings.add("Controller 生成失败， targetPackage[" + targetPackage + "] 格式错误");
			return false;
		}

		String targetProject = this.properties.getProperty("targetProject");
		if (StringUtils.isBlank(targetProject)) {
			if (StringUtils.isBlank(targetPackage)) {
				warnings.add("Controller 生成失败， targetProject 配置失败");
				return false;
			}
		}
		
		String baseUrlPath = this.properties.getProperty("baseUrlPath");
		
		String moduleName = this.properties.getProperty("moduleName");
		if (StringUtils.isBlank(moduleName)) {
			moduleName = "";
		}else {
			moduleName = moduleName + "/";
		}

		this.targetPackage = targetPackage;
		this.targetProject = targetProject;
		this.baseUrlPath = baseUrlPath;
		this.moduleName = moduleName;
		return true;
	}

	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		String modelName = introspectedTable.getTableConfiguration().getDomainObjectName();
		String tableName = introspectedTable.getTableConfiguration().getTableName();

		this.serviceTargetPackage = ServiceGenerateDataStorage.getInstance().getServicePackage(tableName);
		if (StringUtils.isBlank(serviceTargetPackage)) {
			throw new NullPointerException("controller 生成失败，因为关联的service未生成，请检查插件[GenerateServicePlugin]配置的顺序。");
		}

		GeneratedJavaFile controller = null;
		try {
			controller = new GeneratedJavaFile(this.createController(introspectedTable, modelName), this.targetProject,
					this.context.getJavaFormatter());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		List<GeneratedJavaFile> list = new ArrayList<>(2);
		list.add(controller);
		return list;
	}

	private CompilationUnit createController(IntrospectedTable introspectedTable, String modelClassName)
			throws SQLException {
		String controllerFullName = this.targetPackage + "." + modelClassName + "Controller";
		String tableComment = TableCommentStorage.getInstance().get(introspectedTable);
		if (StringUtils.isBlank(tableComment)) {
			tableComment = modelClassName;
		}

		FullyQualifiedJavaType controllerType = new FullyQualifiedJavaType(controllerFullName);
		TopLevelClass controller = new TopLevelClass(controllerType);
		controller.setVisibility(JavaVisibility.PUBLIC);

		controller.addAnnotation("@Api(value=\"" + tableComment + "\")");
		controller.addAnnotation("@Controller");
		
		if(StringUtils.isBlank(this.baseUrlPath)){
			this.baseUrlPath = StringUtils.uncapitalize(modelClassName);
		}
		controller.addAnnotation("@RequestMapping(\"" + this.baseUrlPath + "\")");

		List<FullyQualifiedJavaType> importList = this.getImportList(introspectedTable, modelClassName);
		for (FullyQualifiedJavaType importItem : importList) {
			controller.addImportedType(importItem);
		}

		this.addFieldsAndMethod(controller, introspectedTable, modelClassName);

		return controller;
	}

	private void addFieldsAndMethod(TopLevelClass controller, IntrospectedTable introspectedTable,
			String modelClassName) throws SQLException {
		FullyQualifiedJavaType serviceType = this.getServiceType(introspectedTable, modelClassName);

		String serviceName = StringUtils.uncapitalize(modelClassName) + "Service";
		Field service = new Field(serviceName, serviceType);
		service.setVisibility(JavaVisibility.PROTECTED);

		service.addAnnotation("@Resource");
		controller.addField(service);

		controller.addMethod(this.methodToAddView(modelClassName));
		controller.addMethod(this.methodToMainView(modelClassName));
		controller.addMethod(this.methodToUpdateViewById(modelClassName));
		
		controller.addMethod(this.methodCreate(introspectedTable, modelClassName));
		controller.addMethod(this.methodEdit(introspectedTable, modelClassName));
		controller.addMethod(this.methodDeleteById(introspectedTable, modelClassName));
		controller.addMethod(this.methodFindByPage(introspectedTable, modelClassName));
//		controller.addMethod(this.fin);
		

	}

	private Method methodToAddView(String modelClassName) {
		/*
		 * @ApiIgnore
		 * 
		 * @RequestMapping("service/toAddView") public ModelAndView toAddView()
		 * { ModelAndView view = new ModelAndView();
		 * view.setViewName("dictionary/dicGroup/toAddView"); return view; }
		 */

		Method method = new Method();
		method.setName("toAddView");
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(new FullyQualifiedJavaType("ModelAndView"));
		
		String path = this.moduleName + StringUtils.uncapitalize(modelClassName);

		method.addBodyLine("return new ModelAndView(\"" + path + "/toAddView\");");

		method.addAnnotation("@ApiIgnore");
		method.addAnnotation("@RequestMapping(\"service/toAddView\")");
		return method;
	}
	
	private Method methodToMainView(String modelClassName){
		/*@ApiIgnore
		@RequestMapping("service/toListPageView")
		public ModelAndView toMainView() {
			ModelAndView view = new ModelAndView();
			view.setViewName("dictionary/dicGroup/toMainView");
			return view;
		}*/
		
		Method method = new Method();
		method.setName("toMainView");
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(new FullyQualifiedJavaType("ModelAndView"));

		String path = this.moduleName + StringUtils.uncapitalize(modelClassName);
		method.addBodyLine("return new ModelAndView(\"" + path + "/toMainView\");");

		method.addAnnotation("@ApiIgnore");
		method.addAnnotation("@RequestMapping(\"service/toMainView\")");
		return method;
	}
	
	public Method methodToUpdateViewById(String modelClassName) {
		/*
		@ApiIgnore
		@RequestMapping("service/toUpdateViewById")
		public ModelAndView toDicGroupById(int id) {
			ModelAndView view = new ModelAndView();
			
			DicGroup dicGroup = this.dicGroupService.queryObjById(id);
			
			view.addObject("dicGroup", dicGroup);
			view.setViewName("dictionary/dicGroup/toAddView");
			return view;
		}*/
		
		Method method = new Method();
		method.setName("toUpdateViewById");
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(new FullyQualifiedJavaType("ModelAndView"));
		
		Parameter parameter = new Parameter(new FullyQualifiedJavaType("int"), "id");
		method.addParameter(parameter);

		String littleModel = StringUtils.uncapitalize(modelClassName);
		method.addBodyLine("ModelAndView view = new ModelAndView();");
		method.addBodyLine(modelClassName +" "+littleModel+" = this."+littleModel+"Service.queryObjById(id);");
		method.addBodyLine("view.addObject(\""+littleModel+"\", "+littleModel+");");
		
		String path = this.moduleName + littleModel;
		method.addBodyLine("view.setViewName(\""+path+"/toAddView\");");
		method.addBodyLine("return view;");
		
		method.addAnnotation("@ApiIgnore");
		method.addAnnotation("@RequestMapping(\"service/toUpdateViewById\")");
		return method;
	}
	
	private Method methodCreate(IntrospectedTable introspectedTable, String modelClassName) throws SQLException{
		/*
		@ApiOperation(httpMethod="POST", value="创建字典组")
		@RequestMapping("service/create")
		@ResponseBody
		public JsonResult<String> create(@RequestBody DicGroup dicGroup) {
			dicGroupValidtion(dicGroup);
			
			JsonResult<String> result = new JsonResult<>();
			this.dicGroupService.addObj(dicGroup);
			result.setData(null);
			result.setMsg("ok");
			result.setStatus("00");
			return result;
		}*/
		String littleModel = StringUtils.uncapitalize(modelClassName);
		
		Method method = new Method();
		method.setName("create");
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(new FullyQualifiedJavaType("JsonResult<String>"));
		
		Parameter parameter = new Parameter(this.getModelType(introspectedTable, modelClassName), littleModel);
		parameter.addAnnotation("@RequestBody");
		method.addParameter(parameter);

		method.addBodyLine("JsonResult<String> result = new JsonResult<>();");
		method.addBodyLine("this."+littleModel+"Service.addObj("+littleModel+");");
		method.addBodyLine("result.setData(null);");
		method.addBodyLine("result.setMsg(\"ok\");");
		method.addBodyLine("result.setStatus(\"00\");");
		method.addBodyLine("return result;");
		
		String tableComment = TableCommentStorage.getInstance().get(introspectedTable);
		if(StringUtils.isBlank(tableComment)){
			tableComment = introspectedTable.getTableConfiguration().getDomainObjectName();
		}
		method.addAnnotation("@ApiOperation(httpMethod=\"POST\", value=\"创建"+tableComment+"\")");
		method.addAnnotation("@RequestMapping(\"service/create\")");
		method.addAnnotation("@ResponseBody");
		return method;
	}
	
	private Method methodEdit(IntrospectedTable introspectedTable, String modelClassName) throws SQLException{
		/*
		@ApiOperation(httpMethod="POST", value="编辑字典组")
		@RequestMapping("service/edit")
		@ResponseBody
		public JsonResult<String> edit(@RequestBody DicGroup dicGroup) {
		    if (snsBanner.getId() == null || snsBanner.getId() <=0) {
		    	throw new CommonException("id 为空，保存失败");
		    }
			dicGroupValidtion(dicGroup);
			
			JsonResult<String> result = new JsonResult<>();
			this.dicGroupService.modifyObj(dicGroup);
			result.setData(null);
			result.setMsg("ok");
			result.setStatus("00");
			return result;
		}*/
		
		String littleModel = StringUtils.uncapitalize(modelClassName);

		Method method = new Method();
		method.setName("edit");
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(new FullyQualifiedJavaType("JsonResult<String>"));
		
		Parameter parameter = new Parameter(this.getModelType(introspectedTable, modelClassName), littleModel);
		parameter.addAnnotation("@RequestBody");
		method.addParameter(parameter);
		
		method.addBodyLine("if ("+littleModel+".getId() == null || "+littleModel+".getId() <=0) {");
		method.addBodyLine("throw new CommonException(\"id 为空，保存失败\");");
		method.addBodyLine("}");

		method.addBodyLine("JsonResult<String> result = new JsonResult<>();");
		method.addBodyLine("this."+littleModel+"Service.modifyObj("+littleModel+");");
		method.addBodyLine("result.setData(null);");
		method.addBodyLine("result.setMsg(\"ok\");");
		method.addBodyLine("result.setStatus(\"00\");");
		method.addBodyLine("return result;");
		
		String tableComment = TableCommentStorage.getInstance().get(introspectedTable);
		if(StringUtils.isBlank(tableComment)){
			tableComment = introspectedTable.getTableConfiguration().getDomainObjectName();
		}
		method.addAnnotation("@ApiOperation(httpMethod=\"POST\", value=\"编辑"+tableComment+"\")");
		method.addAnnotation("@RequestMapping(\"service/edit\")");
		method.addAnnotation("@ResponseBody");
		return method;
	}
	
	private Method methodDeleteById(IntrospectedTable introspectedTable, String modelClassName) throws SQLException{
		/*
		@ApiOperation(httpMethod="POST", value="删除字典组")
		@RequestMapping("service/deleteById")
		@ResponseBody
		public JsonResult<String> deleteById(int id){
			JsonResult<String> result = new JsonResult<>();
			this.dicGroupService.deleteObjById(id);
			result.setData(null);
			result.setMsg("ok");
			result.setStatus("00");
			return result;
		}*/
		
		String littleModel = StringUtils.uncapitalize(modelClassName);

		Method method = new Method();
		method.setName("deleteById");
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(new FullyQualifiedJavaType("JsonResult<String>"));
		
		Parameter parameter = new Parameter(new FullyQualifiedJavaType("int"), "id");
		method.addParameter(parameter);

		method.addBodyLine("JsonResult<String> result = new JsonResult<>();");
		method.addBodyLine("this."+littleModel+"Service.deleteObjById(id);");
		method.addBodyLine("result.setData(null);");
		method.addBodyLine("result.setMsg(\"ok\");");
		method.addBodyLine("result.setStatus(\"00\");");
		method.addBodyLine("return result;");
		
		String tableComment = TableCommentStorage.getInstance().get(introspectedTable);
		if(StringUtils.isBlank(tableComment)){
			tableComment = introspectedTable.getTableConfiguration().getDomainObjectName();
		}
		method.addAnnotation("@ApiOperation(httpMethod=\"POST\", value=\"删除"+tableComment+"\")");
		method.addAnnotation("@RequestMapping(\"service/deleteById\")");
		method.addAnnotation("@ResponseBody");
		return method;
		
	}
	
	private Method methodFindByPage(IntrospectedTable introspectedTable, String modelClassName) throws SQLException{
		/*
		@ApiOperation(httpMethod="GET", value="查询字典组")
		@RequestMapping("service/findByPage")
		@ResponseBody
		public PageView<DicGroup> findByPage(
				@ApiParam(name="iDisplayStart",defaultValue="0") String iDisplayStart, 
				@ApiParam(name="iDisplayLength",defaultValue="10") String iDisplayLength, 
				@ApiParam(name="sSearch", value="查询条件") String sSearch){
			if (StringUtils.isBlank(iDisplayStart) || !StringUtils.isNumeric(iDisplayStart)) {
				iDisplayStart = "0";
			}
			if (StringUtils.isBlank(iDisplayLength) || !StringUtils.isNumeric(iDisplayLength)) {
				iDisplayLength = "10";
			}

			PageView<DicGroup> pageView = new PageView<>(iDisplayStart, iDisplayLength);

			DicGroupExample example = new DicGroupExample();
			example.setPageView(pageView);
			
			if (StringUtils.isNotBlank(sSearch)) {
				DicGroupExample.Criteria cri1 = example.createCriteria();
				cri1.andNameLike( sSearch + "%");
				DicGroupExample.Criteria cri2 = example.or();
				cri2.andCodeLike(sSearch + "%");
			}
			
			return this.dicGroupService.queryObjByPage(example);
		}*/
		String littleModel = StringUtils.uncapitalize(modelClassName);
		
		Method method = new Method();
		method.setName("findByPage");
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(new FullyQualifiedJavaType("PageView<"+modelClassName+">"));
		
		Parameter p1 = new Parameter(new FullyQualifiedJavaType("String"), "iDisplayStart");
		p1.addAnnotation("@ApiParam(value=\"分页索引\",defaultValue=\"0\")");
		p1.addAnnotation("@RequestParam(defaultValue=\"0\")");
		
		method.addParameter(p1);
		
		Parameter p2 = new Parameter(new FullyQualifiedJavaType("String"), "iDisplayLength");
		p2.addAnnotation("@ApiParam(value=\"每页的数量\",defaultValue=\"10\")");
		p2.addAnnotation("@RequestParam(defaultValue=\"10\")");
		
		method.addParameter(p2);
		
		Parameter p3 = new Parameter(new FullyQualifiedJavaType("String"), "sSearch");
		p3.addAnnotation("@ApiParam(value=\"查询条件\")");
		
		method.addParameter(p3);
		
		method.addBodyLine("PageView<"+modelClassName+"> pageView = new PageView<>(iDisplayStart, iDisplayLength);");
		method.addBodyLine(modelClassName +"Example example = new "+modelClassName+"Example();");
		method.addBodyLine("example.setPageView(pageView);");
		method.addBodyLine("if (StringUtils.isNotBlank(sSearch)) {");
//		method.addBodyLine( modelClassName + "Example.Criteria cri1 = example.createCriteria();");
//		method.addBodyLine("cri1.andNameLike( sSearch + \"%\");");
//		method.addBodyLine(modelClassName +"Example.Criteria cri2 = example.or();");
//		method.addBodyLine("cri2.andCodeLike(sSearch + \"%\");");
		method.addBodyLine("// TODO Auto-generated method stub");
		method.addBodyLine("}");
		method.addBodyLine("return this."+ littleModel +"Service.queryObjByPage(example);");
		
		String tableComment = TableCommentStorage.getInstance().get(introspectedTable);
		if(StringUtils.isBlank(tableComment)){
			tableComment = introspectedTable.getTableConfiguration().getDomainObjectName();
		}
		
		method.addAnnotation("@ApiOperation(httpMethod=\"GET\", value=\"查询"+tableComment+"\")");
		method.addAnnotation("@RequestMapping(\"service/findByPage\")");
		method.addAnnotation("@ResponseBody");
		return method;
	}

	private List<FullyQualifiedJavaType> getImportList(IntrospectedTable introspectedTable, String modelClassName) {
		List<FullyQualifiedJavaType> list = new ArrayList<>();

		list.add(new FullyQualifiedJavaType("java.util.List"));
		list.add(new FullyQualifiedJavaType("javax.annotation.Resource"));
		list.add(new FullyQualifiedJavaType("org.apache.commons.lang.StringUtils"));
		list.add(new FullyQualifiedJavaType("org.springframework.stereotype.Controller"));
		list.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestBody"));
		list.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestMapping"));
		list.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.ResponseBody"));
		list.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestParam"));
		list.add(new FullyQualifiedJavaType("org.springframework.web.servlet.ModelAndView"));
		list.add(new FullyQualifiedJavaType("com.mcoding.base.core.JsonResult"));
		list.add(this.getModelType(introspectedTable, modelClassName));
		list.add(this.getExampleType(introspectedTable, modelClassName));
		list.add(new FullyQualifiedJavaType("com.mcoding.base.core.PageView"));
		list.add(this.getServiceType(introspectedTable, modelClassName));
		list.add(new FullyQualifiedJavaType("com.mcoding.base.core.CommonException"));
		list.add(new FullyQualifiedJavaType("io.swagger.annotations.Api"));
		list.add(new FullyQualifiedJavaType("io.swagger.annotations.ApiOperation"));
		list.add(new FullyQualifiedJavaType("io.swagger.annotations.ApiParam"));
		list.add(new FullyQualifiedJavaType("springfox.documentation.annotations.ApiIgnore"));
		return list;
	}

	private FullyQualifiedJavaType getModelType(IntrospectedTable introspectedTable, String modelClassName) {

		String beanPackageStr = introspectedTable.getContext().getJavaModelGeneratorConfiguration().getTargetPackage();
		String fullModelClassName = beanPackageStr + "." + modelClassName;

		FullyQualifiedJavaType modelType = new FullyQualifiedJavaType(fullModelClassName);
		return modelType;
	}

	private FullyQualifiedJavaType getExampleType(IntrospectedTable introspectedTable, String modelClassName) {
		String beanPackageStr = introspectedTable.getContext().getJavaModelGeneratorConfiguration().getTargetPackage();
		String exampleName = modelClassName + "Example";

		String fullExampleNameStr = beanPackageStr + "." + exampleName;
		FullyQualifiedJavaType exampleType = new FullyQualifiedJavaType(fullExampleNameStr);
		return exampleType;
	}

	private FullyQualifiedJavaType getServiceType(IntrospectedTable introspectedTable, String modelClassName) {
		String serviceFullName = this.serviceTargetPackage + "." + modelClassName + "Service";
		FullyQualifiedJavaType mapperType = new FullyQualifiedJavaType(serviceFullName);
		return mapperType;
	}

}
