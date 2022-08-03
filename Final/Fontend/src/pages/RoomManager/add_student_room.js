import { forwardRef, useEffect, useImperativeHandle, useState } from "react";
import React from "react";
import "./room.module.css";
import axios from "axios";

const AddStudentRoom = forwardRef((props, ref) => {
    const token = localStorage.getItem("token-auth");
    const urlPost = 'http://localhost:8080/QuanLyKTX_war_exploded/room/student';
    
    useImperativeHandle(ref, () => ({
        postStudentRoom(studentCode) {
            let isValid = true;
            const postData = {
                roomId: props.roomId,
                studentId: studentCode
            }
            if (postData.studentId === "")
                isValid = false;

            if (isValid === true) {
                axios.post(urlPost, postData, {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                })
                    .then(res => {
                        console.log(res);
                        if (res.data.status != null && res.data.status === 0) {
                            alert("Thêm sinh viên vào phòng thất bại do dữ liệu không hợp lệ hoặc sinh viên đã được thêm vào phòng rồi!!");
                        }
                    });
            }
            return isValid;
        }
    }))

    return (
        <div>
            <section>
                <h1 className="title">Thêm sinh viên vào phòng</h1>
                <form className="form">
                    <label>
                        <span>Mã sinh viên</span>
                    </label>
                </form>
            </section>
        </div>
    )
})

export default AddStudentRoom;