import React, { useEffect, useRef, useState } from "react";
import Header from "../../elements/header";
import Sidebar from "../../elements/sidebar";
import { Link } from "react-router-dom";
import Table from "../../component/table/table";
import Popup from "../../component/popup/popup";
import SearchBar from "../../component/searchbar/searchbar";

import RoomBill from "./room_bill";
import ShowRoom from "./show_info_room";
import UpdateRoom from "./update_room";
import "./room.css";

const Bill = () => {
    const urlGet = 'http://localhost:8080/QuanLyKTX_war_exploded/room';
    const [rooms, setRooms] = useState('');
    const [room, setRoom] = useState('');
    const [isUpdate, setIsUpdate] = useState(false);
    const [change, setChange] = useState(false);
    const updateChildRef = useRef()
    const getBill = useRef()
    const getRoom = useRef()
    const token = localStorage.getItem("token-auth");
    useEffect(() => {
        fetch(urlGet,
            {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            })
            .then(res => res.json())
            .then(data => { setRooms(data); console.log(data); })
    }, [change]);

    const updateFunction = () => {
        if (updateChildRef.current.putRoom() === true) {
            setIsUpdate(!isUpdate)
            getRoom.current.update(room.roomId);
            setTimeout(() => setChange(!change), 500);
        }
    }

    const customerTableHead = [
        'Room ID',
        'Room Code',
    ]

    const clickItem = (item) => {
        setRoom(item);
        getBill.current.update(item.roomId);
        getRoom.current.update(item.roomId)
    }

    const renderHead = (item, index) => <th key={index}>{item}</th>

    const renderBody = (item, index) => (
        <tr key={index}>
            <td>{item.roomId}</td>
            <td><div className="room" onClick={() => clickItem(item)}>{item.roomCode}</div></td>
            <td><i className="fas fa-pen" onClick={() => {
                clickItem(item);
                setIsUpdate(!isUpdate);
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
                            <li className="breadcrumb-item active">Bill</li>
                            <SearchBar data={rooms} showItem={clickItem}></SearchBar>
                        </ol>
                    </div>
                    <div className="container-fluid-half-l">
                        <div className="card mx-auto">
                            <div className="container-fluid">
                                <ol className="breadcrumb">
                                    <li className="breadcrumb-item active">Bill</li>
                                </ol>
                            </div>
                            <div className="card-body">
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
                                <Table
                                    headData={customerTableHead}
                                    renderHead={(item, index) => renderHead(item, index)}
                                    bodyData={rooms}
                                    renderBody={(item, index) => renderBody(item, index)}
                                />
                            </div>
                        </div>
                    </div>

                    <div className="container-fluid-half-r">
                        <ShowRoom
                            roomId={''}
                            roomCode={''}
                            maxSlots={0}
                            availableSlots={0}
                            roomPaymentState={1}
                            roomState={1}
                            ref={getRoom}
                            reloadBills={() => getBill.current.update(room.roomId)}
                            reloadRoom={() => setChange(!change)}
                        ></ShowRoom>
                    </div>

                    <div className="container-fluid-half-r">
                        <RoomBill
                            roomId={room.roomId}
                            ref={getBill}
                            reloadRoom={() => { setChange(!change); getRoom.current.update(room.roomId); }}
                        ></RoomBill>
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

export default Bill;