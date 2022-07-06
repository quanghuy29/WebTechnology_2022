import { forwardRef, useEffect, useImperativeHandle, useState } from "react";
import Table from "../../component/table/table";
import React from "react";

const StudentRoom = forwardRef((props, ref) => {
    const urlGet = 'http://localhost:8080/QuanLyKTX_war_exploded/room/student?idRoom=';
    const [roomID, setRoomID] = useState(0);
    const [students, setStudents] = useState('')
    const [student, setStudent] = useState('')
    const [change, setChange] = useState(false);

    useEffect(() => {
        fetch(urlGet + roomID)
            .then(res => res.json())
            .then(data => setStudents(data))
    }, [change]);

    useImperativeHandle(ref, () => ({
        update(roomId) {
            console.log(roomId + ' update');
            setRoomID(roomId);
            setChange(!change);
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
            <td>{item.studentId}</td>
            <td>Huy</td>
            <td>{item.payMoneyRemain}</td>
            <td>{item.paymentState}</td>
            <td><i class="fas fa-edit"></i>
            </td>
            <td><i class="fas fa-minus-circle"></i>
            </td>
        </tr>
    )
    return (
        <Table
            headData={customerTableHead}
            renderHead={(item, index) => renderHead(item, index)}
            bodyData={students}
            renderBody={(item, index) => renderBody(item, index)}
        />
    )
})

export default StudentRoom;