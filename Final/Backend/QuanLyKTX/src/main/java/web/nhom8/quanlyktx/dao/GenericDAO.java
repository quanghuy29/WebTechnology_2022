package web.nhom8.quanlyktx.dao;

import web.nhom8.quanlyktx.mapper.RowMapper;

import java.util.List;
/**
 * @bief general interface class to reused code.
 *  This interface placed in DAO Layer.
 * */
public interface GenericDAO<T> {

    <T>List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);

    void update (String sql, Object... parameters);

    void insert (String sql, Object... parameters);

    void count(String sql, Object... parameters);
}
