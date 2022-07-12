package web.nhom8.quanlyktx.service.impl;

import web.nhom8.quanlyktx.service.IRoomService;
import web.nhom8.quanlyktx.service.IStudentRoomService;
import web.nhom8.quanlyktx.dao.IStudentRoomDAO;
import web.nhom8.quanlyktx.model.RoomModel;
import web.nhom8.quanlyktx.model.StudentRoomModel;

import javax.inject.Inject;
import java.util.List;

public class StudentRoomService implements IStudentRoomService {
    @Inject
    private IStudentRoomDAO studentRoomDAO;
    @Inject
    private IRoomService roomService;

    @Override
    public List<StudentRoomModel> findAllStudentByRoom(Long idRoom) {
        return studentRoomDAO.findAllStudentByRoom(idRoom);
    }

    @Override
    public List<StudentRoomModel> findAll() {
        return studentRoomDAO.findAll();
    }

    @Override
    public StudentRoomModel save(StudentRoomModel model) {
        if (model.getPayMoneyRemain() == null) model.setPayMoneyRemain(0f);
        if (model.getPaymentState() == null) model.setPaymentState(1);
        List<StudentRoomModel> list = studentRoomDAO.findAllStudentByRoom(model.getRoomId());
        RoomModel room = roomService.findOne(model.getRoomId());
        if (room.getMaxSlots() - list.size() < 1) return null;

        if (studentRoomDAO.save(model) != null) {
            list = studentRoomDAO.findAllStudentByRoom(model.getRoomId());
            room.setAvailableSlots(room.getMaxSlots() - list.size());
            roomService.update(room);
            return findByStudentId(model.getStudentId());
        }
        return null;
    }

    @Override
    public void delete(Long idStudent) {
        studentRoomDAO.delete(idStudent);
        studentRoomDAO.resetAI();
    }

    @Override
    public StudentRoomModel update(StudentRoomModel model) {
        if (model.getPayMoneyRemain() == 0) model.setPaymentState(1);
        else model.setPaymentState(2);
        return studentRoomDAO.update(model);
    }

    @Override
    public StudentRoomModel findOne(Long id) {
        return studentRoomDAO.findOne(id);
    }

    @Override
    public StudentRoomModel findByStudentId(Long idStudent) {
        return studentRoomDAO.findByStudentId(idStudent);
    }
}
