import { forwardRef, useEffect, useImperativeHandle, useState } from "react";
import React from "react";
import "./room.css";
import axios from "axios";

const AddBill = forwardRef((props, ref) => {
    const token = localStorage.getItem("token-auth");
    const [money, setMoney] = useState(0);
    const [paymentDate, setPaymentDate] = useState('');
    const urlPostBill = 'http://localhost:8080/QuanLyKTX_war_exploded/room/utility';

    useImperativeHandle(ref, () => ({
        postBill() {
            let isValid = true;
            const postData = {
                roomId: props.roomId,
                money: money,
                paymentDate: paymentDate,
            }
            if (postData.money === "")
                isValid = false;

            if (isValid === true) {
                axios.post(urlPostBill, postData, {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                })
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
                    <label>
                        <span>Ngày</span><input type="text" onChange={(event) => setPaymentDate(event.target.value)} placeholder="YYYY-MM-DD"></input>
                    </label>
                </form>
            </section>
        </div>
    )
})

export default AddBill;