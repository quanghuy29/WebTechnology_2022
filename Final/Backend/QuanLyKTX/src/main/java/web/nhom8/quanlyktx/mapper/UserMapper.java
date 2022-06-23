package web.nhom8.quanlyktx.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import web.nhom8.quanlyktx.model.UserModel;

public class UserMapper implements RowMapper<UserModel> {
    @Override
    public UserModel mapRow(ResultSet rs) {
        UserModel user = new UserModel();
        try {
            user.setUserId(rs.getInt("UserId"));
            user.setUsername(rs.getString("Username"));
            user.setPassword(rs.getString("Password"));
            user.setRoleId(rs.getLong("RoleId"));
            user.setState(rs.getInt("State"));
            return user;
        } catch (SQLException e) {
            return null;
        }
    }
}
