import axios from "axios";
import React, { useState, useEffect } from "react";
import classNames from "classnames/bind";
import styles from "./Student.module.scss";

import Header from "../../elements/header";
import Sidebar from "../../elements/sidebar";
import { Link, Redirect } from "react-router-dom";

const URL_STUDENT_MANAGER =
  "http://localhost:8080/QuanLyKTX_war_exploded/api-student_manager";

const ROLE = {
  admin: 1,
  manager: 2,
  accountant: 3,
};

const STATE = {
  block: 1,
  active: 0,
};

const cx = classNames.bind(styles);

function Student() {
  const [students, setStudents] = useState([]);
  const [checked, setChecked] = useState(true);
  const [mode, setMode] = useState("create")

  // ----------- FORM FIELD ---------------------


    const [id, setId] = useState('')
    const [code, setCode] = useState('')
    const [fullname, setFullname] = useState('')
    const [dob, setDob] = useState('')
    const [email, setEmail] = useState('')
    const [address, setAddress] = useState('')

  useEffect(() => {
    axios
      .get(URL_STUDENT_MANAGER)
      .then((res) => {
        console.log(res);
        setStudents([...res.data]);
      })
      .catch((e) => console.log(e));
  }, []);

  const renderRoleName = (roleId) => {
    switch (roleId) {
      case ROLE.admin:
        return <b>Admin</b>;
      case ROLE.manager:
        return <b>manager</b>;
      case ROLE.accountant:
        return <b>accountant</b>;
      default:
        break;
    }
  };

  const renderStateName = (state) => {
    if (state) {
      return <b>Block</b>;
    } else {
      return <b>Active</b>;
    }
  };

  const handleAddAccount = () => {
    setMode("create");
    setId("")
    setCode("")
    setFullname("")
    setDob("")
    setEmail("")
    setAddress("")
  };

  const handleEdit = async (data) => {
    console.log("edit")
    setMode("edit");
    setId(data.id)
    setCode(data.code)
    setFullname(data.fullname)
    setDob(data.dob)
    setEmail(data.email)
    setAddress(data.address)
  };

  const handleSubmit = async () => {
    if(mode === "create") {
      const reqData = {
        id,
        code,
        fullname,
        dob,
        email,
        address,
      };
      
      console.log(reqData)
      return 
      const res = await axios.post(URL_STUDENT_MANAGER, JSON.stringify(reqData));
      console.log(res);
    } else if (mode === "edit") {
      console.log("edit")
      const reqData = {
        id,
        code,
        fullname,
        dob,
        email,
        address,
      };
  
      const res = await axios.put(URL_STUDENT_MANAGER, JSON.stringify(reqData));
      console.log(res);
    }
  };

  const handleCancel = () => {
    setId("")
    setCode("")
    setFullname("")
    setDob("")
    setEmail("")
    setAddress("")
  };

  return (
    <div className="account-container">
      <Header />
      <div id="wrapper">
        <Sidebar />
        <div className={cx("content-wrapper")}>
          <div className="card">
            <div className="card-header">
              <i className="fas fa-table" />
              &nbsp;&nbsp;Employees List
            </div>
            <div className="card-body">
              <div className={cx("row-table")}>
                <button
                  className={cx("btn-top")}
                  type="button"
                  onClick={handleAddAccount}
                >
                  <a href="#modal-opened" className="link-1" id="modal-closed">
                    Add
                  </a>
                </button>
              </div>
              <table className="table- table-bordered">
                <thead>
                  <tr>
                    <th>id</th>
                    <th>code</th>
                    <th>full name</th>
                    <th>Dob</th>
                    <th>Email</th>
                    <th>Address</th>
                    <th className="text-center">action</th>
                  </tr>
                </thead>
                <tbody>
                  {students.map((student) => (
                    <tr key={student.userId}>
                      <td>{student.userId}</td>
                      <td>{student.username}</td>
                      <td>{renderRoleName(student.roleId)}</td>
                      <td>{renderStateName(student.state)}</td>
                      <td className="text-center">
                        <button
                          type="button"
                          className="btn btn-sm btn-info"
                          onClick={(e) => handleEdit(student)}
                        >
                          <span className={cx("text")}>
                            <a
                              href="#modal-opened"
                              className="link-1"
                              id="modal-closed"
                            >
                              Edit
                            </a>
                          </span>
                        </button>
                        {/* &nbsp; | &nbsp;
                        <button type="button" className="btn btn-sm btn-danger" onClick={handleDelete}>
                          <span className={cx("text")}>Delete</span>
                        </button> */}
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>

            <div className={cx("container-modal")}>
              <div className={cx("modal-container")} id="modal-opened">
                <div className={cx("modal")}>
                  <div id="wrap" className={cx("input")}>
                    <section className={cx("input-content")}>
                      <h2 style={{textTransform: 'uppercase'}}>
                        {mode}
                      </h2>
                      <div className={cx("input-content-wrap")}>
                        <dl className={cx("inputbox")}>
                          <dt className={cx("inputbox-title")}>
                            Input Code
                          </dt>
                          <dd className={cx("inputbox-content")}>
                            <input
                              id="input0"
                              type="text"
                              value={code}
                              onChange={(e) => setCode(e.target.value)}
                              autoComplete="off"
                              readOnly
                              onFocus={(e) =>
                                e.target.removeAttribute("readonly")
                              }
                              required
                            />
                            <label htmlFor="input0">Code</label>
                            <span className={cx("underline")}></span>
                          </dd>
                        </dl>
                        <dl className={cx("inputbox")}>
                          <dt className={cx("inputbox-title")}>
                            Input Fullname
                          </dt>
                          <dd className={cx("inputbox-content")}>
                            <input
                              id="input1"
                              type="text"
                              value={fullname}
                              onChange={(e) => setFullname(e.target.value)}
                              autoComplete="off"
                              readOnly
                              onFocus={(e) =>
                                e.target.removeAttribute("readonly")
                              }
                              required
                            />
                            <label htmlFor="input1">Fullname</label>
                            <span className={cx("underline")}></span>
                          </dd>
                        </dl>
                        <dl className={cx("inputbox")}>
                          <dt className={cx("inputbox-title")}>
                            Input Dob
                          </dt>
                          <dd className={cx("inputbox-content")}>
                            <input
                              id="input2"
                              type="text"
                              value={dob}
                              onChange={(e) => setDob(e.target.value)}
                              autoComplete="off"
                              readOnly
                              onFocus={(e) =>
                                e.target.removeAttribute("readonly")
                              }
                              required
                            />
                            <label htmlFor="input2">Date of birth</label>
                            <span className={cx("underline")}></span>
                          </dd>
                        </dl>
                        <dl className={cx("inputbox")}>
                          <dt className={cx("inputbox-title")}>
                            Input Email
                          </dt>
                          <dd className={cx("inputbox-content")}>
                            <input
                              id="input3"
                              type="email"
                              value={email}
                              onChange={(e) => setEmail(e.target.value)}
                              autoComplete="off"
                              readOnly
                              onFocus={(e) =>
                                e.target.removeAttribute("readonly")
                              }
                              required
                            />
                            <label htmlFor="input3">Email</label>
                            <span className={cx("underline")}></span>
                          </dd>
                        </dl>
                        <dl className={cx("inputbox")}>
                          <dt className={cx("inputbox-title")}>
                            Input Address
                          </dt>
                          <dd className={cx("inputbox-content")}>
                            <input
                              id="input4"
                              type="text"
                              value={address}
                              onChange={(e) => setAddress(e.target.value)}
                              autoComplete="off"
                              readOnly
                              onFocus={(e) =>
                                e.target.removeAttribute("readonly")
                              }
                              required
                            />
                            <label htmlFor="input4">Address</label>
                            <span className={cx("underline")}></span>
                          </dd>
                        </dl>

                        <div className={cx("btns")}>
                          <button
                            className={cx("btn", { confirm: true })}
                            onClick={handleSubmit}
                          >
                            {mode}
                            {/* <a href="/account-manager">{mode}</a> */}
                          </button>
                          <button
                            className={cx("btn", { cancel: true })}
                            onClick={handleCancel}
                          >
                            <a href="#">cancel</a>
                          </button>
                        </div>
                      </div>
                    </section>
                  </div>
                  <a href="#" className={cx("link-2")} />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Student;
