package web.nhom8.quanlyktx.dao.impl;

import web.nhom8.quanlyktx.dao.IRoomDAO;
import web.nhom8.quanlyktx.mapper.RoomMapper;
import web.nhom8.quanlyktx.model.RoomModel;

import java.util.List;

public class RoomDAO extends AbstractDAO<RoomModel> implements IRoomDAO {
    @Override
    public RoomModel findOne(Long roomId) {
        String sql = "SELECT * FROM Room WHERE RoomId = ?";
        List<RoomModel> roomModels = query(sql, new RoomMapper(), roomId);
        return roomModels.isEmpty() ? null : roomModels.get(0);
    }

    @Override
    public List<RoomModel> findAll() {
        String sql = "SELECT * FROM Room";
        return query(sql, new RoomMapper());
    }

    @Override
    public RoomModel save(RoomModel room) {
        Long id = insert("INSERT INTO Room (RoomCode, MaxSlots, AvailableSlots, RoomState, RoomPaymentState) VALUES(?, ?, ?, ?, ?)",
                room.getRoomCode(), room.getMaxSlots(), room.getAvailableSlots(),
                room.getRoomPaymentState(), room.getRoomState());
        return findOne(id);
    }

    @Override
    public RoomModel update(RoomModel room) {
        String sql = "UPDATE Room SET RoomCode = ?, MaxSlots = ?," +
                "AvailableSlots = ?, RoomState = ?, RoomPaymentState = ? WHERE RoomId = ?";
        update(sql, room.getRoomCode(), room.getMaxSlots(), room.getAvailableSlots()
                , room.getRoomState(), room.getRoomPaymentState(), room.getRoomId());
        return findOne(room.getRoomId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Room WHERE RoomId = ?";
        update(sql, id);
    }

    @Override
    public int getTotalItem() {
        String sql = "SELECT count(*) FROM Room";
        return count(sql);
    }
}
