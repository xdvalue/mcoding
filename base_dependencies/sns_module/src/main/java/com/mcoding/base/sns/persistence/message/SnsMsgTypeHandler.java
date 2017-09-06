package com.mcoding.base.sns.persistence.message;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.mcoding.base.sns.bean.message.SnsMsg;
import com.mcoding.base.sns.service.message.SnsMsgService;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

public class SnsMsgTypeHandler implements TypeHandler<SnsMsg> {

	@Override
	public void setParameter(PreparedStatement ps, int i, SnsMsg parameter, JdbcType jdbcType) throws SQLException {
	}

	@Override
	public SnsMsg getResult(ResultSet rs, String columnName) throws SQLException {
		SnsMsgService snsMsgService = SpringContextHolder.getBean("snsMsgService");
		int id = rs.getInt(columnName);
		return snsMsgService.queryObjById(id);
	}

	@Override
	public SnsMsg getResult(ResultSet rs, int columnIndex) throws SQLException {
		SnsMsgService snsMsgService = SpringContextHolder.getBean("snsMsgService");
		int id = rs.getInt(columnIndex);
		return snsMsgService.queryObjById(id);
	}

	@Override
	public SnsMsg getResult(CallableStatement cs, int columnIndex) throws SQLException {
		SnsMsgService snsMsgService = SpringContextHolder.getBean("snsMsgService");
		int id = cs.getInt(columnIndex);
		return snsMsgService.queryObjById(id);
	}

}
