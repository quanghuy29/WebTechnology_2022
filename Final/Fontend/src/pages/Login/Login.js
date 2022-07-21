import React, { useState, useRef, useEffect, useContext } from "react";
import { Link, Navigate, useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import classNames from "classnames/bind";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import styles from "./Login.module.scss";

const cx = classNames.bind(styles);
const LOGIN_URL = "http://localhost:8080/QuanLyKTX_war_exploded/api-login";

function Login() {
  const navigate = useNavigate();
  const location = useLocation();
  const from = location.state?.from?.pathname || "/";

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [blur, setBlur] = useState(false);
  const [isInvalid, setIsInvalid] = useState("");

  const classes = cx("input", "form-control", isInvalid);

  // useEffect(() => {
  //     if (username.trim().length === 0 || password.trim().length === 0) {
  //         setIsInvalid("is-invalid")
  //     } else {
  //         setIsInvalid('')
  //     }
  // }, [blur])

  const handleValidate = () => {
    setBlur(!blur);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    console.log("login")

    const dataLogin = JSON.stringify({ email: username, password });

    try {
      const res = await axios.post(LOGIN_URL, dataLogin);

      console.log(res)
      let optionToast;
      if (res.data?.status === 200) {
        optionToast = "success";

        const token = res?.data?.message;
        localStorage.setItem("token-auth", token)
        const roles = res?.data?.status;
        setUsername("");
        setPassword("");

        navigate(from, { replace: true });

        toast[optionToast](res.data.message);
      } else if (res.data?.status === 400) {
        optionToast = "error";
        toast[optionToast](res.data.message);
      }
    } catch (error) {
      toast.error("No server response");
    }
  };

  return (
    <div className={cx("container-login")}>
      <ToastContainer />
      <div className={cx("card-login", "card")}>
        <div className={cx("heading", "card-header")}>Login</div>
        <div className="card-body">
          <form onSubmit={handleSubmit}>
            <div className={cx("form-group")}>
              <div className={cx("form-control-group", "form-label-group")}>
                <input
                  className={classes}
                  type="text"
                  id="username"
                  name="username"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                  onBlur={handleValidate}
                  autoFocus
                  required
                />
                <label className={cx("label")} htmlFor="username">
                  Username:{" "}
                </label>
                {/* <div className="invalid-feedback">
                                    Please provide a valid username.
                                </div> */}
              </div>
            </div>
            <div className={cx("form-group")}>
              <div className={cx("form-control-group", "form-label-group")}>
                <input
                  className={classes}
                  type="password"
                  id="password"
                  name="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  onBlur={handleValidate}
                  required
                />
                <label className={cx("label")} htmlFor="password">
                  Password:{" "}
                </label>
                {/* <div className="invalid-feedback">
                                    Please provide a valid password.
                                </div> */}
              </div>
            </div>

            <div className={cx("form-group")}>
              <button className="btn btn-primary btn-block" type="submit">
                Login
                {/* spinner loading */}
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Login;
