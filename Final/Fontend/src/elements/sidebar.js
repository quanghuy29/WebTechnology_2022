import React, { Component } from 'react';
import { Link } from 'react-router-dom';

export default class Sidebar extends Component {
    render() {
        return (
            <div id="wrapper">
                <ul className="sidebar navbar-nav">
                    <li className="nav-item">
                        <Link to={'/room'} className="nav-link"><i className="fas fa-fw fa-chart-area"></i>
                            <span>&nbsp;Room</span></Link>
                    </li>
                    <li className="nav-item">
<<<<<<< HEAD
                        <Link to={'/bill'} className="nav-link">
                            <span>&nbsp;Bill</span></Link>
=======
                        <Link to={'/account-manager'} className="nav-link"><i className="fas fa-fw fa-chart-area"></i>
                            <span>&nbsp;Account Manager</span></Link>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="tables.html">
                            <i className="fas fa-fw fa-table"></i>
                            <span>&nbsp;Tables</span></a>
>>>>>>> WEB_HUY
                    </li>
                </ul>
            </div>
        );
    }
}
