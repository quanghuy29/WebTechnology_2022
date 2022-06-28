package web.nhom8.quanlyktx.Service.implement;

import web.nhom8.quanlyktx.Service.IRoomService;
import web.nhom8.quanlyktx.dao.IRoomDAO;
import web.nhom8.quanlyktx.model.RoomModel;

import javax.inject.Inject;
import java.util.List;

public class RoomService implements IRoomService {
    @Inject
    private IRoomDAO roomDAO;

    @Override
    public RoomModel findOne(Long roomId) {
        return roomDAO.findOne(roomId);
    }

    @Override
    public List<RoomModel> findAll() {
        return roomDAO.findAll();
    }

    @Override
    public RoomModel save(RoomModel room) {
        return roomDAO.save(room);
    }

    @Override
    public RoomModel update(RoomModel room) {
        return roomDAO.update(room);
    }

    @Override
    public void delete(Long id) {
        roomDAO.delete(id);
    }

    @Override
    public int getTotalItem() {
        return roomDAO.getTotalItem();
    }
}
