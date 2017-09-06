package com.mcoding.base.order.service.order;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.mcoding.base.order.bean.order.OrderProduct;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

public class OrderProductHandler implements TypeHandler<List<OrderProduct>> {
	
	public OrderProductHandler(){
		super();
	}
	
	@Override
	public void setParameter(PreparedStatement ps, int i, List<OrderProduct> parameter, JdbcType jdbcType)
			throws SQLException {
		return;
	}

	@Override
	public List<OrderProduct> getResult(ResultSet rs, String columnName) throws SQLException {
		return SpringContextHolder.getOneBean(OrderProductService.class).queryByOrderId(rs.getInt(columnName));
	}

	@Override
	public List<OrderProduct> getResult(ResultSet rs, int columnIndex) throws SQLException {
		return SpringContextHolder.getOneBean(OrderProductService.class).queryByOrderId(rs.getInt(columnIndex));
	}

	@Override
	public List<OrderProduct> getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return SpringContextHolder.getOneBean(OrderProductService.class).queryByOrderId(cs.getInt(columnIndex));
	}

	

}
