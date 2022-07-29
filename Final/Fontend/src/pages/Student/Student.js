import axios from "axios";
import React, { useState, useEffect } from "react";
import classNames from "classnames/bind";
import styles from "./Student.module.scss";

import Header from "../../elements/header";
import Sidebar from "../../elements/sidebar";

const URL_STUDENT_MANAGER =
  "http://localhost:8080/QuanLyKTX_war_exploded/api-student_manager";

const STATE = {
  block: '0',
  active: '1',
};

const cx = classNames.bind(styles);

function Student() {
  const [mode, setMode] = useState("create");
  const [students, setStudents] = useState([]);
  // ----------- FORM FIELD ---------------------
  const [student, setStudent] = useState({
    studentId: "",
    studentCode: "",
    fullname: "",
    dateOfBirth: "",
    email: "",
    address: "",
    phone: "",
    yearSchool: 1,
    studentClass: "",
    state: 1,
  });


  const token = localStorage.getItem("token-auth");

  useEffect(() => {
    axios
      .get(URL_STUDENT_MANAGER, {
        params: {
          action: "findAll",
        },
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        setStudents([...res.data]);
      })
      .catch((e) => console.log(e));
  }, []);

  const renderStateName = (state) => {
    if (!state) {
      return <b>Block</b>;
    } else {
      return <b>Active</b>;
    }
  };

  const handleChange = e => {
    const value = e.target.value;
    setStudent({
      ...student,
      [e.target.name]: value,
    });
  };

  const handleAddAccount = () => {
    setMode("create");
    setStudent({
      studentId: "",
      studentCode: "",
      fullname: "",
      dateOfBirth: "",
      email: "",
      address: "",
      phone: "",
      yearSchool: 1,
      studentClass: "",
      state: 1,
    });
  };

  const handleEdit = async (data) => {
    setMode("edit");
    setStudent({
      ...student,
      ...data,
      state: data.state.toString(),
    });
  };

  const handleSubmit = async () => {
    if (mode === "create") {
      const reqData = {
        ...student,
        state: parseInt(student.state),
        yearSchool: parseInt(student.yearSchool)
      };

      const res = await axios.post(
        URL_STUDENT_MANAGER,
        JSON.stringify(reqData),
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      console.log(res);
    } else if (mode === "edit") {
      const reqData = {
        ...student,
        state: parseInt(student.state),
        yearSchool: parseInt(student.yearSchool)
      };

      const res = await axios.put(
        URL_STUDENT_MANAGER,
        JSON.stringify(reqData),
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      console.log(res);
    }
  };

  const handleCancel = () => {
    setStudent({
      studentId: "",
      studentCode: "",
      fullname: "",
      dateOfBirth: "",
      email: "",
      address: "",
      phone: "",
      yearSchool: 1,
      studentClass: "",
      state: 1,
    });
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
                    <th>Phone</th>
                    <th>Year School</th>
                    <th>class</th>
                    <th>state</th>

                    <th className="text-center">action</th>
                  </tr>
                </thead>
                <tbody>
                  {students.map((student) => (
                    <tr key={student.studentId}>
                      <td>{student.studentId}</td>
                      <td>{student.studentCode}</td>
                      <td>{student.fullname}</td>
                      <td>{student.dateOfBirth}</td>
                      <td>{student.email}</td>
                      <td>{student.address}</td>
                      <td>{student.phone}</td>
                      <td>{student.yearSchool}</td>
                      <td>{student.studentClass}</td>
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
                      <h2 style={{ textTransform: "uppercase" }}>{mode}</h2>
                      <div className={cx("input-content-wrap")}>
                        <dl className={cx("inputbox")}>
                          <dt className={cx("inputbox-title")}>Input Student Code</dt>
                          <dd className={cx("inputbox-content")}>
                            <input
                              id="student-code"
                              type="text"
                              name="studentCode"
                              value={student.studentCode}
                              onChange={handleChange}
                              autoComplete="off"
                              required
                            />
                            <label htmlFor="student-code">Student Code</label>
                            <span className={cx("underline")}></span>
                          </dd>
                        </dl>
                        <dl className={cx("inputbox")}>
                          <dt className={cx("inputbox-title")}>
                            Input Fullname
                          </dt>
                          <dd className={cx("inputbox-content")}>
                            <input
                              id="fullname"
                              type="text"
                              name="fullname"
                              value={student.fullname}
                              onChange={handleChange}
                              autoComplete="off"
                              required
                            />
                            <label htmlFor="fullname">Fullname</label>
                            <span className={cx("underline")}></span>
                          </dd>
                        </dl>
                        <dl className={cx("inputbox")}>
                          <dt className={cx("inputbox-title")}>Input Date of birth</dt>
                          <dd className={cx("inputbox-content")}>
                          <input 
                            type="date" 
                            id="birthday" 
                            name="dateOfBirth" 
                            value={student.dateOfBirth}
                            onChange={handleChange}
                          />
                          </dd>
                        </dl>
                        <dl className={cx("inputbox")}>
                          <dt className={cx("inputbox-title")}>Input Email</dt>
                          <dd className={cx("inputbox-content")}>
                            <input
                              id="email"
                              type="email"
                              name="email"
                              value={student.email}
                              onChange={handleChange}
                              autoComplete="off"
                              required
                            />
                            <label htmlFor="email">Email</label>
                            <span className={cx("underline")}></span>
                          </dd>
                        </dl>
                        <dl className={cx("inputbox")}>
                          <dt className={cx("inputbox-title")}>
                            Input Address
                          </dt>
                          <dd className={cx("inputbox-content")}>
                          <input
                              id="address"
                              type="text"
                              name="address"
                              value={student.address}
                              onChange={handleChange}
                              autoComplete="off"
                              required
                            />
                            <label htmlFor="address">Address</label>
                            <span className={cx("underline")}></span>
                          </dd>
                        </dl>
                        <dl className={cx("inputbox")}>
                          <dt className={cx("inputbox-title")}>
                            Input Phone
                          </dt>
                          <dd className={cx("inputbox-content")}>
                          <input
                              id="phone"
                              type="text"
                              name="phone"
                              value={student.phone}
                              onChange={handleChange}
                              autoComplete="off"
                              required
                            />
                            <label htmlFor="phone">Phone</label>
                            <span className={cx("underline")}></span>
                          </dd>
                        </dl>
                        <dl className={cx("inputbox")}>
                          <dt className={cx("inputbox-title")}>
                            Input Year School
                          </dt>
                          <dd className={cx("inputbox-content")}>
                          <input
                              id="year-school"
                              type="text"
                              name="yearSchool"
                              value={student.yearSchool}
                              onChange={handleChange}
                              autoComplete="off"
                              required
                            />
                            <label htmlFor="year-school">Year School</label>
                            <span className={cx("underline")}></span>
                          </dd>
                        </dl>
                        <dl className={cx("inputbox")}>
                          <dt className={cx("inputbox-title")}>
                            Input Student Class
                          </dt>
                          <dd className={cx("inputbox-content")}>
                          <input
                              id="student-class"
                              type="text"
                              name="studentClass"
                              value={student.studentClass}
                              onChange={handleChange}
                              autoComplete="off"
                              required
                            />
                            <label htmlFor="student-class">Student Class</label>
                            <span className={cx("underline")}></span>
                          </dd>
                        </dl>
                        {mode === "edit" && (<dl className={cx("inputbox")}>
                          <dt className={cx("inputbox-title")}>Input state</dt>
                          <dd className={cx("inputbox-content")}>
                            <div className={cx("wrapper")}>
                              <div>
                                <input
                                  type="radio"
                                  id="active"
                                  name="state"
                                  value={STATE.active}
                                  checked={student.state === STATE.active}
                                  onChange={handleChange}
                                />
                                <label className={cx("label")} htmlFor="active">
                                  active
                                </label>
                              </div>
                              <div>
                                <input
                                  type="radio"
                                  id="block"
                                  name="state"
                                  value={STATE.block}
                                  checked={student.state === STATE.block}
                                  // checked={state === STATE.block}
                                  onChange={handleChange}
                                />
                                <label className={cx("label")} htmlFor="block">
                                  block
                                </label>
                              </div>
                            </div>
                          </dd>
                        </dl>)}

                        <div className={cx("btns")}>
                          <button
                            className={cx("btn", { confirm: true })}
                            onClick={handleSubmit}
                          >
                            <a href="/student">{mode}</a>
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
