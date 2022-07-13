package web.nhom8.quanlyktx.mapper;

import web.nhom8.quanlyktx.model.RoomPaymentModel;
import web.nhom8.quanlyktx.model.StudentModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomPaymentMapper implements RowMapper<RoomPaymentModel> {

    @Override
    public RoomPaymentModel mapRow(ResultSet rs) {
        RoomPaymentModel roomPaymentModel = new RoomPaymentModel();
        try {
            roomPaymentModel.setPaymentId(rs.getInt("PaymentId"));
            roomPaymentModel.setStudentRoomId(rs.getInt("StudentRoomId"));
            roomPaymentModel.setPaymentMoney(rs.getFloat("PaymentMoney"));
            roomPaymentModel.setPaymentDate(rs.getDate("PaymentDate"));
            roomPaymentModel.setState(rs.getInt("State"));
            return roomPaymentModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
