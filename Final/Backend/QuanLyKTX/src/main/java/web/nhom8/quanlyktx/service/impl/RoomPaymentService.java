package web.nhom8.quanlyktx.service.impl;

import web.nhom8.quanlyktx.model.RoomPaymentModel;

import web.nhom8.quanlyktx.service.IRoomPaymentService;
import web.nhom8.quanlyktx.dao.IRoomPaymentDAO;

import javax.inject.Inject;
import java.util.List;

public class RoomPaymentService implements IRoomPaymentService {
    @Inject
    private IRoomPaymentDAO roomPaymentDAO;

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
        model.setState(2);
        model = roomPaymentDAO.save(model);
        return model;
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
