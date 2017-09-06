package com.mcoding.base.utils.page;

/**
 * Created by LiBing on 2014/6/12.
 * 实现各个数据物理分页的方言(本地化接口)
 */
public interface Dialect {
    /**
     * 是否支持分页
     * @return
     */
    boolean supportPage();

    /**
     * 是否支持占位符分页
     * @return
     */
    boolean supportPlaceholderPage();

    /**
     * 将sql变成分页sql语句,直接使用offset,limit的值作为占位符
     * @param sql  原始的Sql语句
     * @param offset 分页开始位置
     * @param limit  分页大小
     * @return 分页的SQL
     */
    String getPageSqlString(String sql, int offset, int limit);

    /**
     * 将sql变成分页sql语句,提供将offset及limit使用占位符(placeholder)替换.
     * @param sql 原始的Sql语句
     * @param offset 分页开始位置
     * @param offsetPlaceholder 分页开始位置占位符
     * @param limit  分页大小
     * @param limitPlaceholder 分页大小占位符
     * @return 分页的SQL
     */
    String getPageSqlPlaceholderString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder);
}
