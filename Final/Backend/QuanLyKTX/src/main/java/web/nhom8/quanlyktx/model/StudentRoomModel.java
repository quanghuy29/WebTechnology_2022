package web.nhom8.quanlyktx.model;

public class StudentRoomModel {
    private Long Id;
    private Long StudentId;
    private Long RoomId;
    private Float PayMoneyRemain;
    private Integer PaymentState;

    public void setId(Long id) {
        Id = id;
    }

    public Long getId() {
        return Id;
    }

    public Long getStudentId() {
        return StudentId;
    }

    public void setStudentId(Long studentId) {
        StudentId = studentId;
    }

    public Long getRoomId() {
        return RoomId;
    }

    public void setRoomId(Long roomId) {
        RoomId = roomId;
    }

    public Float getPayMoneyRemain() {
        return PayMoneyRemain;
    }

    public void setPayMoneyRemain(Float payMoneyRemain) {
        PayMoneyRemain = payMoneyRemain;
    }

    public Integer getPaymentState() {
        return PaymentState;
    }

    public void setPaymentState(Integer paymentState) {
        PaymentState = paymentState;
    }

}
