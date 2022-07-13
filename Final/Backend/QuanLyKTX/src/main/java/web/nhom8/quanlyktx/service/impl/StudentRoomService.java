package web.nhom8.quanlyktx.service.impl;

import web.nhom8.quanlyktx.model.StudentModel;
import web.nhom8.quanlyktx.service.IRoomService;
import web.nhom8.quanlyktx.service.IStudentRoomService;
import web.nhom8.quanlyktx.dao.IStudentRoomDAO;
import web.nhom8.quanlyktx.model.RoomModel;
import web.nhom8.quanlyktx.model.StudentRoomModel;
import web.nhom8.quanlyktx.service.IStudentService;

import javax.inject.Inject;
import java.util.List;

public class StudentRoomService implements IStudentRoomService {
    @Inject
    private IStudentRoomDAO studentRoomDAO;
    @Inject
    private IRoomService roomService;
    @Inject
    private IStudentService studentService;

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
        StudentModel student = studentService.findByStudentCode(model.getStudentId().toString());
        model.setStudentId((long)student.getStudentId());
        model.setStudentCode(student.getStudentCode());
        model.setStudentName(student.getFullname());
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
        StudentRoomModel studentRoomModel = findByStudentId(idStudent);
        RoomModel room = roomService.findOne(studentRoomModel.getRoomId());
        studentRoomDAO.delete(idStudent);
        studentRoomDAO.resetAI();
        List<StudentRoomModel> list = studentRoomDAO.findAllStudentByRoom(room.getRoomId());
        room.setAvailableSlots(room.getMaxSlots() - list.size());
        roomService.update(room);
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
