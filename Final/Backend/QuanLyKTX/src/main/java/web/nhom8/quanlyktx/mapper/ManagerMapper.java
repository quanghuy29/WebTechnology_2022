package web.nhom8.quanlyktx.mapper;

import web.nhom8.quanlyktx.model.ManagerModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerMapper implements RowMapper<ManagerModel> {
    @Override
    public ManagerModel mapRow(ResultSet rs) {
        ManagerModel manager = new ManagerModel();
        try {
            manager.setManagerId(rs.getInt("ManagerId"));
            manager.setUserId(rs.getInt("UserId"));
            manager.setFullname(rs.getString("Fullname"));
            manager.setDateOfBirth(rs.getDate("DateOfBirth"));
            manager.setEmail(rs.getString("Email"));
            manager.setAddress(rs.getString("Address"));
            manager.setPhone(rs.getString("Phone"));
            manager.setYearOfService(rs.getInt("YearOfService"));
            manager.setState(rs.getInt("State"));
            return manager;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
