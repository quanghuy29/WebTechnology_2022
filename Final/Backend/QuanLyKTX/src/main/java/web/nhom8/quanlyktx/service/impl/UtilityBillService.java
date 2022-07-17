package web.nhom8.quanlyktx.service.impl;

import web.nhom8.quanlyktx.model.UtilityBillModel;
import web.nhom8.quanlyktx.dao.IUtilityBillDAO;
import web.nhom8.quanlyktx.service.IUtilityBillService;

import javax.inject.Inject;
import java.util.List;

public class UtilityBillService implements IUtilityBillService{
    @Inject
    private IUtilityBillDAO utilityBillDAO;

    @Override
    public List<UtilityBillModel> findBillByRoom(Long roomId) {
        return utilityBillDAO.findBillByRoom(roomId);
    }

    @Override
    public List<UtilityBillModel> findBillByState(Long roomId, int state) {
        return utilityBillDAO.findBillByState(roomId, state);
    }

    @Override
    public UtilityBillModel findOne(Long billId) {
        return utilityBillDAO.findOne(billId);
    }

    @Override
    public UtilityBillModel save(UtilityBillModel model) {
        model.setState(2);
        model = utilityBillDAO.save(model);
        return model;
    }

    @Override
    public UtilityBillModel update(UtilityBillModel model) {
        return utilityBillDAO.update(model);
    }

    @Override
    public void delete(Long roomId) {
        utilityBillDAO.delete(roomId);
    }
}
