package com.mcoding.mybatis.generator.plugins;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import com.mcoding.mybatis.generator.utils.TableCommentStorage;

public class GenerateJspPlugin extends PluginAdapter {

	private static final String targetPackage = "webapp"+File.separator+"WEB-INF"+File.separator+"view";
	private String targetProject;
	private String moduleName;
	private String modelComment;
	private String modelName;

	@Override
	public boolean validate(List<String> warnings) {

		this.targetProject = this.properties.getProperty("targetProject");
		if (StringUtils.isBlank(targetProject)) {
			warnings.add("JSP 生成失败， targetProject 配置失败");
			return false;
		}

		this.moduleName = this.properties.getProperty("moduleName");
		if (StringUtils.isBlank(this.moduleName)) {
			warnings.add("JSP 生成失败， moduleName 配置失败");
			return false;
		}

		return true;
	}

	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		try {
			modelComment = TableCommentStorage.getInstance().get(introspectedTable);
			modelName = introspectedTable.getTableConfiguration().getDomainObjectName();
			modelName = StringUtils.uncapitalize(modelName);

			String resource = targetProject + File.separator + targetPackage + File.separator + moduleName + File.separator + modelName;

			File file = new File(resource);
			if (!file.exists()) {
				file.delete();
			}
			file.mkdirs();

			// 生成mainJSP
			generateMainViewJsp(introspectedTable, resource);

			// 生成toAddViewJSP
			generateToAddViewJsp(introspectedTable, resource);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return super.contextGenerateAdditionalJavaFiles(introspectedTable);
	}

	private void generateMainViewJsp(IntrospectedTable introspectedTable, String dirResource) {
		InputStream in = GenerateJspPlugin.class.getClassLoader().getResourceAsStream("template/toMainView_jsp.txt");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			dirResource = dirResource + File.separator + "toMainView.jsp";
			new File(dirResource).createNewFile();

			PrintWriter pw = new PrintWriter(dirResource);
			String line = "";
			while ((line = br.readLine()) != null) {
				line = line.replaceAll("/#modelName#/", modelName);
				line = line.replaceAll("/#commentName#/", modelComment);
				line = line.replaceAll("/#moduleName#/", moduleName);
				line = line.replaceAll("/#baseJspPackage#/", modelName);

				pw.println(line);
			}
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void generateToAddViewJsp(IntrospectedTable introspectedTable, String dirResource) {
		String inputHtml = this.getInputHtml(introspectedTable);
		
		InputStream in = GenerateJspPlugin.class.getClassLoader().getResourceAsStream("template/toAddView_jsp.txt");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			dirResource = dirResource + File.separator + "toAddView.jsp";
			new File(dirResource).createNewFile();

			PrintWriter pw = new PrintWriter(dirResource);
			String line = "";
			while ((line = br.readLine()) != null) {
				modelName = modelName.replaceFirst(modelName.substring(0, 1), modelName.substring(0, 1).toLowerCase());
				
				line = line.replaceAll("/#modelName#/", modelName);
				line = line.replaceAll("/#moduleName#/", moduleName);
				line = line.replaceAll("/#commentName#/", modelComment);
				line = line.replaceAll("/#inputHtml#/", inputHtml);

				pw.println(line);
			}
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getInputHtml(IntrospectedTable introspectedTable){
		List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
		StringBuffer inputHtml = new StringBuffer();
		
		for (int i = 0; i < columns.size(); i++) {
			inputHtml.append(this.buildFromTemplate(columns.get(i), this.modelName));
			inputHtml.append("\n");
		}
		return inputHtml.toString();
	}
	
	private String buildFromTemplate(IntrospectedColumn introspectedColumn, String modelName){
		String remark = introspectedColumn.getRemarks();
		String field = introspectedColumn.getJavaProperty();
		remark = StringUtils.isBlank(remark) ? introspectedColumn.getActualColumnName() : remark;
		
		String space = "						";
		String template = space +
		        "<div class=\"form-group\"> " + "\n" + space +
		  		"    <label class=\"col-md-3 control-label\">"+remark +space + 
				"    </label>" + "\n" +space + 
				"    <div class=\"col-md-9\">" + "\n" +space + 
				"   	<input type=\"text\" name=\""+ field +"\" value=\"\\$\\{"+modelName+"."+field+"\\}\""+ "\n" +space + 
				"   		class=\"form-control input-inline input-medium\""+ "\n" +space + 
				"   		placeholder=\"请输入"+remark+"\" required>"+ "\n" +space + 
				"    </div>" + "\n" +space + 
		        "</div>";
		
		return template;
	}

}
