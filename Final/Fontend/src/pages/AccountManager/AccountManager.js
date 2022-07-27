import axios from "axios";
import React, { useState, useEffect } from "react";
import classNames from "classnames/bind";
import styles from "./AccountManager.module.scss";

import Header from "../../elements/header";
import Sidebar from "../../elements/sidebar";
import { Link, Redirect } from "react-router-dom";

const URL_ACCOUNT_MANAGER =
  "http://localhost:8080/QuanLyKTX_war_exploded/api-account_manager";

const ROLE = {
  admin: 1,
  manager: 2,
  accountant: 3,
};

const STATE = {
  block: '0',
  active: '1',
};

const cx = classNames.bind(styles);

function AccountManager() {
  const [accounts, setAccounts] = useState([]);
  const [account, setAccount] = useState({
    userId: 1,
    username: "",
    password: "",
    role: "",
    state: 1
  })
  const [isInvalid, setIsInvalid] = useState("");
  const [checked, setChecked] = useState(true);
  const [mode, setMode] = useState("create");

  // ----------- FORM FIELD ---------------------
  const [userId, setUserId] = useState(1);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState(1);
  const [state, setState] = useState(0);

  const token = localStorage.getItem("token-auth");

  useEffect(() => {
    axios
      .get(URL_ACCOUNT_MANAGER, {
        params: {
          'action': 'findAll'
        },
        headers: {
          'Authorization': `Bearer ${token}`,
        }
      })
      .then((res) => {
        const newAccountList = res.data.map(account => {
          return {
            userId: account.userId,
            username: account.username,
            password: account.password,
            role: account.roleModel.roleName,
            state: account.userState.toString()
          }
        })
        setAccounts([...newAccountList]);
      })
      .catch((e) => console.log(e));
  }, [account]);

  const renderStateName = (state) => {
    if (state) {
      return <b>Active</b>;
    } else {
      return <b>Block</b>;
    }
  };

  const handleChange = e => {
    const value = e.target.value;
    console.log(value)
    setAccount({
      ...account,
      [e.target.name]: value
    });
  }


  const handleAddAccount = () => {
    setMode("create");
    setAccount({
      username: "",
      password: "",
      role: "",
    })
  };

  const handleEdit = async (data) => {
    setMode("edit")
    setAccount({
      ...account,
      ...data
    })
  };

  const handleSubmit = async () => {
    if (mode === "create") {
      const reqData = {
        ...account,
        state: parseInt(account.state)
      };

      const res = await axios.post(
        URL_ACCOUNT_MANAGER,
        JSON.stringify(reqData),
        {
          Authorization: token,
        }
      );
      console.log(res);
    } else if (mode === "edit") {
      console.log("edit");
      const reqData = {
        userId,
        username,
        password,
        roleId: role,
        state,
      };

      const res = await axios.put(
        URL_ACCOUNT_MANAGER,
        JSON.stringify(reqData),
        {
          Authorization: token,
        }
      );
      console.log(res);
    }
  };

  const handleCancel = () => {
    console.log("cancle");
    setUsername("");
    setPassword("");
    setRole(1);
    setState(0);
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
                    Add account
                  </a>
                </button>
              </div>
              <table className="table- table-bordered">
                <thead>
                  <tr>
                    <th>id</th>
                    <th>username</th>
                    <th>role</th>
                    <th>state</th>
                    <th className="text-center">action</th>
                  </tr>
                </thead>
                <tbody>
                  {accounts.map((account) => (
                    <tr key={account?.userId}>
                      <td>{account?.userId}</td>
                      <td>{account?.username}</td>
                      <td>{account?.role}</td>
                      <td>{renderStateName(account?.useState)}</td>
                      <td className="text-center">
                        <button
                          type="button"
                          className="btn btn-sm btn-info"
                          onClick={(e) => handleEdit(account)}
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
                          <dt className={cx("inputbox-title")}>
                            Input Username
                          </dt>
                          <dd className={cx("inputbox-content")}>
                            <input
                              id="input0"
                              type="text"
                              value={account.username}
                              onChange={handleChange}
                              autoComplete="off"
                              required
                            />
                            <label htmlFor="input0">Username</label>
                            <span className={cx("underline")}></span>
                          </dd>
                        </dl>
                        <dl className={cx("inputbox")}>
                          <dt className={cx("inputbox-title")}>
                            Input Password
                          </dt>
                          <dd className={cx("inputbox-content")}>
                            <input
                              id="input1"
                              type="password"
                              autoComplete="off"
                              value={account.password}
                              onChange={handleChange}
                              required
                            />
                            <label htmlFor="input1">Password</label>
                            <span className={cx("underline")}></span>
                          </dd>
                        </dl>
                        <dl className={cx("inputbox")}>
                          <dt className={cx("inputbox-title")}>Input Role</dt>
                          <dd className={cx("inputbox-content")}>
                            <div className={cx("select")}>
                              <select
                                name="role"
                                id="role"
                                onChange={handleChange}
                                value={account.role}
                              >
                                <option value={ROLE.admin}>Admin</option>
                                <option value={ROLE.manager}>Manager</option>
                                <option value={ROLE.accountant}>
                                  Accountant
                                </option>
                              </select>
                            </div>
                          </dd>
                        </dl>
                        <dl className={cx("inputbox")}>
                          <dt className={cx("inputbox-title")}>Input state</dt>
                          <dd className={cx("inputbox-content")}>
                            <div className={cx("wrapper")}>
                              <div>
                                <input
                                  type="radio"
                                  id="active"
                                  name="state"
                                  value={STATE.active}
                                  checked={account.state === STATE.active}
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
                                  checked={account.state === STATE.block}
                                  // checked={state === STATE.block}
                                  onChange={handleChange}
                                />
                                <label className={cx("label")} htmlFor="block">
                                  block
                                </label>
                              </div>
                            </div>
                          </dd>
                        </dl>

                        <div className={cx("btns")}>
                          <button
                            className={cx("btn", { confirm: true })}
                            onClick={handleSubmit}
                          >
                            <a href="/account-manager">{mode}</a>
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

export default AccountManager;
