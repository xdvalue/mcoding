package com.mcoding.mybatis.generator.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.internal.db.ConnectionFactory;

public class TableCommentStorage {
	
	private static Pattern pattern = Pattern.compile("COMMENT=\'(.*)\'", Pattern.DOTALL);
	private static final TableCommentStorage instance = new TableCommentStorage();
	private Map<String, String> storage = new Hashtable<>();
	
	private TableCommentStorage(){
		super();
	}
	
	public static TableCommentStorage getInstance(){
		return instance;
	}
	
	public String get(IntrospectedTable introspectedTable) throws SQLException{
		String tableName = introspectedTable.getTableConfiguration().getTableName();
		if (StringUtils.isBlank(tableName)) {
			return null;
		}
		
		String tableComment = this.storage.get(tableName);
		if(StringUtils.isBlank(tableComment)){
			JDBCConnectionConfiguration configuration = introspectedTable.getContext().getJdbcConnectionConfiguration();
			Connection connection = ConnectionFactory.getInstance().getConnection(configuration);
			tableComment = this.queryCommentOfTable(connection, tableName);
			
		}
		
		return tableComment;
	}
	
	private String queryCommentOfTable(Connection connection, String tableName) throws SQLException{
		String tableComment = null;
		
		Statement statement = connection.createStatement();
		
		String querySql = "show create table " + tableName;
		try {
			ResultSet resultSet = statement.executeQuery(querySql);
			
			while (resultSet.next()) {
				String createTableSql = resultSet.getString(2);
				Matcher matcher = pattern.matcher(createTableSql);
				
				if(matcher.find()){
					tableComment = matcher.group(1);
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("查询 table["+tableName+"]的注解失败，原因是:" + e.getMessage());
		}
		
		
		if (StringUtils.isBlank(tableComment)) {
			tableComment = tableName;
		}
		
		return tableComment;
	}
	

}
