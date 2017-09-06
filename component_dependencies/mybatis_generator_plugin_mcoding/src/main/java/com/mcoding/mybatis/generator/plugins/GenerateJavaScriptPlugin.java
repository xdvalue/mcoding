package com.mcoding.mybatis.generator.plugins;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

public class GenerateJavaScriptPlugin extends PluginAdapter {

	private static final String targetPackage = "webapp"+File.separator+"resources"+File.separator+"js"+File.separator+"custom";

	private String targetProject;
	// private String comment;
	private String recordTemplate = "{ \"sTitle\": \"%s\", \"mData\": \"%s\"},";
	private String modelName;
	private String moduleName;

	@Override
	public boolean validate(List<String> warnings) {

		targetProject = this.properties.getProperty("targetProject");
		if (StringUtils.isBlank(targetProject)) {
			warnings.add("JS 生成失败， targetProject 配置失败");
			return false;
		}

		moduleName = this.properties.getProperty("moduleName");
		if (StringUtils.isBlank(moduleName)) {
			warnings.add("JSP 生成失败， moduleName 配置失败");
			return false;
		}

		return true;
	}

	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		try {
			// comment =
			// TableCommentStorage.getInstance().get(introspectedTable);
			modelName = introspectedTable.getTableConfiguration().getDomainObjectName();
			modelName = modelName.replaceFirst(modelName.substring(0, 1), modelName.substring(0, 1).toLowerCase());

			String resource = targetProject + File.separator + targetPackage + File.separator + moduleName + File.separator + modelName;

			File file = new File(resource);
			if (!file.exists()) {
				file.delete();
			}
			file.mkdirs();

			// 生成mainJS
			generateMainJs(introspectedTable, resource);
			// 生成addJS
			generateAddJs(introspectedTable, resource);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return super.contextGenerateAdditionalJavaFiles(introspectedTable);
	}

	/**
	 * 生成mainJS
	 * 
	 * @param introspectedTable
	 */
	private void generateMainJs(IntrospectedTable introspectedTable, String dirResource) {
		List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
		StringBuilder recordBuilder = new StringBuilder();
		for (IntrospectedColumn column : columns) {
			String remarks = column.getRemarks();
			String property = column.getJavaProperty();

			remarks = StringUtils.isNotBlank(remarks) ? remarks : column.getActualColumnName();
			String record = String.format(recordTemplate, remarks, property);
			recordBuilder.append("\t\t\t" + record + "\n");
		}

		InputStream in = GenerateJavaScriptPlugin.class.getClassLoader().getResourceAsStream("template/main_js.txt");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			dirResource = dirResource + File.separator + "main.js";
			new File(dirResource).createNewFile();

			PrintWriter pw = new PrintWriter(dirResource);
			String line = "";
			while ((line = br.readLine()) != null) {
				line = line.replaceAll("/#dataColumns#/", recordBuilder.toString());
				line = line.replaceAll("/#modelName#/", modelName);
				pw.println(line);
			}
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void generateAddJs(IntrospectedTable introspectedTable, String dirResource) {
		InputStream in = GenerateJavaScriptPlugin.class.getClassLoader().getResourceAsStream("template/add_js.txt");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			dirResource = dirResource + File.separator + "add.js";
			new File(dirResource).createNewFile();

			PrintWriter pw = new PrintWriter(dirResource);
			String line = "";
			while ((line = br.readLine()) != null) {
				line = line.replaceAll("/#modelName#/", modelName);
				pw.println(line);
			}
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
