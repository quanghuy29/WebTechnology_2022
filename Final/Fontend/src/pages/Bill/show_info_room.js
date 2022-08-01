import { forwardRef, useEffect, useImperativeHandle, useState, useRef } from "react";
import React from "react";
import Popup from "../../component/popup/popup";
import AddBill from "./add_bill";
import "./room.css";

const ShowRoom = forwardRef((props, ref) => {
    const [change, setChange] = useState(false);
    const [roomID, setRoomID] = useState(0);
    const [room, setRoom] = useState(props);
    const [isFirstTime, setIsFirstTime] = useState(true);
    const [isAddBill, setIsAddBill] = useState(false);
    const token = localStorage.getItem("token-auth");
    const addBill = useRef()
    const urlGet = 'http://localhost:8080/QuanLyKTX_war_exploded/room?idRoom=';
    const checkPaymentRoomState = (item) => {
        if (item === 1) return "Đã trả đủ tiền điện nước"
        else if (item === 2) return "Còn thiếu tiền điện nước"
    }

    const checkRoomState = (item) => {
        if (item === 1) return "Đang ở"
        else if (item === 2) return "Đang khoá"
    }

    const addFunction = () => {
        if (room.roomState === 2) {
            alert("Phòng bị khoá, không thể thêm hoá đơn!!");
            return;
        }

        if (addBill.current.postBill() === true) {
            setIsAddBill(!isAddBill);
        }
        props.reloadBills();
        setTimeout(() => props.reloadRoom(), 500);
        setTimeout(() => setChange(!change), 500);
    }

    const showData = (data) => {
        if (!isFirstTime) setRoom(data);
        setIsFirstTime(false);
    }

    useEffect(() => {
        fetch(urlGet + roomID,
            {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            })
            .then(res => res.json())
            .then(data => showData(data));
    }, [change, roomID]);

    useImperativeHandle(ref, () => ({
        update(roomId) {
            setRoomID(roomId);
            setTimeout(() => setChange(!change), 500);
        }
    }))
    return (
        <div>
            <table>
                <tbody>
                    <tr>
                        <td>Trạng thái thanh toán</td>
                        <td>{checkPaymentRoomState(room.roomPaymentState)}</td>
                    </tr>
                    <tr>
                        <td>Trạng thái phòng</td>
                        <td>{checkRoomState(room.roomState)}</td>
                    </tr>
                    <tr>
                        <td>
                            {room.roomCode !== '' &&
                                <i class="fas fa-user-plus" onClick={() => {
                                    if (room.roomCode !== '')
                                        setIsAddBill(!isAddBill)
                                }}> Thêm hoá đơn</i>
                            }
                        </td>
                    </tr>
                </tbody>
            </table>
            {isAddBill && <Popup
                content={
                    <AddBill
                        roomId={roomID}
                        ref={addBill}
                    ></AddBill>}
                handleClose={() => setIsAddBill(!isAddBill)}
                handleConfirm={() => addFunction()}
            />}

        </div>
    )
})

export default ShowRoom