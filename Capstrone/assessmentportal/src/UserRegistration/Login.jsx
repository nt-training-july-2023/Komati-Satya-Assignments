import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import './LoginStyles.css';
const Login = () => {
    const [loginData, setLoginData] = useState({
        email: "",
        password: ""
    })
    const role = "";
    const [errors, setErrors] = useState({});
    const [disable, setDisable] = useState(true)
    const navigate = useNavigate();
    const changeData = (e) => {
       
        setLoginData({ ...loginData, [e.target.name]: e.target.value });

        console.log(errors);
    }
        const login = (e) => {
            const validationErrors={};
              if (!loginData.email) {
                validationErrors.email = 'Email Required'
                 
              }
              if (!loginData.password) {
                validationErrors.password= 'password Required'
              }
            if(Object.keys(validationErrors)){
                setErrors(validationErrors)
            }
              console.log(errors);
           
                axios.post('http://localhost:6002/login', loginData)
                    .then(response => {
                        console.log(response)
                        if (response.data.message == "password must be same") {
                            // window.alert("password must be same");
                            validationErrors.password='password must be same'
                        }
                        if (response.data.message == "Email not exist") {
                            // window.alert("email not exist");
                            validationErrors.email='Email does not exist'
                        }
                        if(Object.keys(validationErrors)){
                            setErrors(validationErrors)
                        }
                        if (response.data.message == "succcessfully retrieve the data") {
                            if (response?.data.User_Information.role == "Admin") {
                                navigate('/AdminDashBoard');
                            }
                            else if (response?.data.User_Information.role == "student") {
                                navigate('/UserDashBoard');
                            }
                        }
                       

                    })
            
            
        }
        return (
            <>
                <div className="loginData">
                    <form>
                        <div className="signin">
                            <h1 className="heading">SignIn</h1>
                            <label className="head"><b>Email</b></label><br/><br/>
                            <input className="data" type="email" name="email" value={loginData.email} onChange={changeData} /><br /><br />
                            <p style={{ color: "red", fontSize: "14px" }}>{errors.email}</p>
                            <label className="head"><b>Password</b></label><br/><br/>
                            <input className="data" type="password" name="password" value={loginData.password} onChange={changeData} /><br />
                            <p style={{ color: "red", fontSize: "14px" }}>{errors.password}</p>
                            <button className="btn" type="button" onClick={login}>Login</button>
                            <a className="anchor" href="Registration">New user!!</a>
                        </div>
                    </form>
                </div>
            </>
        )
    }

export default Login;