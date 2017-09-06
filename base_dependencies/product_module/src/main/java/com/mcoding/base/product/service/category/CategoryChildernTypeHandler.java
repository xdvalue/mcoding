package com.mcoding.base.product.service.category;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.mcoding.base.product.bean.category.ProductCategory;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

public class CategoryChildernTypeHandler implements TypeHandler<List<ProductCategory>>{

	@Override
	public void setParameter(PreparedStatement ps, int i, List<ProductCategory> parameter, JdbcType jdbcType)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductCategory> getResult(ResultSet rs, String columnName) throws SQLException {
		return SpringContextHolder.getOneBean(ProductCategoryService.class).queryChildern(rs.getInt(columnName));
	}

	@Override
	public List<ProductCategory> getResult(ResultSet rs, int columnIndex) throws SQLException {
		return SpringContextHolder.getOneBean(ProductCategoryService.class).queryChildern(rs.getInt(columnIndex));
	}

	@Override
	public List<ProductCategory> getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return SpringContextHolder.getOneBean(ProductCategoryService.class).queryChildern(cs.getInt(columnIndex));
	}

}
