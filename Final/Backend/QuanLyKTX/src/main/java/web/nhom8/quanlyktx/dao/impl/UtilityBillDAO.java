package web.nhom8.quanlyktx.dao.impl;

import web.nhom8.quanlyktx.dao.IUtilityBillDAO;
import web.nhom8.quanlyktx.mapper.UtilityBillMapper;
import web.nhom8.quanlyktx.model.UtilityBillModel;

import java.util.List;

public class UtilityBillDAO extends AbstractDAO<UtilityBillModel> implements IUtilityBillDAO {
    @Override
    public List<UtilityBillModel> findBillByRoom(Long roomId) {
        String sql = "SELECT * FROM UtilityBill WHERE RoomId = ?";
        return query(sql, new UtilityBillMapper(), roomId);
    }

    @Override
    public List<UtilityBillModel> findBillByState(Long roomId, int state) {
        String sql = "SELECT * FROM `UtilityBill` WHERE `RoomId` = ? AND `State` = ?";
        return query(sql, new UtilityBillMapper(), roomId, state);
    }

    @Override
    public UtilityBillModel findOne(Long billId) {
        String sql = "SELECT * FROM UtilityBill WHERE BillId = ?";
        List<UtilityBillModel> models = query(sql, new UtilityBillMapper(), billId);
        return models.isEmpty() ? null : models.get(0);
    }

    @Override
    public UtilityBillModel save(UtilityBillModel model) {
        String sql = "INSERT INTO UtilityBill (RoomId, Money, PaymentDate, State) VALUES(?, ?, ?, ?)";
        Long id = insert(sql, model.getRoomId(), model.getMoney(),
                model.getPaymentDate(), model.getState());
        if (id == null) return null;
        return findOne(id);
    }

    @Override
    public UtilityBillModel update(UtilityBillModel model) {
        String sql = "UPDATE UtilityBill SET Money = ?, State = ? WHERE BillId = ?";
        update(sql, model.getMoney(), model.getState(), model.getBillId());
        return findOne(model.getBillId());
    }

    @Override
    public void delete(Long roomId) {
        String sql = "DELETE FROM UtilityBill WHERE RoomId = ?";
        update(sql, roomId);
    }
}
