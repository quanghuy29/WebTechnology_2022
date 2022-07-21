import { forwardRef, useImperativeHandle, useState } from "react";
import React from "react";
import "./room.css";
import axios from "axios";

const UpdateRoom = forwardRef((props, ref) => {
    const roomId = props.roomId;
    const [roomCode, setRoomCode] = useState(props.roomCode);
    const [maxSlots, setMaxSlots] = useState(props.maxSlots);
    const [paymentState, setPaymentState] = useState(props.roomPaymentState);
    const [roomState, setRoomState] = useState(props.roomState);

    const numberSlots = props.maxSlots - props.availableSlots;
    const urlPut = 'http://localhost:8080/QuanLyKTX_war_exploded/room';

    useImperativeHandle(ref, () => ({
        putRoom() {
            let isValid = true;
            const putData = {
                roomCode: roomCode,
                maxSlots: maxSlots,
                availableSlots: maxSlots - numberSlots,
                roomPaymentState: paymentState,
                roomState: roomState
            }
            if (putData.roomCode === "")
                isValid = false;

            if (isValid === true) {
                axios.put(urlPut, putData, { params: { idRoom: roomId } })
                    .then(res => console.log(res));
            }
            return isValid;
        }
    }))


    return (
        <section>
            <h1 className="title">Cập nhật phòng</h1>
            <label>
                <span>Trạng thái thanh toán</span>
                <select onChange={(event) => setPaymentState(event.target.value)} value={paymentState} >
                    <option value="1">Đã trả đủ tiền điện nước</option>
                    <option value="2">Còn thiếu tiền điện nước</option>
                </select>
            </label>
        </section>
    );
});

export default UpdateRoom;