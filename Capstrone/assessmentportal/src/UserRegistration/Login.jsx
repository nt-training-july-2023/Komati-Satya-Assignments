
import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import './LoginStyles.css';
import Swal from "sweetalert2";
import { Link } from "react-router-dom";

const Login = () => {
    const [loginData, setLoginData] = useState({
        email: "",
        password: ""
    });
    const [errors, setErrors] = useState({});
    const navigate = useNavigate();

    const changeData = (e) => {
        setLoginData({ ...loginData, [e.target.name]: e.target.value });
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
            if(validationErrors.email && validationErrors.password){
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

            try {
                const response = await axios.post('http://localhost:6002/login', loginData);
                console.log(response);

                if (response.data.message === "password must be same") {
                    await Swal.fire({
                        title: 'Error!',
                        text: 'Wrong password',
                        icon: 'error',
                        confirmButtonText: 'Cool'
                    });
                } else if (response.data.message === "Email not exist") {
                    await Swal.fire({
                        title: 'Error!',
                        text: 'Wrong email',
                        icon: 'error',
                        confirmButtonText: 'Cool'
                    });
                } else  {
                    if (response?.data.User_Information.role === "Admin") {
                        await Swal.fire({
                            title: 'Login Success',
                            text: 'Correct credentials',
                            icon: 'success',
                            confirmButtonText: 'Ok'
                        });
                        // navigate('/AdminDashBoard');
                        navigate(`/AdminDashBoard?data=${JSON.stringify(response.data)}`);
                        
                    } else if (response?.data.User_Information.role === "student") {
                        await Swal.fire({
                            title: 'Login Success',
                            text: 'Correct credentials',
                            icon: 'success',
                            confirmButtonText: 'Ok'
                        });
                        // navigate('/UserDashBoard');
                        navigate(`/UserDashBoard?data=${JSON.stringify(response.data)}`);
                    }
                }
            } catch (error) {
                
                console.error(error);
            }
        }
    }

    return (
        <div className="login" >
           
        <div className="loginData">
        
            <form>
                <div className="signin">
                    <h1 className="heading">SignIn Here!!</h1>
                    <label className="head" ><b>Email</b></label><br /><br />
                    <input className="data" type="email" name="email" value={loginData.email} onChange={changeData} /><br /><br />
                    <label className="head"><b>Password</b></label><br /><br />
                    <input className="data" type="password" name="password" value={loginData.password} onChange={changeData} /><br />
                    <button className="btn" type="submit" onClick={login}>Login</button><br/><br/>
                    <a className="anchor" href="Registration">New user SignUp here!!</a>
                </div>
            </form>
        </div>
        </div>
    );
}

export default Login;
