package web.nhom8.quanlyktx.model;

import java.sql.Date;

public class RoomPaymentModel {
    private Long PaymentId;
    private Long StudentRoomId;
    private float PaymentMoney;
    private Date PaymentDate;
    private int State;

    public RoomPaymentModel() {

    }
    public RoomPaymentModel(Long paymentId, Long studentRoomId, float paymentMoney, Date paymentDate, int state) {
        PaymentId = paymentId;
        StudentRoomId = studentRoomId;
        PaymentMoney = paymentMoney;
        PaymentDate = paymentDate;
        State = state;
    }

    public Long getPaymentId() {
        return PaymentId;
    }

    public void setPaymentId(Long paymentId) {
        PaymentId = paymentId;
    }

    public Long getStudentRoomId() {
        return StudentRoomId;
    }

    public void setStudentRoomId(Long studentRoomId) {
        StudentRoomId = studentRoomId;
    }

    public float getPaymentMoney() {
        return PaymentMoney;
    }

    public void setPaymentMoney(float paymentMoney) {
        PaymentMoney = paymentMoney;
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
        return "RoomPayment{" +
                "PaymentId=" + PaymentId +
                ", StudentRoomId=" + StudentRoomId +
                ", PaymentMoney=" + PaymentMoney +
                ", PaymentDate=" + PaymentDate +
                ", State=" + State +
                '}';
    }
}
