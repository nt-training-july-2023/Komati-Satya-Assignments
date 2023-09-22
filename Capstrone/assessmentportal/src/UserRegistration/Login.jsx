
import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import './LoginStyles.css';
import Swal from "sweetalert2";
import { Link } from "react-router-dom";
import { FaEye, FaEyeSlash } from 'react-icons/fa';
import UserApi from "../APIs/UserApi";
import DisableBackButton from "../APIs/disableBackButton";

const Login = () => {
    const [loginData, setLoginData] = useState({
        email: "",
        password: ""
    });
    const [errors, setErrors] = useState({});
    const navigate = useNavigate();
    const [showPassword, setShowPassword] = useState("true");
    const changeData = (e) => {
        setLoginData({ ...loginData, [e.target.name]: e.target.value });
    }
    const togglePassword = () => {
        setShowPassword(!showPassword);
    }
    const login = async (e) => {
        e.preventDefault();

        const validationErrors = {};

        if (!loginData.email) {
            validationErrors.email = 'Email Required';
        }
        if (!loginData.password) {
            validationErrors.password = 'Password Required';
        }

        if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors);
            if (validationErrors.email && validationErrors.password) {
                await Swal.fire({
                    title: 'Error!',
                    text: 'Credentials required',
                    icon: 'error',
                    confirmButtonText: 'Ok'
                });
            }
            else if (validationErrors.email && !(validationErrors.password)) {
                await Swal.fire({
                    title: 'Error!',
                    text: 'Email is required',
                    icon: 'error',
                    confirmButtonText: 'Ok'
                });
            }
            if (validationErrors.password && !(validationErrors.email)) {
                await Swal.fire({
                    title: 'Error!',
                    text: 'Password is required',
                    icon: 'error',
                    confirmButtonText: 'Cool'
                });
            }
        } else {

            setErrors({});
           
            UserApi.loginUser(loginData).then(response => {

                if (response.data.message === "password must be same") {
                    Swal.fire({
                        title: 'Error!',
                        text: 'Wrong password',
                        icon: 'error',
                        confirmButtonText: 'Ok'
                    });
                } else if (response.data.message === "Email not exist") {
                    Swal.fire({
                        title: 'Error!',
                        text: 'Wrong email',
                        icon: 'error',
                        confirmButtonText: 'Ok'
                    });
                } else {
                    if (response?.data.User_Information.role === "Admin") {
                        localStorage.setItem("userRole", response.data.User_Information.role);
                        localStorage.setItem("userId", response.data.User_Information.userId);
                        Swal.fire({
                            title: 'Login Success',
                            text: 'Correct credentials',
                            icon: 'success',
                            confirmButtonText: 'Ok'
                        });
                  
                        navigate('/UserDashBoard')

                    } else if (response?.data.User_Information.role === "student") {
                        localStorage.setItem("userRole", response.data.User_Information.role);
                        localStorage.setItem("userEmail", response.data.User_Information.email);
                        localStorage.setItem("userName", response.data.User_Information.userName);
                        localStorage.setItem("userId", response.data.User_Information.userId);
                        console.log(response.data.User_Information.userId)

                        Swal.fire({
                            title: 'Login Success',
                            text: 'Correct credentials',
                            icon: 'success',
                            confirmButtonText: 'Ok'
                        });
                        navigate('/UserDashBoard');

                    }

                }
            }).catch(error => {

                console.error(error);
            })

        }
    }

    return (
        <div className="login" >
            <DisableBackButton />
            <div className="loginData">
                <h1 className="ass">Assessment Portal</h1>
                <h2 className="know">- Come...Test your Knowledge Here!!!</h2>
                <form>
                    <div className="signin">
                        <h1 className="heading">SignIn Here!!</h1>
                        <label className="head" ><b>Email</b></label><br /><br />
                        <input className="data" type="email" name="email" value={loginData.email} onChange={changeData} /><br /><br />
                        <div className="password-container">
                            <label className="head"><b>Password</b></label><br /><br />
                            <input className="data" type={showPassword ? 'password' : 'text'} name="password" value={loginData.password} onChange={changeData} /><br />
                            <button className="show-password" type="button" onClick={togglePassword}>

                                {showPassword ? <FaEyeSlash /> : <FaEye />}
                            </button>
                        </div>
                        <button className="btn" type="submit" onClick={login}>Login</button><br /><br />
                        <a className="anchor" href="Registration">New user SignUp here!!</a>
                    </div>
                </form>
            </div>
        </div>
    );
}


export default Login;
