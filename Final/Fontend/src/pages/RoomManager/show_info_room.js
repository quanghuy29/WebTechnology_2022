import { forwardRef, useEffect, useImperativeHandle, useState, useRef } from "react";
import React from "react";
import Popup from "../../component/popup/popup";
import AddStudentRoom from "./add_student_room";
import "./room.css";

const ShowRoom = forwardRef((props, ref) => {
    const [change, setChange] = useState(false);
    const [roomID, setRoomID] = useState(0);
    const [room, setRoom] = useState(props);
    const [isFirstTime, setIsFirstTime] = useState(true);
    const [isAddStudent, setIsAddStudent] = useState(false);
    const addStudent = useRef()
    const urlGet = 'http://localhost:8080/QuanLyKTX_war_exploded/room?idRoom=';

    const checkPaymentRoomState = (item) => {
        if (item === 1) return "Đã trả đủ tiền điện nước"
        else if (item === 2) return "Còn thiếu tiền điện nước"
    }

    const checkRoomState = (item) => {
        if (item === 1) return "Đang ở"
        else if (item === 2) return "Đang khoá"
    }

    const addFunction = () => {
        if (addStudent.current.postStudentRoom() === true) {
            setIsAddStudent(!isAddStudent);
        }
        props.reloadStudents();
    }

    useEffect(() => {
        fetch(urlGet + roomID)
            .then(res => res.json())
            .then(data => {
                if (!isFirstTime) setRoom(data);
                setIsFirstTime(false);
            })

    }, [change]);

    useImperativeHandle(ref, () => ({
        update(roomId) {
            setRoomID(roomId);
            setChange(!change);
        }
    }))

    return (
        <div>
            <table>
                <tbody>
                    <tr>
                        <td>Mã phòng</td>
                        <td>{room.roomCode}</td>
                    </tr>
                    <tr>
                        <td>Lượng chỗ ở tối đa</td>
                        <td>{room.maxSlots}</td>
                    </tr>
                    <tr>
                        <td>Lượng chỗ ở còn trống</td>
                        <td>{room.availableSlots}</td>
                    </tr>
                    <tr>
                        <td>Trạng thái thanh toán</td>
                        <td>{checkPaymentRoomState(room.roomPaymentState)}</td>
                    </tr>
                    <tr>
                        <td>Trạng thái phòng</td>
                        <td>{checkRoomState(room.roomState)}</td>
                    </tr>
                    <tr>
                        <td>
                            <i class="fas fa-user-plus" onClick={() => {
                                if (room.roomCode != '')
                                    setIsAddStudent(!isAddStudent)
                            }}> Thêm sinh viên vào phòng</i>
                        </td>
                    </tr>
                </tbody>
            </table>
            {isAddStudent && room.roomCode != '' && <Popup
                content={
                    <AddStudentRoom
                        roomId={roomID}
                        ref={addStudent}
                    ></AddStudentRoom>
                }
                handleClose={() => setIsAddStudent(!isAddStudent)}
                handleConfirm={() => addFunction()}
            />}
        </div>
    )
})

export default ShowRoom