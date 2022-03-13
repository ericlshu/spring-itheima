package com.eric.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Description : 自定义Date类型数据转换器
 *
 * @author Eric L SHU
 * @date 2022-03-13 10:00
 */
public class DateTypeHandler extends BaseTypeHandler<Date> {

    /**
     * 将java类型转换成数据库需要的类型
     *
     * @param ps        PreparedStatement
     * @param i         index of parameter
     * @param parameter parameter
     * @param jdbcType  jdbcType
     * @throws SQLException SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException
    {
        ps.setLong(i, parameter.getTime());
    }

    /**
     * 将数据库中类型转换成java类型
     *
     * @param rs         查询出的结果集
     * @param columnName 要转换的字段名称
     * @return Date类型数据
     * @throws SQLException SQLException
     */
    @Override
    public Date getNullableResult(ResultSet rs, String columnName) throws SQLException
    {
        return new Date(rs.getLong(columnName));
    }

    /**
     * 将数据库中类型转换成java类型
     *
     * @param rs          查询出的结果集
     * @param columnIndex 要转换的字段位置
     * @return Date类型数据
     * @throws SQLException SQLException
     */
    @Override
    public Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException
    {
        return new Date(rs.getLong(columnIndex));
    }

    /**
     * 将数据库中类型转换成java类型
     *
     * @param cs          public interface CallableStatement extends PreparedStatement
     *                    用于执行SQL存储过程的界面。JDBC API提供了存储过程SQL转义语法，允许以标准方式为所有RDBMS调用存储过程。
     * @param columnIndex 要转换的字段位置
     * @return Date类型数据
     * @throws SQLException SQLException
     */
    @Override
    public Date getNullableResult(CallableStatement cs, int columnIndex) throws SQLException
    {
        return new Date(cs.getLong(columnIndex));
    }
}
