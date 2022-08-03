import React, { Component } from "react";
import { Link } from "react-router-dom";

let roleId = localStorage.getItem('roleId');
export default class Sidebar extends Component {
  render() {
    return (
      <div id="wrapper">
        {roleId === '2' &&
          <ul className="sidebar navbar-nav">
            <li className="nav-item">
              <Link to={"/account-manager"} className="nav-link">
                <i className="fas fa-user"></i>
                <span>&nbsp;Account Manager</span>
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/student"} className="nav-link">
                <i class="far fa-address-book"></i>
                <span>&nbsp;Students</span>
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/room"} className="nav-link">
                <i class="fas fa-home"></i>
                <span>&nbsp;Room</span>
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/bill"} className="nav-link">
                <i class="far fa-money-bill-alt"></i>
                <span>&nbsp;Bill</span>
              </Link>
            </li>
          </ul>
        }
        {roleId === '1' &&
          <ul className="sidebar navbar-nav">
            <li className="nav-item">
              <Link to={"/student"} className="nav-link">
                <i class="far fa-address-book"></i>
                <span>&nbsp;Students</span>
              </Link>
            </li>
          </ul>
        }
        {roleId === '3' &&
          <ul className="sidebar navbar-nav">
            <li className="nav-item">
              <Link to={"/room"} className="nav-link">
                <i class="fas fa-home"></i>
                <span>&nbsp;Room</span>
              </Link>
            </li>
          </ul>
        }
        {roleId === '4' &&
          <ul className="sidebar navbar-nav">
            <li className="nav-item">
              <Link to={"/bill"} className="nav-link">
                <i class="far fa-money-bill-alt"></i>
                <span>&nbsp;Bill</span>
              </Link>
            </li>
          </ul>
        }
      </div>
    );
  }
}
