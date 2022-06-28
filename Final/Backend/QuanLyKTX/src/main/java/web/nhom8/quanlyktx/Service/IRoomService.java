package web.nhom8.quanlyktx.Service;

import web.nhom8.quanlyktx.model.RoomModel;

import java.util.List;

public interface IRoomService {
    RoomModel findOne(Long roomId);
    List<RoomModel> findAll();
    RoomModel save(RoomModel room);
    RoomModel update(RoomModel room);
    void delete(Long id);
    int getTotalItem();
}
