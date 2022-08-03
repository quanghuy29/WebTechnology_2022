import { forwardRef, useImperativeHandle, useState } from "react";
import React from "react";
import "./room.module.css";
import axios from "axios";

const UpdateStudentRoom = forwardRef((props, ref) => {
    const studentId = props.studentId;
    const [payMoneyRemain, setMoneyRemain] = useState(props.payMoneyRemain);
    const token = localStorage.getItem("token-auth");
    const urlPut = 'http://localhost:8080/QuanLyKTX_war_exploded/room/student';
    useImperativeHandle(ref, () => ({
        putStudent() {
            let isValid = true;
            const putData = {
                payMoneyRemain: payMoneyRemain,
            }
            if (payMoneyRemain < 0) {
                alert("Số tiền không thể nhỏ hơn không");
                isValid = false;
            }
            if (isValid === true) {
                axios.put(urlPut, putData, { params: { idStudent: studentId } ,
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
            <h1 className="title">Cập nhật sinh viên trong phòng</h1>
            <label>
                <span>Số tiền còn thiếu</span>
                <input type="number" onChange={(event) => setMoneyRemain(event.target.value)} value={payMoneyRemain} required></input>
            </label>
        </section>
    )
})

export default UpdateStudentRoom