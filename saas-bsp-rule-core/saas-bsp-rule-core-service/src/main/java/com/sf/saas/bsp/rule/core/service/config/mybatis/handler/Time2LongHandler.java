package com.sf.saas.bsp.rule.core.service.config.mybatis.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;

/**
 * @Description:
 * @Author: 01405933
 * @CreateTime: 2022-08-04 11:04
 */
@MappedJdbcTypes(value = {JdbcType.DATE, JdbcType.TIMESTAMP})
@MappedTypes(Long.class)
public class Time2LongHandler extends BaseTypeHandler<Long> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Long aLong, JdbcType jdbcType) throws SQLException {
        if(jdbcType  == JdbcType.DATE) {
            preparedStatement.setDate(i, new Date(aLong));
        } else if(jdbcType  == JdbcType.TIMESTAMP) {
            preparedStatement.setTimestamp(i, new Timestamp(aLong));
        }
    }

    @Override
    public Long getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return toLong(resultSet.getObject(s));
    }

    @Override
    public Long getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return toLong(resultSet.getObject(i));
    }

    @Override
    public Long getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return toLong(callableStatement.getObject(i));
    }

    private Long toLong(Object value) {
        if(value instanceof Date) {
            return ((Date) value).getTime();
        } else if(value instanceof Timestamp) {
            return ((Timestamp) value).getTime();
        }
        return null;
    }

}
