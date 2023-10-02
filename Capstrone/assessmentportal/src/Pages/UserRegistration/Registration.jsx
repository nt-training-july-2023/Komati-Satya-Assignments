import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import './RegistrationStyles.css'
import { FaEye, FaEyeSlash } from 'react-icons/fa';
import ButtonComponent from "../../Components/Inputs/ButtonComponent";
import Input from "../../Components/Inputs/Input";
import UserApi from "../../Service/UserApi";
import SweetAlert from "../../Components/SweetAlertComponents/SweetAlert";
import LabelComponent from "../../Components/LabelComponent/LabelComponent";
import H1Component from "../../Components/HeadingComponent/H1component";

const Registration = () => {
  const [showPassword, setShowPassword] = useState("true");
  const [formData, setFormData] = useState({
    userName: "",
    email: "",
    password: "",
    confirmPassword: "",
    gender: "male",
    role: "student",
    dateOfBirth: "",
    phoneNumber: ""
  });
  const navigate = useNavigate();
  const [errors, setErrors] = useState({});

  const changeData = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };
  const togglePassword = () => {
    setShowPassword(!showPassword);
  }
  const validateForm = () => {
    const validationErrors = {};

    if (!formData.email) {
      validationErrors.email = 'Email Required';
    }
    if (!formData.password) {
      validationErrors.password = 'Password Required';
    }

    if (!formData.confirmPassword) {
      validationErrors.confirmPassword = 'Confirm Password Required';
    }
    if (!formData.userName) {
      validationErrors.userName = 'Username Required';
    }

    if (!formData.dateOfBirth) {
      validationErrors.dateOfBirth = 'Date of Birth Required';
    }

    if (!formData.phoneNumber) {
      validationErrors.phoneNumber = 'Phone Number Required';
    }
    setErrors(validationErrors);

    return Object.keys(validationErrors).length === 0;
  };
  const re = {
    capital: /(?=.*[A-Z])/,
    length: /(?=.{8,40}$)/,
    specialChar: /[ -\/:-@\[-\`{-~]/,
    digit: /(?=.*[0-9])/
  };
  const submitData = async () => {

    if (validateForm()) {
      if (formData.phoneNumber.length < 10) {
        SweetAlert.fieldsRequired("Phone number must be 10 digits");
      }
      else if (formData.phoneNumber.length > 10) {
        SweetAlert.fieldsRequired("Phone number must be 10 digits");
      }

      else if (formData.password && !re.capital.test(formData.password) ||
        !re.specialChar.test(formData.password) ||
        !re.length.test(formData.password) ||
        !re.digit.test(formData.password)) {
        SweetAlert.fieldsRequired("Password must contain at least a Capital, Special character, Number, and minimum 8 characters");

      }
      else if (formData.password !== formData.confirmPassword) {
        SweetAlert.fieldsRequired("Password must be same");
      }
      else if (formData.email && (!/^[a-z][a-zA-Z0-9.]*@nucleusteq\.com$/.test(formData.email))) {
        SweetAlert.fieldsRequired("Email must contain domain @nucleusteq");

      }
      else {
        UserApi.addUser(formData)
          .then((response) => {
            
            if (response.data.message === "User register successfully") {
             SweetAlert.success("User Register successfully");
              navigate('/');
            }
           
          })
          .catch((error) => {
            if (error.response.data.message === "Email already exist") {
              SweetAlert.fieldsRequired("Email already exist");
            }
          });
      }
    }
    else {
      SweetAlert.fieldsRequired("All Credentials required");
    }
  }

  return (
    <div className="userform">
      <form>
        <div className="signUp">
          <H1Component>Registration</H1Component>
          <div className="details">
            <LabelComponent className="side">Name</LabelComponent><br /><br />
            <Input className="values" type="text" placeholder="enter a name" name="userName" value={formData.userName} onChange={changeData} /><br /><br />
            <LabelComponent className="side">Email</LabelComponent><br /><br />
            <Input className="values" type="email" placeholder="enter a email" name="email" value={formData.email} onChange={changeData} /><br /><br />
            <div className="password-container2">
              <LabelComponent className="side">Password</LabelComponent><br /><br />
              <Input className="values" type={showPassword ? 'password' : 'text'} name="password" placeholder="enter a password" value={formData.password} onChange={changeData} /><br />
              <ButtonComponent className="show-password2" type="button" onClick={togglePassword}>

                {showPassword ? <FaEyeSlash /> : <FaEye />}
              </ButtonComponent>
            </div><br />
           
            <div className="password-container2">
              <LabelComponent className="side">Confirm Password</LabelComponent><br /><br />
              <Input className="values" type={showPassword ? 'password' : 'text'} name="confirmPassword" placeholder="enter confirm password" value={formData.confirmPassword} onChange={changeData} /><br />
              <ButtonComponent className="show-password2" type="button" onClick={togglePassword}>
                {showPassword ? <FaEyeSlash /> : <FaEye />}
              </ButtonComponent>
            </div><br />
            <LabelComponent className="side">DateOfBirth</LabelComponent><br /><br />
            <Input className="values" type="Date" placeholder="enter a date of birth" name="dateOfBirth" value={formData.dateOfBirth} onChange={changeData} /><br /><br />
            <LabelComponent className="side">Phone number</LabelComponent><br /><br />
            <Input className="values" type="text" name="phoneNumber" placeholder="enter a phone number" value={formData.phoneNumber} onChange={changeData} /><br /><br />
            <LabelComponent className="side">Gender</LabelComponent><br /><br />

            <div className="gen">
              <Input className="gen1" type="radio" name="gender" value={formData.gender} onChange={changeData} />
              <LabelComponent className="gender">Male</LabelComponent>
              <Input className="gen2" type="radio" name="gender" value={formData.gender} onChange={changeData} />
              <LabelComponent className="gender">Female</LabelComponent>
              <Input className="gen2" type="radio" name="gender" value={formData.gender} onChange={changeData} />
              <LabelComponent className="gender">Other</LabelComponent><br />
            </div>
            <ButtonComponent className="submit" type="button" onClick={submitData}>Register</ButtonComponent>
            <a href="/" className="anchor1">Already have an account? Login here!!!</a>
          </div>
        </div>
      </form>
    </div>
  );
}

export default Registration;
