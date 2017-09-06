package com.mcoding.base.utils.page;

/**
 * Created by LiBing on 2014/6/12.
 * MySql实现物理分页的方言实现
 */
public class MySQLDialect implements Dialect {
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
        if (offset > 0) {
            return sql + " limit " + offsetPlaceholder + "," + limitPlaceholder;
        } else {
            return sql + " limit " + limitPlaceholder;
        }
    }
}
