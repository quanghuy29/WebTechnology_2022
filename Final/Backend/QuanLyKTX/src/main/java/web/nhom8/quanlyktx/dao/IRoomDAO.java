package web.nhom8.quanlyktx.dao;

import web.nhom8.quanlyktx.model.RoomModel;

import java.util.List;

public interface IRoomDAO extends GenericDAO<RoomModel> {
    RoomModel findOne(Long roomId);
    List<RoomModel> findAll();
    RoomModel save(RoomModel room);
    RoomModel update(RoomModel room);
    void delete(int id);
    int getTotalItem();
}
