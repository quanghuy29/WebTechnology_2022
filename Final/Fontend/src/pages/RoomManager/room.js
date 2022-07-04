import React, { useEffect, useRef, useState } from "react";
import Header from "../../elements/header";
import Sidebar from "../../elements/sidebar";
import { Link } from "react-router-dom";
import Table from "../../component/table/table";
import Popup from "../../component/popup/popup";
import AddRoom from "./add_room";
import UpdateRoom from "./update_room";
import DeleteRoom from "./delete_room";


const Room = () => {
    const urlGet = 'http://localhost:8080/QuanLyKTX_war_exploded/room';
    const [rooms, setRooms] = useState('');
    const [room, setRoom] = useState('');
    const [isOpenPopup, setIsOpenPopup] = useState(false);
    const [isUpdate, setIsUpdate] = useState(false);
    const [isDelete, setIsDelete] = useState(false);
    const [change, setChange] = useState(false);
    const deleteChildRef = useRef()
    const addChildRef = useRef()
    const updateChildRef = useRef()

    useEffect(() => {
        fetch(urlGet)
            .then(res => res.json())
            .then(data => setRooms(data))
    }, [change]);

    const deleteFunction = () => {
        deleteChildRef.current.deleteRoom();
        setIsDelete(!isDelete);
        setTimeout(() => setChange(!change), 500);
    }

    const addFunction = () => {
        if (addChildRef.current.postRoom() === true) {
            setIsOpenPopup(!isOpenPopup)
        }
        setTimeout(() => setChange(!change), 500);
    }

    const updateFunction = () => {
        if (updateChildRef.current.putRoom() === true) {
            setIsUpdate(!isUpdate)
        }
        setTimeout(() => setChange(!change), 500);
    }

    const customerTableHead = [
        'Room ID',
        'Room Code',
        'Max Slots',
        'Available Slots',
        'Room Payment State',
        'RoomState'
    ]

    const renderHead = (item, index) => <th key={index}>{item}</th>

    const checkPaymentRoomState = (item) => {
        if (item.roomPaymentState === 1) return "Đã trả đủ tiền điện nước"
        else return "Còn thiếu tiền điện nước"
    }

    const checkRoomState = (item) => {
        if (item.roomState === 1) return "Đang ở"
        else if (item.roomState === 2) return "Đang khoá"
    }

    const renderBody = (item, index) => (
        <tr key={index}>
            <td>{item.roomId}</td>
            <td>{item.roomCode}</td>
            <td>{item.maxSlots}</td>
            <td>{item.availableSlots}</td>
            <td>{checkPaymentRoomState(item)}</td>
            <td>{checkRoomState(item)}</td>
            <td><i className="fas fa-pen" onClick={() => {
                setRoom(item);
                setIsUpdate(!isUpdate);
            }}></i>
            </td>
            <td><i className="fas fa-trash" onClick={() => {
                setRoom(item);
                setIsDelete(!isDelete);
            }}></i>
            </td>
        </tr>
    )

    return (
        <div>
            <Header />
            <div id="wrapper">
                <Sidebar></Sidebar>
                <div id="content-wrapper">
                    <div className="container-fluid">
                        <ol className="breadcrumb">
                            <li className="breadcrumb-item">
                                <Link to={'/dashboard'} >Dashboard</Link>
                            </li>
                            <li className="breadcrumb-item active">Room</li>
                            <button className="ml-auto" onClick={() => setIsOpenPopup(!isOpenPopup)}>Add Room</button>
                        </ol>
                    </div>
                    <div className="container-fluid">
                        <div className="card mx-auto">
                            <div className="card-header">Room</div>
                            <div className="card-body">
                                {isOpenPopup && <Popup
                                    content={
                                        <AddRoom
                                            ref={addChildRef}
                                        ></AddRoom>}
                                    handleClose={() => setIsOpenPopup(!isOpenPopup)}
                                    handleConfirm={() => addFunction()}
                                />}
                                {isUpdate && <Popup
                                    content={
                                        <UpdateRoom
                                            roomId={room.roomId}
                                            roomCode={room.roomCode}
                                            maxSlots={room.maxSlots}
                                            availableSlots={room.availableSlots}
                                            roomPaymentState={room.roomPaymentState}
                                            roomState={room.roomState}
                                            ref={updateChildRef}
                                        ></UpdateRoom>}
                                    handleClose={() => setIsUpdate(!isUpdate)}
                                    handleConfirm={() => updateFunction()}
                                />}
                                {isDelete && <Popup
                                    content={
                                        <DeleteRoom
                                            roomId={room.roomId}
                                            ref={deleteChildRef}
                                        ></DeleteRoom>}
                                    handleClose={() => setIsDelete(!isDelete)}
                                    handleConfirm={() => deleteFunction()}
                                />}
                                <Table
                                    headData={customerTableHead}
                                    renderHead={(item, index) => renderHead(item, index)}
                                    bodyData={rooms}
                                    renderBody={(item, index) => renderBody(item, index)}
                                />
                            </div>
                        </div>
                    </div>
                    <footer className="sticky-footer">
                        <div className="container my-auto">
                            <div className="copyright text-center my-auto">
                                <span>Copyright © Your Website <div>{(new Date().getFullYear())}</div></span>
                            </div>
                        </div>
                    </footer>
                </div>
            </div>

        </div>
    )
}

export default Room;