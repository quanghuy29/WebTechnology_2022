import { forwardRef, useImperativeHandle, useState } from "react";
import React from "react";
import "./room.css";
import axios from "axios";

const AddStudentRoom = forwardRef((props, ref) => {
    const [studentCode, setStudentCode] = useState('');
    const urlPost = 'http://localhost:8080/QuanLyKTX_war_exploded/room/student';

    useImperativeHandle(ref, () => ({
        postStudentRoom() {
            let isValid = true;
            const postData = {
                roomId: props.roomId,
                studentId: studentCode
            }
            if (postData.studentId === "")
                isValid = false;

            if (isValid === true) {
                axios.post(urlPost, postData)
                    .then(res => console.log(res));
            }

            return isValid;
        }
    }))

    return (
        <section>
            <h1 className="title">Thêm sinh viên vào phòng</h1>
            <form className="form">
                <label>
                    <span>Mã sinh viên</span><input type="text" onChange={(event) => setStudentCode(event.target.value)} placeholder="20183764" required></input>
                </label>
            </form>
        </section>
    )
})

export default AddStudentRoom;