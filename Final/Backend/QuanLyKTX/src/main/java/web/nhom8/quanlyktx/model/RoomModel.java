package web.nhom8.quanlyktx.model;

public class RoomModel {
    private int RoomId;
    private String RoomCode;
    private int MaxSlots;
    private int AvailableSlots;
    private int PaymentState;

    public RoomModel(int roomId, String roomCode, int maxSlots, int availableSlots, int paymentState) {
        RoomId = roomId;
        RoomCode = roomCode;
        MaxSlots = maxSlots;
        AvailableSlots = availableSlots;
        PaymentState = paymentState;
    }

    public int getRoomId() {
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

    @Override
    public String toString() {
        return "Room{" +
                "RoomId=" + RoomId +
                ", RoomCode='" + RoomCode + '\'' +
                ", MaxSlots=" + MaxSlots +
                ", AvailableSlots=" + AvailableSlots +
                ", PaymentState=" + PaymentState +
                '}';
    }
}
