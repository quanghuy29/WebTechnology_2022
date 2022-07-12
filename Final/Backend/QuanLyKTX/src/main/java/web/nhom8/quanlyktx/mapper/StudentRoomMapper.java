package web.nhom8.quanlyktx.mapper;

import web.nhom8.quanlyktx.model.StudentRoomModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRoomMapper implements RowMapper<StudentRoomModel> {
    @Override
    public StudentRoomModel mapRow(ResultSet rs) {
        StudentRoomModel model = new StudentRoomModel();
        try {
            model.setId(rs.getLong("StudentRoomId"));
            model.setStudentId(rs.getLong("StudentId"));
            model.setRoomId(rs.getLong("RoomId"));
            model.setPayMoneyRemain(rs.getFloat("PayMoneyRemain"));
            model.setPaymentState(rs.getInt("PaymentState"));
            return model;
        } catch (SQLException e){
            return null;
        }
    }
}
