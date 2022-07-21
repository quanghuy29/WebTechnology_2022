package web.nhom8.quanlyktx.model;

import java.sql.Date;

public class UtilityBillModel {
    private Long BillId;
    private Long RoomId;
    private float Money;
    private Date PaymentDate;
    private int State;

    public UtilityBillModel() {

    }
    public UtilityBillModel(Long billId, Long roomId, float money, Date paymentDate, int state) {
        BillId = billId;
        RoomId = roomId;
        Money = money;
        PaymentDate = paymentDate;
        State = state;
    }

    public Long getBillId() {
        return BillId;
    }

    public void setBillId(Long billId) {
        BillId = billId;
    }
    public Long getRoomId() {
        return RoomId;
    }

    public void setRoomId(Long roomId) {
        RoomId = roomId;
    }

    public float getMoney() {
        return Money;
    }

    public void setMoney(float money) {
        Money = money;
    }

    public Date getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        PaymentDate = paymentDate;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    @Override
    public String toString() {
        return "UtilityBill{" +
                "BillId=" + BillId +
                ", RoomId=" + RoomId +
                ", Money=" + Money +
                ", PaymentDate=" + PaymentDate +
                ", State=" + State +
                '}';
    }
}
