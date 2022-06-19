package web.nhom8.quanlyktx.model;

import java.sql.Date;

public class RoomPaymentModel {
    private int PaymentId;
    private int StudentRoomId;
    private float PaymentMoney;
    private Date PaymentDate;
    private int State;

    public RoomPaymentModel(int paymentId, int studentRoomId, float paymentMoney, Date paymentDate, int state) {
        PaymentId = paymentId;
        StudentRoomId = studentRoomId;
        PaymentMoney = paymentMoney;
        PaymentDate = paymentDate;
        State = state;
    }

    public int getPaymentId() {
        return PaymentId;
    }

    public int getStudentRoomId() {
        return StudentRoomId;
    }

    public void setStudentRoomId(int studentRoomId) {
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
