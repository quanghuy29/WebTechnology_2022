import { forwardRef, useImperativeHandle, useState } from "react";
import React from "react";
import "./room.css";
import axios from "axios";

const UpdateBill = forwardRef((props, ref) => {
    const billId = props.billId;
    const [money, setMoney] = useState(props.money);
    const [state, setState] = useState(props.state);
    const token = localStorage.getItem("token-auth");
    const urlPut = 'http://localhost:8080/QuanLyKTX_war_exploded/room/utility';
    useImperativeHandle(ref, () => ({
        putBill() {
            let isValid = true;
            const putData = {
                money: money,
                state: state
            }
            if (money < 0) {
                alert("Số tiền không thể nhỏ hơn không");
                isValid = false;
            }
            if (isValid === true) {
                axios.put(urlPut, putData, {
                    params: { billId: billId },
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
        <section>
            <h1 className="title">Cập nhật hoá đơn</h1>
            <label>
                <span>Số tiền</span>
                <input type="number" onChange={(event) => setMoney(event.target.value)} value={money} required></input>
                <span>Trạng thái</span>
                <select onChange={(event) => setState(event.target.value)} value={state} >
                    <option value="1">Đã thanh toán</option>
                    <option value="2">Chưa thanh toán</option>
                </select>
            </label>
        </section>
    )
})

export default UpdateBill