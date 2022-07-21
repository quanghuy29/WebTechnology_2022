package web.nhom8.quanlyktx.service;

import web.nhom8.quanlyktx.model.UtilityBillModel;

import java.util.List;

public interface IUtilityBillService {
    List<UtilityBillModel> findBillByRoom(Long roomId);
    List<UtilityBillModel> findBillByState(Long roomId, int state);
    UtilityBillModel findOne(Long billId);
    UtilityBillModel save(UtilityBillModel model);
    UtilityBillModel update(UtilityBillModel model);
    void delete(Long roomId);
}
