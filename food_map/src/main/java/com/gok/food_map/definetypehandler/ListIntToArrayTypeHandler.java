package com.gok.food_map.definetypehandler;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes({List.class})
public class ListIntToArrayTypeHandler extends BaseTypeHandler<List<Integer>> {
    public ListIntToArrayTypeHandler() {
    }

    public void setNonNullParameter(PreparedStatement ps, int i, List<Integer> parameter, JdbcType jdbcType) throws SQLException {
        Array sqlArray = ps.getConnection().createArrayOf("int", parameter.toArray());
        ps.setArray(i, sqlArray);
    }

    public List<Integer> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.arrayToList(rs.getArray(columnName));
    }

    public List<Integer> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.arrayToList(rs.getArray(columnIndex));
    }

    public List<Integer> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.arrayToList(cs.getArray(columnIndex));
    }

    private List<Integer> arrayToList(Array sqlArray) throws SQLException {
        return sqlArray == null ? Collections.emptyList() : Arrays.asList((Integer[])sqlArray.getArray());
    }
}