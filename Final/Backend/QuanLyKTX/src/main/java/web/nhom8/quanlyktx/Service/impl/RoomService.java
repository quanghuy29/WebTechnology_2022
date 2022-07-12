package web.nhom8.quanlyktx.Service.impl;

import web.nhom8.quanlyktx.Service.IRoomService;
import web.nhom8.quanlyktx.dao.IRoomDAO;
import web.nhom8.quanlyktx.dao.IStudentRoomDAO;
import web.nhom8.quanlyktx.model.RoomModel;
import web.nhom8.quanlyktx.model.StudentRoomModel;

import javax.inject.Inject;
import java.util.List;

public class RoomService implements IRoomService {
    @Inject
    private IRoomDAO roomDAO;
    @Inject
    private IStudentRoomDAO studentRoomDAO;

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
        room.setRoomPaymentState(1);
        room.setRoomState(1);
        return roomDAO.save(room);
    }

    @Override
    public RoomModel update(RoomModel room) {
        if (room.getAvailableSlots() > room.getMaxSlots() || room.getMaxSlots() < 0 || room.getAvailableSlots() < 0) return null;
        return roomDAO.update(room);
    }

    @Override
    public void delete(Long id) {
        List<StudentRoomModel> listModel = studentRoomDAO.findAllStudentByRoom(id);
        for (StudentRoomModel model: listModel) {
            studentRoomDAO.delete(model.getStudentId());
        }
        roomDAO.delete(id);
        roomDAO.resetAI();
    }

    @Override
    public int getTotalItem() {
        return roomDAO.getTotalItem();
    }
}
