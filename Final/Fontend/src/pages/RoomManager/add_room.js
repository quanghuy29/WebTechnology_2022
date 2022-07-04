import { forwardRef, useImperativeHandle, useState } from "react";
import React from "react";
import "./room.css";
import axios from "axios";


const AddRoom = forwardRef((props, ref) => {

    const [roomCode, setRoomCode] = useState('');
    const [maxSlots, setMaxSlots] = useState(0);

    const urlPost = 'http://localhost:8080/QuanLyKTX_war_exploded/room';

    useImperativeHandle(ref, () => ({
        postRoom() {
            let isValid = true;
            const postData = {
                roomCode: roomCode,
                maxSlots: maxSlots,
                availableSlots: maxSlots
            }
            if (postData.roomCode === "")
                isValid = false;

            if (maxSlots < 0) {
                alert("Số lượng chỗ ở không thể nhỏ hơn 0");
                isValid = false;
            }    

            /*const data = {
                "roomCode": "B02",
                "maxSlots": 1
            }*/

            if (isValid === true) {
                axios.post(urlPost, postData)
                    .then(res => console.log(res));
            }

            return isValid;
        }
    }))

    return (
        <section>
            <h1 className="title">Thêm phòng</h1>
            <form className="form">
                <label>
                    <span>Mã phòng</span><input type="text" onChange={(event) => setRoomCode(event.target.value)} placeholder="B102" required></input>
                </label>
                <label>
                    <span>Lượng ở tối đa</span><input type="number" onChange={(event) => setMaxSlots(event.target.value)} placeholder="10"></input>
                </label>
            </form>
        </section>
    )
})

export default AddRoom;