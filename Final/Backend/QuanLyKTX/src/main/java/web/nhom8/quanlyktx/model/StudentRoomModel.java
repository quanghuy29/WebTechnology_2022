package web.nhom8.quanlyktx.model;

public class StudentRoomModel {
    private int StudentRoomId;
    private int StudentId;
    private int RoomId;
    private float PayMoneyRemain;
    private int PaymentState;

    public StudentRoomModel(int studentRoomId, int studentId, int roomId, float payMoneyRemain, int paymentState) {
        StudentRoomId = studentRoomId;
        StudentId = studentId;
        RoomId = roomId;
        PayMoneyRemain = payMoneyRemain;
        PaymentState = paymentState;
    }

    public int getStudentRoomId() {
        return StudentRoomId;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }

    public int getRoomId() {
        return RoomId;
    }

    public void setRoomId(int roomId) {
        RoomId = roomId;
    }

    public float getPayMoneyRemain() {
        return PayMoneyRemain;
    }

    public void setPayMoneyRemain(float payMoneyRemain) {
        PayMoneyRemain = payMoneyRemain;
    }

    public int getPaymentState() {
        return PaymentState;
    }

    public void setPaymentState(int paymentState) {
        PaymentState = paymentState;
    }

    @Override
    public String toString() {
        return "StudentRoom{" +
                "StudentRoomId=" + StudentRoomId +
                ", StudentId=" + StudentId +
                ", RoomId=" + RoomId +
                ", PayMoneyRemain=" + PayMoneyRemain +
                ", PaymentState=" + PaymentState +
                '}';
    }
}
