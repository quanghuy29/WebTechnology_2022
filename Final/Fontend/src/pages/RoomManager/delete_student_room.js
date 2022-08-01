import React, { forwardRef, useImperativeHandle } from "react";
import "./room.module.css";
import axios from "axios";

const DeleteStudentRoom = forwardRef((props, ref) => {
    const studentId = props.studentId;
    const token = localStorage.getItem("token-auth");
    const urlDelete = 'http://localhost:8080/QuanLyKTX_war_exploded/room/student';

    useImperativeHandle(ref, () => ({
        deleteStudent() {
            axios.delete(urlDelete, {params: {idStudent: studentId},
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            })
                .then(res => console.log(res));
        }
    }))

    return (
        <section>
            <span>Bạn chắc chắn muốn xoá sinh viên khỏi phòng?</span>
        </section>
    )
})
export default DeleteStudentRoom;