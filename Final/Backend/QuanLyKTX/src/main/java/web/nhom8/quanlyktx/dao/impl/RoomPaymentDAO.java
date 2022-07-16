package web.nhom8.quanlyktx.dao.impl;

import web.nhom8.quanlyktx.dao.IRoomPaymentDAO;
import web.nhom8.quanlyktx.mapper.RoomPaymentMapper;
import web.nhom8.quanlyktx.model.RoomPaymentModel;

import java.util.List;

public class RoomPaymentDAO extends AbstractDAO<RoomPaymentDAO> implements IRoomPaymentDAO {

    @Override
    public List<RoomPaymentModel> findPaymentByStudent(Long studentRoomId) {
        String sql = "SELECT * FROM RoomPayment WHERE StudentRoomId = ?";
        return query(sql, new RoomPaymentMapper(), studentRoomId);
    }

    @Override
    public List<RoomPaymentModel> findPaymentByState(Long studentRoomId, int state) {
        String sql = "SELECT * FROM `RoomPayment` WHERE `StudentRoomId` = ? AND `State` = ?";
        return query(sql, new RoomPaymentMapper(), studentRoomId, state);
    }

    @Override
    public RoomPaymentModel findOne(Long paymentId) {
        String sql = "SELECT * FROM RoomPayment WHERE PaymentId = ?";
        List<RoomPaymentModel> models = query(sql, new RoomPaymentMapper(), paymentId);
        return models.isEmpty() ? null : models.get(0);
    }

    @Override
    public RoomPaymentModel save(RoomPaymentModel model) {
        String sql = "INSERT INTO RoomPayment (PaymentId, StudentRoomId, PaymentMoney, " +
                "PaymentDate, State) VALUES(?, ? ,? ,?, ?)";
        Long id = insert(sql, model.getPaymentId(), model.getStudentRoomId(),
                model.getPaymentMoney(), model.getPaymentDate(), model.getState());
        if (id == null) return null;
        return findOne(id);
    }

    @Override
    public RoomPaymentModel update(RoomPaymentModel model) {
        String sql = "UPDATE RoomPayment SET PaymentMoney = ?, PaymentDate = ?, State = ? " +
                "WHERE PaymentId = ?";
        update(sql, model.getPaymentMoney(), model.getPaymentDate(), model.getState(), model.getPaymentId());
        return findOne(model.getPaymentId());
    }

    @Override
    public void delete(Long studentRoomId) {
        String sql = "DELETE FROM RoomPayment WHERE StudentRoomId = ?";
        update(sql, studentRoomId);
    }
}
