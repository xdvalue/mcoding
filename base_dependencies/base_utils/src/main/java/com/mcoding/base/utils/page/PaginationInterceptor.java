package com.mcoding.base.utils.page;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mcoding.base.core.IExample;
import com.mcoding.base.core.PageView;

/**
 * Created by LiBing on 2014/6/12.
 * Mybatis 的物理分页拦截器实现
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class PaginationInterceptor implements Interceptor, Serializable {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger(PaginationInterceptor.class);
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory DEFALUT_REFLECTOR_FACTORY = new DefaultReflectorFactory();
    private static String DEFAULT_PAGE_SQL_ID = ".*Page$";
    private static Dialect dialect;
    private static String pageSqlId;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public Object intercept(Invocation inv) throws Throwable {
        //StatementHandler shandler = (StatementHandler) inv.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(inv.getTarget(), DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFALUT_REFLECTOR_FACTORY);

        // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)
        while (metaStatementHandler.hasGetter("h")) {
            Object object = metaStatementHandler.getValue("h");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFALUT_REFLECTOR_FACTORY);
        }
        // 分离最后一个代理对象的目标类
        while (metaStatementHandler.hasGetter("target")) {
            Object object = metaStatementHandler.getValue("target");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFALUT_REFLECTOR_FACTORY);
        }

        // property在mybatis settings文件内配置
        //Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
        //String pageSqlId = configuration.getVariables().getProperty("pageSqlId");

        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
        // 只重写需要分页的sql语句。通过MappedStatement的ID匹配，默认重写以Page结尾的MappedStatement的sql
        if (mappedStatement.getId().matches(pageSqlId)) {
            BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
            Object paramObj = boundSql.getParameterObject();
            if (paramObj == null) {
                //throw new NullPointerException("parameterObject is null!");
                return inv.proceed();
            } else {
                PageView<?> pageView = null;
                //参数继承自PageView
                if(paramObj instanceof PageView){
                    pageView = (PageView<?>) paramObj;
                } else if(paramObj instanceof Map){ //参数Map中包含PageView
                    for (Map.Entry entry : (Set<Map.Entry>) ((Map) paramObj).entrySet()) {
                        if (entry.getValue() instanceof IExample) {
                            MetaObject paramObjProxy = MetaObject.forObject(entry.getValue(), DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFALUT_REFLECTOR_FACTORY);
                            pageView = (PageView<?>) paramObjProxy.getValue("pageView");
                            break;
                        }

                        if (entry.getValue() instanceof PageView) {
                            pageView = (PageView<?>) entry.getValue();
                            break;
                        }
                    }
                } else { //参数属性中包含ageView
                    MetaObject paramObjProxy = MetaObject.forObject(paramObj, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFALUT_REFLECTOR_FACTORY);
                    pageView = (PageView<?>) paramObjProxy.getValue("pageView");
                }

                if(pageView == null){
                    return inv.proceed();
                }

                String originalSql = boundSql.getSql();
                if(logger.isDebugEnabled()){
                    logger.debug("originalSql======>" + originalSql);
                }

                setRowCount(inv, mappedStatement, boundSql, paramObj, pageView);

                // 重写成分页sql
                String pageSql = dialect.getPageSqlString(originalSql, pageView.getStartRowNo(), pageView.getPageSize());
//                if(logger.isDebugEnabled()){
//                    logger.debug("generatePageSql======>" + pageSql);
//                }

                metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);

                // 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
                metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
                metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
            }
        }
        // 将执行权交给下一个拦截器
        return inv.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties prop) {
        // 获取数据库方言
        String dialectName = prop.getProperty("dialect");
        if (dialectName == null || "".equals(dialectName)) {
            logger.error("dialect property is not found!");
        } else {
            try {
                dialect = (Dialect) Class.forName(dialectName).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(dialect + ", init fail!\n" + e);
            }
        }
        //根据id来区分是否需要分页
        pageSqlId = prop.getProperty("pageSqlId");
        if (null == pageSqlId || "".equals(pageSqlId)) {
            logger.warn("Property pageSqlId is not setted, use default '.*Page$' ");
            pageSqlId = DEFAULT_PAGE_SQL_ID;
        }
    }

    //设置数据的总行数
    private void setRowCount(Invocation invk, MappedStatement mappedStatement, BoundSql boundSql, Object paramObj, PageView<?> pageView) throws SQLException {
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        try {
            String sql = boundSql.getSql().trim();
            Connection connection = (Connection) invk.getArgs()[0];
            String countSql = "select count(1) from (" + sql + ") tmp_count"; // 记录统计
            countStmt = connection.prepareStatement(countSql);
            MetaObject boundSqlPoxy = MetaObject.forObject(boundSql, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFALUT_REFLECTOR_FACTORY);
            boundSqlPoxy.setValue("sql", countSql);
//            if(logger.isDebugEnabled()){
//                logger.debug("queryRowCountSql======>" + boundSql.getSql());
//            }
            DefaultParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, paramObj, boundSql);
            parameterHandler.setParameters(countStmt);
            rs = countStmt.executeQuery();
            int count = 0;
            if (rs.next()) {
                count = ((Number) rs.getObject(1)).intValue();
            }
            pageView.setRowCount(count);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
                logger.error("ResultSet closed error:", e);
            }
            try {
                countStmt.close();
            } catch (Exception e) {
                logger.error("PreparedStatement closed error:", e);
            }
        }
    }
}
