import axios from "axios";
import React, { useState, useEffect } from "react";
import classNames from "classnames/bind";
import styles from "./AccountManager.module.scss";

import Header from "../../elements/header";
import Sidebar from "../../elements/sidebar";
import { Link, Redirect } from "react-router-dom";

const URL_ACCOUNT_MANAGER =
  "http://localhost:8080/QuanLyKTX_war_exploded/api-account_manager";
const URL_MANAGER = "http://localhost:8080/QuanLyKTX_war_exploded/api-manager";

const ROLE = {
  admin: 2,
  managerStudent: 1,
  managerRoom: 3,
  accountant: 4,
};

const STATE = {
  block: "0",
  active: "1",
};

const cx = classNames.bind(styles);

function AccountManager() {
  const [mode, setMode] = useState("create");
  const [accounts, setAccounts] = useState([]);
  const [managers, setManagers] = useState([]);
  // ----------- FORM FIELD ---------------------
  const [account, setAccount] = useState({
    userId: "",
    username: "",
    password: "",
    roleId: 1,
    userState: 1,
  });
  const [manager, setManager] = useState({
    managerId: "",
    userId: "",
    fullname: "",
    dateOfBirth: "",
    email: "",
    address: "",
    phone: "",
    yearOfService: 1,
    state: 1,
  });

  // Get token form local Storage
  const token = localStorage.getItem("token-auth");

  // Fetch manager data from server
  const getManager = async () => {
    const managerFetched = await axios.get(URL_MANAGER, {
      params: {
        action: "findAll",
      },
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    return managerFetched;
  };

  // Fetch account data from server
  useEffect(() => {
    axios
      .get(URL_ACCOUNT_MANAGER, {
        params: {
          action: "findAll",
        },
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then(async (res) => {
        const managerList = await getManager();
        setManagers([...managerList.data]);
        setAccounts([...res.data]);
      })
      .catch((e) => console.log(e));
  }, []);

  const renderStateName = (state) => {
    if (state) {
      return <b>Active</b>;
    } else {
      return <b>Block</b>;
    }
  };

  // Two-way binding data in field form
  const handleChangeAccount = (e) => {
    const value = e.target.value;
    setAccount({
      ...account,
      [e.target.name]: value,
    });
  };
  const handleChangeManager = (e) => {
    const value = e.target.value;
    setManager({
      ...manager,
      [e.target.name]: value,
    });
  };

  // Handle Event Button: Add, Edit, Submit, Cancel
  const handleAddAccount = () => {
    setMode("create");
    setAccount({
      username: "",
      password: "",
      roleId: 1,
      userState: 1
    });
    setManager({
      fullname: "",
      dateOfBirth: "",
      email: "",
      address: "",
      phone: "",
      yearOfService: 1,
      userId: "",
    });
  };

  const handleEdit = async (accountData, managerData) => {
    setMode("edit");
    setAccount({
      ...account,
      ...accountData,
      userState: accountData.userState.toString()
    });
    setManager({
      ...manager,
      ...managerData
    })
  };

  const handelPromiseSubmit = async () => {
    const reqAccountData = {
      ...account,
      userState: parseInt(account.userState),
    }
    const resAccount = await axios.post(
      URL_ACCOUNT_MANAGER,
      JSON.stringify(reqAccountData),
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );
    return resAccount
  }

  const handleSubmit = async () => {
    if (mode === "create") {
      // const reqAccountData = {
      //   ...account,
      //   userState: parseInt(account.userState),
      // }

      // // Call API
      // const resAccount = await axios.post(
      //   URL_ACCOUNT_MANAGER,
      //   JSON.stringify(reqAccountData),
      //   {
      //     headers: {
      //       Authorization: `Bearer ${token}`,
      //     },
      //   }
      // );

      handelPromiseSubmit()
        .then(async (res) => {
          const reqManagerData = {
            ...manager,
            userId: parseInt(res.data.message),
            state: parseInt(account.userState),
            yearOfService: parseInt(manager.yearOfService),
            managerId: ""
          };
          await axios.post(
            URL_MANAGER,
            JSON.stringify(reqManagerData),
            {
              headers: {
                Authorization: `Bearer ${token}`,
              },
            }
          );
        })
    } else if (mode === "edit") {
      const reqAccountData = {
        ...account,
        userState: parseInt(account.userState),
      }
      const reqManagerData = {
        ...manager,
        state: parseInt(account.userState),
        yearOfService: parseInt(manager.yearOfService)
      };
      console.log(reqAccountData, reqManagerData)
      // const reqData = {
      //   userId,
      //   username,
      //   password,
      //   roleId: role,
      //   state,
      // };

      // const res = await axios.put(
      //   URL_ACCOUNT_MANAGER,
      //   JSON.stringify(reqData),
      //   {
      //     Authorization: token,
      //   }
      // );
    }
  };

  const handleCancel = () => {
    setAccount({
      userId: 1,
      username: "",
      password: "",
      roleId: 1,
      userState: 1,
    });
    setManager({
      managerId: 1,
      userId: 1,
      fullname: "",
      dateOfBirth: "",
      email: "",
      address: "",
      phone: "",
      yearOfService: 1,
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
                  {accounts.map((account) => {
                    const renderManager = managers.map((manager) => (
                      <tr key={account?.userId}>
                        <td>{account?.userId}</td>
                        <td>{account?.username}</td>
                        <td>{account?.roleId}</td>
                        <td>{renderStateName(account?.userState)}</td>
                        <td className="text-center">
                          <button
                            type="button"
                            className="btn btn-sm btn-info"
                            onClick={(e) => handleEdit(account, manager)}
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
                    ));
                    return renderManager;
                  })}
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
                              name="username"
                              value={account.username}
                              onChange={handleChangeAccount}
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
                              name="password"
                              autoComplete="off"
                              value={account.password}
                              onChange={handleChangeAccount}
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
                                name="roleId"
                                id="role"
                                onChange={handleChangeAccount}
                                value={account.roleId}
                              >
                                <option value={ROLE.admin}>Admin</option>
                                <option value={ROLE.managerStudent}>Manager Student</option>
                                <option value={ROLE.managerRoom}>Manager Room</option>
                                <option value={ROLE.accountant}>
                                  Accountant
                                </option>
                              </select>
                            </div>
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
                              value={manager.fullname}
                              onChange={handleChangeManager}
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
                            value={manager.dateOfBirth}
                            onChange={handleChangeManager}
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
                              value={manager.email}
                              onChange={handleChangeManager}
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
                              value={manager.address}
                              onChange={handleChangeManager}
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
                              value={manager.phone}
                              onChange={handleChangeManager}
                              autoComplete="off"
                              required
                            />
                            <label htmlFor="phone">Phone</label>
                            <span className={cx("underline")}></span>
                          </dd>
                        </dl>
                        <dl className={cx("inputbox")}>
                          <dt className={cx("inputbox-title")}>
                            Input Year Of Service
                          </dt>
                          <dd className={cx("inputbox-content")}>
                          <input
                              id="year-of-service"
                              type="text"
                              name="yearOfService"
                              value={manager.yearOfService}
                              onChange={handleChangeManager}
                              autoComplete="off"
                              required
                            />
                            <label htmlFor="year-of-service">Year of Service</label>
                            <span className={cx("underline")}></span>
                          </dd>
                        </dl>

                        {mode === "edit" && (
                          <dl className={cx("inputbox")}>
                            <dt className={cx("inputbox-title")}>
                              Input state
                            </dt>
                            <dd className={cx("inputbox-content")}>
                              <div className={cx("wrapper")}>
                                <div>
                                  <input
                                    type="radio"
                                    id="active"
                                    name="userState"
                                    value={STATE.active}
                                    checked={account.userState === STATE.active}
                                    onChange={handleChangeAccount}
                                  />
                                  <label
                                    className={cx("label")}
                                    htmlFor="active"
                                  >
                                    active
                                  </label>
                                </div>
                                <div>
                                  <input
                                    type="radio"
                                    id="block"
                                    name="userState"
                                    value={STATE.block}
                                    checked={account.userState === STATE.block}
                                    // checked={state === STATE.block}
                                    onChange={handleChangeAccount}
                                  />
                                  <label
                                    className={cx("label")}
                                    htmlFor="block"
                                  >
                                    block
                                  </label>
                                </div>
                              </div>
                            </dd>
                          </dl>
                        )}

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
