import React, {Component} from 'react';
import axios from 'axios';
import {Link, Navigate} from 'react-router-dom';
import { setAuthToken } from '../helpers/setAuthToken';

export default class Login extends Component {

    state = {
        email: '',
        password: '',
        redirect: false,
        authError: false,
        isLoading: false,
        location: {},
    };

    handleEmailChange = event => {
        this.setState({email: event.target.value});
    };
    handlePwdChange = event => {
        this.setState({password: event.target.value});
    };

    handleSubmit = event => {
        event.preventDefault();
        this.setState({isLoading: true});
        const url = 'http://localhost:8080/QuanLyKTX_war_exploded/api-login';
        const email = this.state.email;
        const password = this.state.password;
        

        const loginform = JSON.stringify({
            email: email,
            password: password
        });

        axios.post(url, loginform)
            .then(result => {
                console.log(result.data.message);
                if (result.data.status == 200) {
                    const token = result.data.message;
                    localStorage.setItem('token', token);
                    setAuthToken(token);
                    this.setState({redirect: true, isLoading: false});
                    localStorage.setItem('isLoggedIn', true);
                } else if (result.data.status == 400) {
                    alert(result.data.message);
                    this.setState({authError: true, isLoading: false});
                }
            })
            .catch(error => {
                console.log(error);
                this.setState({authError: true, isLoading: false});
            });
    };

    componentDidMount() {
        const url = 'https://freegeoip.app/json/';
        axios.get(url)
            .then(response => {
                const location = response.data;
                this.setState({ location });
            }) 
            .catch(error => {
                this.setState({ toDashboard: true });
                console.log(error);
            });
    }

    renderRedirect = () => {
        if (this.state.redirect) {
            return <Navigate to='/dashboard'/>
        }
    };

    render() {
        const isLoading = this.state.isLoading;
        return (
            <div className="container">
                <div className="card card-login mx-auto mt-5">
                    <div className="card-header text-center">Login</div>
                    <div className="text-center">
                        <br></br>
                    </div>
                    <div className="card-body">
                        <form onSubmit={this.handleSubmit}>
                            <div className="form-group">
                                <div className="form-label-group">
                                    <input className={"form-control " + (this.state.authError ? 'is-invalid' : '')} id="inputEmail" placeholder="Email address/Username" type="text" name="email" onChange={this.handleEmailChange} autoFocus required/>
                                    <label htmlFor="inputEmail">Email address</label>
                                    <div className="invalid-feedback">
                                        Please provide a valid Email.
                                    </div>
                                </div>
                            </div>
                            <div className="form-group">
                                <div className="form-label-group">
                                    <input type="password" className={"form-control " + (this.state.authError ? 'is-invalid' : '')} id="inputPassword" placeholder="******" name="password" onChange={this.handlePwdChange} required/>
                                    <label htmlFor="inputPassword">Password</label>
                                    <div className="invalid-feedback">
                                        Please provide a valid Password.
                                    </div>
                                </div>
                            </div>
                            <div className="form-group">
                                <div className="checkbox">
                                    <label>
                                        <input type="checkbox" value="remember-me"/>Remember Password
                                    </label>
                                </div>
                            </div>
                            <div className="form-group">
                                <button className="btn btn-primary btn-block" type="submit" disabled={this.state.isLoading ? true : false}>Login &nbsp;&nbsp;&nbsp;
                                    {isLoading ? (
                                        <span className="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
                                    ) : (
                                        <span></span>
                                    )}
                                </button>
                            </div>
                            {/* <div className="form-group">
                                <div className="form-group">
                                    <b>email:</b> gowthaman.nkl1@gmail.com
                                </div>
                                <div className="form-group">
                                    <b>password :</b> password
                                </div>
                            </div> */}
                        </form>
                        <div className="text-center">
                            {/* <Link className="d-block small mt-3" to={'register'}>Register an Account</Link> */}
                            <a className="d-block small" href="forgot-password.html">Forgot Password?</a>
                        </div>
                    </div>
                </div>
                {this.renderRedirect()}
            </div>
        );
    }
}


