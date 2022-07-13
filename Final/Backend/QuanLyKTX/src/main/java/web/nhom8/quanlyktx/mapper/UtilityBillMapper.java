package web.nhom8.quanlyktx.mapper;

import web.nhom8.quanlyktx.model.UtilityBillModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilityBillMapper implements RowMapper<UtilityBillModel> {

    @Override
    public UtilityBillModel mapRow(ResultSet rs) {
        UtilityBillModel utilityBillModel = new UtilityBillModel();
        try {
            utilityBillModel.setBillId(rs.getInt("BillId"));
            utilityBillModel.setRoomId(rs.getInt("RoomId"));
            utilityBillModel.setMoney(rs.getFloat("Money"));
            utilityBillModel.setPaymentDate(rs.getDate("PaymentDate"));
            utilityBillModel.setState(rs.getInt("State"));
            return utilityBillModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
