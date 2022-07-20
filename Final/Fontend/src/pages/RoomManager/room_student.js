import { forwardRef, useEffect, useImperativeHandle, useState, useRef } from "react";
import Table from "../../component/table/table";
import React from "react";
import "./room.css";
import DeleteStudentRoom from "./delete_student_room";
import Popup from "../../component/popup/popup";
import UpdateStudentRoom from "./update_student_room";

const StudentRoom = forwardRef((props, ref) => {
    const urlGet = 'http://localhost:8080/QuanLyKTX_war_exploded/room/student?idRoom=';
    const [roomID, setRoomID] = useState(0);
    const [students, setStudents] = useState('')
    const [student, setStudent] = useState('')
    const [change, setChange] = useState(false);
    const [isDelete, setIsDelete] = useState(false);
    const [isUpdate, setIsUpdate] = useState(false);
    const deleteChildRef = useRef()
    const updateChildRef = useRef()

    const deleteFunction = () => {
        deleteChildRef.current.deleteStudent();
        setIsDelete(!isDelete);
        setTimeout(() => props.reloadRoom(), 500);
        setTimeout(() => setChange(!change), 500);
    }

    const updateFunction = () => {
        if (updateChildRef.current.putStudent() === true) {
            setIsUpdate(!isUpdate)
        }
        setTimeout(() => setChange(!change), 500);
    }

    const checkPaymentState = (item) => {
        if (item === 1) return <td className = "done">Đã trả đủ tiền phòng</td>
        else if (item === 2) return <td className = "notdone">Còn thiếu tiền phòng</td>
    }

    useEffect(() => {
        fetch(urlGet + roomID)
            .then(res => res.json())
            .then(data => setStudents(data))
    }, [change, roomID]);

    useImperativeHandle(ref, () => ({
        update(roomId) {
            setRoomID(roomId);
            setTimeout(() => setChange(!change), 500);
        }
    }))

    const customerTableHead = [
        'Mã sinh viên',
        'Họ tên sinh viên',
        'Tiền phòng còn thiếu',
        'Trạng thái thanh toán'
    ]
    const renderHead = (item, index) => <th key={index}>{item}</th>

    const renderBody = (item, index) => (
        <tr key={index}>
            <td>{item.studentCode}</td>
            <td>{item.studentName}</td>
            <td>{item.payMoneyRemain}</td>
            {checkPaymentState(item.paymentState)}
            <td><i class="fas fa-edit" onClick={() => {
                setStudent(item);
                setIsUpdate(!isUpdate);
            }}></i>
            </td>
            <td><i className="fas fa-minus-circle" onClick={() => {
                setStudent(item);
                setIsDelete(!isDelete);
            }}></i>
            </td>
        </tr>
    )
    return (
        <div>
            <Table
                headData={customerTableHead}
                renderHead={(item, index) => renderHead(item, index)}
                bodyData={students}
                renderBody={(item, index) => renderBody(item, index)}
            />
            {isDelete && <Popup
                content={
                    <DeleteStudentRoom
                        studentId={student.studentId}
                        ref={deleteChildRef}
                    ></DeleteStudentRoom>}
                handleClose={() => setIsDelete(!isDelete)}
                handleConfirm={() => deleteFunction()}
            />}
            {isUpdate && <Popup
                content={
                    <UpdateStudentRoom
                        studentId={student.studentId}
                        payMoneyRemain={student.payMoneyRemain}
                        paymentState={student.paymentState}
                        ref={updateChildRef}
                    ></UpdateStudentRoom>
                }
                handleClose={() => setIsUpdate(!isUpdate)}
                handleConfirm={() => updateFunction()}
            />
            }
        </div>
    )
})

export default StudentRoom;