package com.samton.platform.framework.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class IPTypeHandler extends BaseTypeHandler<Object> {

	    @Override
	    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter,
	            JdbcType jdbcType) throws SQLException {
		 	ps.setObject(i, parameter);
	    }

	    @Override
	    public Object getNullableResult(ResultSet rs, String columnName)
	            throws SQLException {
	        return rs.getString(columnName);
	    }

	    @Override
	    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
	        return rs.getString(columnIndex);
	    }

	    @Override
	    public Object getNullableResult(CallableStatement cs, int columnIndex)
	            throws SQLException {
	    	return cs.getString(columnIndex);
	    }

}
