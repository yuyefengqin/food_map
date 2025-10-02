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
public class ListStrToArrayTypeHandler extends BaseTypeHandler<List<String>> {
    public ListStrToArrayTypeHandler() {
    }

    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        Array sqlArray = ps.getConnection().createArrayOf("text", parameter.toArray());
        ps.setArray(i, sqlArray);
    }

    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.arrayToList(rs.getArray(columnName));
    }

    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.arrayToList(rs.getArray(columnIndex));
    }

    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.arrayToList(cs.getArray(columnIndex));
    }

    private List<String> arrayToList(Array sqlArray) throws SQLException {
        return sqlArray == null ? Collections.emptyList() : Arrays.asList((String[])sqlArray.getArray());
    }
}