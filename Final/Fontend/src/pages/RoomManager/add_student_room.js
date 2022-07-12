import { forwardRef, useEffect, useImperativeHandle, useState } from "react";
import React from "react";
import "./room.css";
import axios from "axios";
import SearchStudent from "../../component/searchStudent/searchstudent";


const AddStudentRoom = forwardRef((props, ref) => {
    const [studentCode, setStudentCode] = useState('');
    const [students, setStudents] = useState('');
    const urlPost = 'http://localhost:8080/QuanLyKTX_war_exploded/room/student';
    const urlGetStudent = 'http://localhost:8080/QuanLyKTX_war_exploded/api-student_manager';
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

    useEffect(() => {
        axios.get(urlGetStudent, { params: { action: "findAll" } })
            .then(res => { setStudents(res.data); console.log(res.data) });
    }, []);

    const clickItem = (item) => {
        setStudentCode(item.studentCode);
    }

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
            <SearchStudent data={students} showItem={clickItem}></SearchStudent>
        </div>
    )
})

export default AddStudentRoom;