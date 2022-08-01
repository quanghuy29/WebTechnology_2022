import { forwardRef, useEffect, useImperativeHandle, useState, useRef } from "react";
import Table from "../../component/table/table";
import React from "react";
import "./room.css";
import Popup from "../../component/popup/popup";
import UpdateBill from "./update_bill";

const RoomBill = forwardRef((props, ref) => {
    const urlGetBill = 'http://localhost:8080/QuanLyKTX_war_exploded/room/utility?roomId=';
    const [roomID, setRoomID] = useState(0);
    const [bills, setBills] = useState('')
    const [bill, setBill] = useState('')
    const [change, setChange] = useState(false);
    const [isUpdate, setIsUpdate] = useState(false);
    const updateChildRef = useRef()
    const token = localStorage.getItem("token-auth");
    const updateFunction = () => {
        if (updateChildRef.current.putBill() === true) {
            setIsUpdate(!isUpdate)
        }
        setTimeout(() => setChange(!change), 500);
    }

    const checkPaymentState = (item) => {
        if (item === 1) return "Đã thanh toán"
        else if (item === 2) return "Chưa thanh toán"
    }

    useEffect(() => {
        fetch(urlGetBill + roomID,
            {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            })
            .then(res => res.json())
            .then(data => setBills(data))
    }, [change, roomID]);

    useImperativeHandle(ref, () => ({
        update(roomId) {
            setRoomID(roomId);
            setTimeout(() => setChange(!change), 500);
        }
    }))

    const customerTableHead = [
        'Mã hoá đơn',
        'Tiền điện + nước',
        'Ngày',
        'Trạng thái thanh toán'
    ]
    const renderHead = (item, index) => <th key={index}>{item}</th>

    const renderBody = (item, index) => (
        <tr key={index}>
            <td>{item.billId}</td>
            <td>{item.money}</td>
            <td>{item.paymentDate}</td>
            <td>{checkPaymentState(item.state)}</td>
            <td><i class="fas fa-edit" onClick={() => {
                setBill(item);
                setIsUpdate(!isUpdate);
            }}></i>
            </td>
        </tr>
    )

    return (
        <div>
            <Table
                headData={customerTableHead}
                renderHead={(item, index) => renderHead(item, index)}
                bodyData={bills}
                renderBody={(item, index) => renderBody(item, index)}
            />

            {isUpdate && <Popup
                content={
                    <UpdateBill
                        billId={bill.billId}
                        money={bill.money}
                        state={bill.state}
                        ref={updateChildRef}
                    ></UpdateBill>
                }
                handleClose={() => setIsUpdate(!isUpdate)}
                handleConfirm={() => updateFunction()}
            />
            }
        </div>
    )
})

export default RoomBill;