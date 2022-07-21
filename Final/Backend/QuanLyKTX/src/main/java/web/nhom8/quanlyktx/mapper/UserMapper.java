package web.nhom8.quanlyktx.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import web.nhom8.quanlyktx.model.ManagerModel;
import web.nhom8.quanlyktx.model.RoleModel;
import web.nhom8.quanlyktx.model.UserModel;

public class UserMapper implements RowMapper<UserModel> {
    @Override
    public UserModel mapRow(ResultSet rs) {
        UserModel user = new UserModel();
        RoleModel roleModel = new RoleModel();
        ManagerModel managerModel = new ManagerModel();
        try {
            user.setUserId(rs.getInt("UserId"));
            user.setUsername(rs.getString("Username"));
            user.setPassword(rs.getString("Password"));
            roleModel.setRoleCode(rs.getString("RoleCode"));
            roleModel.setRoleName(rs.getString("RoleName"));
            user.setRoleModel(roleModel);
            user.setRoleId(rs.getLong("RoleId"));
            roleModel.setRoleId(user.getRoleId());
            managerModel.setManagerId(rs.getInt("ManagerId"));
            managerModel.setFullname(rs.getString("Fullname"));
            managerModel.setAddress(rs.getString("Address"));
            managerModel.setEmail(rs.getString("Email"));
            managerModel.setPhone(rs.getString("Phone"));
            managerModel.setDateOfBirth(rs.getDate("DateOfBirth"));
            managerModel.setYearOfService(rs.getInt("YearOfService"));
            user.setManagerModel(managerModel);
            user.setUserState(rs.getInt("UserState"));
            return user;
        } catch (SQLException e) {
            return null;
        }
    }
}
