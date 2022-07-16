package web.nhom8.quanlyktx.service;

import web.nhom8.quanlyktx.model.RoomPaymentModel;

import java.util.List;

public interface IRoomPaymentService {
    List<RoomPaymentModel> findPaymentByStudent(Long studentRoomId);
    List<RoomPaymentModel> findPaymentByState(Long studentRoomId, int state);
    RoomPaymentModel findOne(Long paymentId);
    RoomPaymentModel save(RoomPaymentModel model);
    RoomPaymentModel update(RoomPaymentModel model);
    void delete(Long studentRoomId);
}
