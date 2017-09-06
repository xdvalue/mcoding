package com.mcoding.mybatis.generator.plugins;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import com.mcoding.mybatis.generator.utils.TableCommentStorage;

public class ApiModelPlugin extends PluginAdapter {
	
	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		String tableComment = null;
		String tableName = introspectedTable.getTableConfiguration().getTableName();
		try {
			tableComment = TableCommentStorage.getInstance().get(introspectedTable);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		topLevelClass.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.ApiModel"));
		if (StringUtils.isNotBlank(tableComment)) {
			topLevelClass.addAnnotation("@ApiModel(value=\""+tableComment+"\")");
		}else{
			topLevelClass.addAnnotation("@ApiModel(value=\""+tableName+"\")");
		}
		
		return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
	}

	@Override
	public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		if (StringUtils.isNotBlank(introspectedColumn.getRemarks())) {
			String remark;
			try {
				remark = new String(introspectedColumn.getRemarks().getBytes(), "UTF-8");
				
				topLevelClass.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.ApiModelProperty"));
				field.addAnnotation("@ApiModelProperty(\""+ remark +"\")");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
		}
		return super.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
	}
	
	

}
