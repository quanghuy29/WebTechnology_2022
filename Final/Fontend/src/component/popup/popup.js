import React from "react";
import "./popup.css"

const Popup = props => {
    return (
        <div className="popup-box">
            <div className="box">
                <div className="close-icon" onClick={props.handleClose}></div>
                {props.content}
                <button className="submit" onClick={props.handleConfirm}>Xác nhận</button>
            </div>
        </div>
    );
};

export default Popup;