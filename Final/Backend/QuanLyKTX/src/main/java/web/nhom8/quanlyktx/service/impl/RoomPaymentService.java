package web.nhom8.quanlyktx.service.impl;

import web.nhom8.quanlyktx.model.RoomPaymentModel;
import web.nhom8.quanlyktx.model.StudentRoomModel;

import web.nhom8.quanlyktx.service.IRoomPaymentService;
import web.nhom8.quanlyktx.service.IStudentRoomService;
import web.nhom8.quanlyktx.dao.IRoomPaymentDAO;

import javax.inject.Inject;
import java.util.List;

public class RoomPaymentService implements IRoomPaymentService {
    @Inject
    private IRoomPaymentDAO roomPaymentDAO;

    @Inject
    private IStudentRoomService studentRoomService;

    @Override
    public List<RoomPaymentModel> findPaymentByStudent(Long studentRoomId) {
        return roomPaymentDAO.findPaymentByStudent(studentRoomId);
    }

    @Override
    public List<RoomPaymentModel> findPaymentByState(Long studentRoomId, int state) {
        return roomPaymentDAO.findPaymentByState(studentRoomId, state);
    }

    @Override
    public RoomPaymentModel findOne(Long paymentId) {
        return roomPaymentDAO.findOne(paymentId);
    }

    @Override
    public RoomPaymentModel save(RoomPaymentModel model) {
        StudentRoomModel studentRoomModel = studentRoomService.findOne(model.getStudentRoomId());
        model.setStudentRoomId(studentRoomModel.getId());
        model.setState(2);
        if (roomPaymentDAO.save(model) != null) {
            return findOne(model.getPaymentId());
        }
        return null;
    }

    @Override
    public RoomPaymentModel update(RoomPaymentModel model) {
        return roomPaymentDAO.update(model);
    }

    @Override
    public void delete(Long studentRoomId) {
        roomPaymentDAO.delete(studentRoomId);
    }
}
