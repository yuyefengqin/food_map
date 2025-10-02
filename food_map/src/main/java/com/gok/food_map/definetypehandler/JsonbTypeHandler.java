package com.gok.food_map.definetypehandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.postgresql.util.PGobject;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@MappedTypes({Map.class})
public class JsonbTypeHandler<T> extends BaseTypeHandler<T> {
    private static final ObjectMapper mapper = new ObjectMapper();
    private final TypeReference<T> typeReference;

    public JsonbTypeHandler() {
        // 默认处理Map<String, String>类型
        this.typeReference = new TypeReference<T>() {};
    }

    // 保留原构造方法兼容性
    public JsonbTypeHandler(Class<T> type) {
        this.typeReference = new TypeReference<T>() {
            @Override
            public java.lang.reflect.Type getType() {
                return type;
            }
        };
    }

    // 添加支持TypeReference的构造方法
    public JsonbTypeHandler(TypeReference<T> typeReference) {
        this.typeReference = typeReference;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        try {
            PGobject pGobject = new PGobject();
            pGobject.setType("jsonb");
            pGobject.setValue(mapper.writeValueAsString(parameter));
            ps.setObject(i, pGobject);
        } catch (Exception e) {
            throw new SQLException("Error converting object to JSON: " + e.getMessage());
        }
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return parseJson(rs.getObject(columnName));
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return parseJson(rs.getObject(columnIndex));
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return parseJson(cs.getObject(columnIndex));
    }

    private T parseJson(Object value) {
        if (value == null) return null;

        try {
            // 处理PGobject类型
            if (value instanceof PGobject) {
                String json = ((PGobject) value).getValue();
                return json != null ? mapper.readValue(json, typeReference) : null;
            }
            // 处理直接字符串类型
            else if (value instanceof String) {
                return mapper.readValue((String) value, typeReference);
            }
            throw new RuntimeException("Unsupported JSONB type: " + value.getClass());
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON: " + e.getMessage(), e);
        }
    }
}
