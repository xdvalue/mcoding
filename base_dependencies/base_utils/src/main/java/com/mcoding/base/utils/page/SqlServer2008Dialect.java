package com.mcoding.base.utils.page;

public class SqlServer2008Dialect implements Dialect {

	@Override
	public boolean supportPage() {
		return true;
	}

	@Override
	public boolean supportPlaceholderPage() {
		return true;
	}

	@Override
	public String getPageSqlString(String sql, int offset, int limit) {
		return getPageSqlPlaceholderString(sql, offset, String.valueOf(offset), limit, String.valueOf(limit));
	}

	@Override
	public String getPageSqlPlaceholderString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		int topNum = offset + limit;
		String pageSql = "select * "+
                "from ( " +
                  "select row_number() over(order by tempColumn) tempRowNumber , * "+
                  "from (" + 
                    "select top "+topNum+" tempColumn=0,* from ( "+sql+" ) result "+
                  ") t " +
                 ")tt " +
                 "where tempRowNumber>" + offset;
		return pageSql;
//		String sql = "SELECT TOP " + limit + " * " + 
//	                 "FROM "+ 
//                     "( " +
//                       "SELECT ROW_NUMBER() OVER (ORDER BY tmpC) AS RowNumber,* FROM ("+sql+") " +
//                     ") A" +
//                     "WHERE RowNumber > "+offset;
//		return sql;
	}

}
