package web.nhom8.quanlyktx.mapper;

import web.nhom8.quanlyktx.model.RoomModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomMapper implements RowMapper<RoomModel> {
    @Override
    public RoomModel mapRow(ResultSet rs) {
        RoomModel room = new RoomModel();
        try {
            room.setRoomId(rs.getLong("RoomId"));
            room.setRoomCode(rs.getString("RoomCode"));
            room.setAvailableSlots(rs.getInt("AvailableSlots"));
            room.setMaxSlots(rs.getInt("MaxSlots"));
            room.setRoomPaymentState(rs.getInt("RoomPaymentState"));
            room.setRoomState(rs.getInt("RoomState"));
            return room;
        } catch (SQLException e){
            return null;
        }
    }
}
