package web.nhom8.quanlyktx.model;

public class RoomModel {
    private Long RoomId;
    private String RoomCode;
    private int MaxSlots;
    private int AvailableSlots;
    private int PaymentState;
    private int RoomState;

    public RoomModel(Long roomId, String roomCode, int maxSlots, int availableSlots, int paymentState) {
        RoomId = roomId;
        RoomCode = roomCode;
        MaxSlots = maxSlots;
        AvailableSlots = availableSlots;
        PaymentState = paymentState;
    }

    public RoomModel(){

    }

    public void setRoomId(Long roomId) {
        RoomId = roomId;
    }

    public Long getRoomId() {
        return RoomId;
    }

    public String getRoomCode() {
        return RoomCode;
    }

    public void setRoomCode(String roomCode) {
        RoomCode = roomCode;
    }

    public int getMaxSlots() {
        return MaxSlots;
    }

    public void setMaxSlots(int maxSlots) {
        MaxSlots = maxSlots;
    }

    public int getAvailableSlots() {
        return AvailableSlots;
    }

    public void setAvailableSlots(int availableSlots) {
        AvailableSlots = availableSlots;
    }

    public int getPaymentState() {
        return PaymentState;
    }

    public void setPaymentState(int paymentState) {
        PaymentState = paymentState;
    }

    public int getRoomState() {
        return RoomState;
    }

    public void setRoomState(int roomState) {
        RoomState = roomState;
    }

    @Override
    public String toString() {
        return "{\n" +
                "RoomId: " + RoomId +
                ", \nRoomCode: " + RoomCode + '\'' +
                ", \nMaxSlots: " + MaxSlots +
                ", \nAvailableSlots: " + AvailableSlots +
                ", \nPaymentState: " + PaymentState +
                ", \nRoomState= " + RoomState +
                "\n}";
    }
}
