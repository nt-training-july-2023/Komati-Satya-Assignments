import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import './LoginStyles.css';
import Swal from "sweetalert2";
import { FaEye, FaEyeSlash } from 'react-icons/fa';
import UserApi from "../APIs/UserApi";
import DisableBackButton from "../APIs/disableBackButton";
import Input from "../Inputs/Input";
import ButtonComponent from "../Inputs/ButtonComponent";


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
                    confirmButtonText: 'Ok'
                });
            }
        } else {

            setErrors({});
           
            UserApi.loginUser(loginData).then(response => {
               console.log(response)
                // if (response.data === "password must be same") {
                //     Swal.fire({
                //         title: 'Error!',
                //         text: 'Wrong password',
                //         icon: 'error',
                //         confirmButtonText: 'Ok'
                //     });
                
                // } else if (response.data === "Email not exist") {
                //     Swal.fire({
                //         title: 'Error!',
                //         text: 'Wrong email',
                //         icon: 'error',
                //         confirmButtonText: 'Ok'
                //     });
                // } 
                // else {
                    if (response?.data.role === "Admin") {
                        localStorage.setItem("userRole", response.data.role);
                        localStorage.setItem("userId", response.data.userId);
                        Swal.fire({
                            title: 'Login Success',
                            text: 'Correct credentials',
                            icon: 'success',
                            confirmButtonText: 'Ok'
                        });
                        navigate('/UserDashBoard')

                    } else if (response?.data.role === "student") {
                        localStorage.setItem("userRole", response.data.role);
                        localStorage.setItem("userEmail", response.data.email);
                        localStorage.setItem("userName", response.data.userName);
                        localStorage.setItem("userId", response.data.userId);
                        Swal.fire({
                            title: 'Login Success',
                            text: 'Correct credentials',
                            icon: 'success',
                            confirmButtonText: 'Ok'
                        });
                        navigate('/UserDashBoard');
                    
                }
            }).catch(error => {

                console.log(error);
                if (error.response.data.errorMessage === "password must be same") {
                    Swal.fire({
                        title: 'Error!',
                        text: 'Wrong password',
                        icon: 'error',
                        confirmButtonText: 'Ok'
                    });
                
                } else if (error.response.data.errorMessage === "Email not exist") {
                    Swal.fire({
                        title: 'Error!',
                        text: 'Wrong email',
                        icon: 'error',
                        confirmButtonText: 'Ok'
                    });
                } 
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
    
                        <Input
                           className="data"
                           type="email" 
                           name="email" 
                           value={loginData.email}
                            onChange={changeData} 
                        />
                        <br/><br/>
                        <div className="password-container">
                            <label className="head"><b>Password</b></label><br /><br />
                            <input className="data" type={showPassword ? 'password' : 'text'} name="password" value={loginData.password} onChange={changeData} /><br />
                            <ButtonComponent className="show-password" type="button" onClick={togglePassword}>

                                {showPassword ? <FaEyeSlash /> : <FaEye />}
                            </ButtonComponent>
                        </div>
                        <ButtonComponent className="btn" type="submit" onClick={login}>Login</ButtonComponent><br /><br />
                        <a className="anchor" href="Registration">New user SignUp here!!</a>
                    </div>
                </form>
            </div>
        </div>
    );
}


export default Login;
