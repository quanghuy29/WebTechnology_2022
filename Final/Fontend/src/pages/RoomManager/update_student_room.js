import { forwardRef, useImperativeHandle, useState } from "react";
import React from "react";
import "./room.css";
import axios from "axios";

const UpdateStudentRoom = forwardRef((props, ref) => {
    const [studentId, setStudentId] = useState(props.studentId);
    const [payMoneyRemain, setMoneyRemain] = useState(props.payMoneyRemain);
    const [paymentState, setPaymentState] = useState(props.paymentState)

    const urlPut = 'http://localhost:8080/QuanLyKTX_war_exploded/room/student';
    useImperativeHandle(ref, () => ({
        putStudent() {
            let isValid = true;
            const putData = {
                payMoneyRemain: payMoneyRemain,
                paymentState: paymentState
            }
            if (payMoneyRemain < 0) {
                alert("Số tiền không thể nhỏ hơn không");
                isValid = false;
            }
            if (isValid === true) {
                axios.put(urlPut, putData, { params: { idStudent: studentId } })
                    .then(res => console.log(res));
            }
            return isValid;
        }
    }))

    return (
        <section>
            <h1 className="title">Cập nhật sinh viên trong phòng</h1>
            <label>
                <span>Số tiền còn thiếu</span>
                <input type="number" onChange={(event) => setMoneyRemain(event.target.value)} value={payMoneyRemain} required></input>
            </label>
            <label>
                <span>Trạng thái thanh toán</span>
                <select onChange={(event) => setPaymentState(event.target.value)} value={paymentState} >
                    <option value="1">Đã trả đủ tiền phòng</option>
                    <option value="2">Còn thiếu tiền phòng</option>
                </select>
            </label>
        </section>
    )
})

export default UpdateStudentRoom