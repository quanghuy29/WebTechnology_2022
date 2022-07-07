package web.nhom8.quanlyktx.Service.impl;

import web.nhom8.quanlyktx.Service.IStudentRoomService;
import web.nhom8.quanlyktx.dao.IStudentRoomDAO;
import web.nhom8.quanlyktx.model.StudentRoomModel;

import javax.inject.Inject;
import java.util.List;

public class StudentRoomService implements IStudentRoomService {
    @Inject
    private IStudentRoomDAO studentRoomDAO;

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
        return studentRoomDAO.save(model);
    }

    @Override
    public void delete(Long idStudent) {
        studentRoomDAO.delete(idStudent);
        studentRoomDAO.resetAI();
    }

    @Override
    public StudentRoomModel update(StudentRoomModel model) {
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
