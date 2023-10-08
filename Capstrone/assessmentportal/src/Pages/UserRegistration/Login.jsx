import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import './LoginStyles.css';
import { FaEye, FaEyeSlash} from 'react-icons/fa';
import SweetAlert from "../../Components/SweetAlertComponents/SweetAlert";
import UserApi from "../../Service/UserApi";
import DisableBackButton from "../../Components/disableBackButton";
import Input from "../../Components/Inputs/Input";
import ButtonComponent from "../../Components/Inputs/ButtonComponent";
import H1Component from "../../Components/HeadingComponent/H1component";
import H2Component from "../../Components/HeadingComponent/H2component";


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
        else if (loginData.email && (!/^[a-z][a-zA-Z0-9.]*@nucleusteq\.com$/.test(loginData.email))) {
            SweetAlert.fieldsRequired("Email must contain domain @nucleusteq");
        }
        else if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors);
            if (validationErrors.email && validationErrors.password) {
                SweetAlert.fieldsRequired("credentials required")
            }
            else if (validationErrors.email && !(validationErrors.password)) {
                SweetAlert.fieldsRequired("email required")
            }
            if (validationErrors.password && !(validationErrors.email)) {
                SweetAlert.fieldsRequired("password required")
            }
        } else {
            setErrors({});
            UserApi.loginUser(loginData).then(response => {
                if (response?.data.data.role === "Admin") {
                    localStorage.setItem("userRole", response.data.data.role);
                    localStorage.setItem("userId", response.data.data.userId);
                    SweetAlert.success("login success")
                    navigate('/UserDashBoard')

                } else if (response?.data.data.role === "student") {
                    localStorage.setItem("userRole", response.data.data.role);
                    localStorage.setItem("userEmail", response.data.data.email);
                    localStorage.setItem("userName", response.data.data.userName);
                    localStorage.setItem("userId", response.data.data.userId);
                    SweetAlert.success("login success")
                    navigate('/UserDashBoard');
                }
            }).catch(error => {
                if (error.response.data.message === "wrong password") {
                    SweetAlert.fieldsRequired("Invalid password");

                } else if (error.response.data.message === "Email not exist") {
                    SweetAlert.fieldsRequired("Invalid Email");
                }
            })
        }
    }
    return (
        <div className="login" >
            <DisableBackButton />
            <div className="loginData">
                <H1Component className="ass">Assessment Portal</H1Component>
                <H2Component className="know">- Come...Test your Knowledge Here!!!</H2Component>
                <form>
                    <div className="signin">
                        <H1Component className="heading">SignIn Here!!</H1Component>
                        <Input
                            className="data-email"
                            type="email"
                            name="email"
                            value={loginData.email}
                            onChange={changeData}
                            placeholder="Enter email"
                        />
                        <br /><br />
                        <div className="password-container">
                            <Input className="data-password" type={showPassword ? 'password' : 'text'} name="password" value={loginData.password} onChange={changeData} placeholder="Enter password" /><br />
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
