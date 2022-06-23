package web.nhom8.quanlyktx.mapper;


import java.sql.ResultSet;

public interface RowMapper<T> {
    T mapRow(ResultSet rs);
}
