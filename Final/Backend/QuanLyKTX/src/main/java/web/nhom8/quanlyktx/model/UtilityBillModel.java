package web.nhom8.quanlyktx.model;

import java.sql.Date;

public class UtilityBillModel {
    private int BillId;
    private int RoomId;
    private float Money;
    private Date PaymentDate;
    private int State;

    public UtilityBillModel(int billId, int roomId, float money, Date paymentDate, int state) {
        BillId = billId;
        RoomId = roomId;
        Money = money;
        PaymentDate = paymentDate;
        State = state;
    }

    public int getBillId() {
        return BillId;
    }

    public int getRoomId() {
        return RoomId;
    }

    public void setRoomId(int roomId) {
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
