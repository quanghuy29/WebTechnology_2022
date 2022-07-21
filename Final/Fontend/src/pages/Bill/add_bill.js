import { forwardRef, useEffect, useImperativeHandle, useState } from "react";
import React from "react";
import "./room.css";
import axios from "axios";

const AddBill = forwardRef((props, ref) => {

    const [roomId, setRoomId] = useState(0);
    const [money, setMoney] = useState(0);

    const urlPost = 'http://localhost:8080/QuanLyKTX_war_exploded/room/student';
    const urlPostBill = 'http://localhost:8080/QuanLyKTX_war_exploded/room/utility';
    
    useImperativeHandle(ref, () => ({
        urlPostBill(money, paymentDate) {
            let isValid = true;
            const postData = {
                roomId: props.roomId,
                money: money,
                paymentDate: paymentDate,
            }
            if (postData.money === "")
                isValid = false;

            if (isValid === true) {
                axios.post(urlPostBill, postData)
                    .then(res => console.log(res));
            }
            return isValid;
        }
    }))

    return (
        <div>
            <section>
                <h1 className="title">Thêm hoá đơn điện nước</h1>
                <form className="form">
                    <label>
                        <span>Số tiền</span><input type="number" onChange={(event) => setMoney(event.target.value)} placeholder="10"></input>
                    </label>
                </form>
            </section>
        </div>
    )
})

export default AddBill;