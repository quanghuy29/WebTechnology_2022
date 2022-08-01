import React, { Component } from 'react';
import { Link, Navigate } from "react-router-dom";

let roleId = localStorage.getItem('roleId');
export default class Header extends Component {

    constructor(props) {
        super(props);
        this.handleClickLogout = this.handleClickLogout.bind(this)
    }

    state = {
        toDashboard: false,
    };

    handleClickLogout() {
        localStorage.removeItem('token');
        localStorage.removeItem('token-auth');
        localStorage.removeItem('roleId');
        localStorage.setItem('isLoggedIn', false);
        this.setState({ toDashboard: true });
    }


    render() {
        if (this.state.toDashboard === true) {
            return <Navigate to='/' />
        }
        return (
            <nav className="navbar navbar-expand navbar-dark bg-dark static-top">

                <Link to={'/'} className="navbar-brand mr-1" style={{ display: 'none' }}>Start Bootstrap</Link>

                <button className="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle">
                    <i className="fas fa-bars"></i>
                </button>

                {roleId === '2' && <span className='role'>&nbsp;Quản lý trưởng</span>}
                {roleId === '1' && <span className='role'>&nbsp;Quản lý sinh viên</span>}
                {roleId === '3' && <span className='role'>&nbsp;Quản lý phòng ở</span>}
                {roleId === '4' && <span className='role'>&nbsp;Kế toán</span>}

                <form className="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0" style={{ display: 'none' }} >
                    <div className="input-group">
                        <input type="text" className="form-control" placeholder="Search for..." aria-label="Search"
                            aria-describedby="basic-addon2" style={{ display: 'none' }} />
                        <div className="input-group-append" style={{ display: 'none' }}>
                            <button className="btn btn-primary" type="button">
                                <i className="fas fa-search"></i>
                            </button>
                        </div>
                    </div>
                </form>


                <button className="logout" onClick={this.handleClickLogout}>
                    <i class="fas fa-sign-out-alt"></i>
                    <span>&nbsp;Log Out</span>
                </button>

            </nav>
        );
    }
}
