import React from "react";
import Header from "../../elements/header";
import Sidebar from "../../elements/sidebar";

const Welcome = () => {
    return (
        <div>
            <Header />
            <div id="wrapper">
                <Sidebar></Sidebar>
                <div className="welcome">Chào mừng tới trang Quản lý ký túc xá</div>
                <footer className="sticky-footer">
                    <div className="container my-auto">
                        <div className="copyright text-center my-auto">
                            <span>Copyright © Your Website <div>{(new Date().getFullYear())}</div></span>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
    )
}

export default Welcome;