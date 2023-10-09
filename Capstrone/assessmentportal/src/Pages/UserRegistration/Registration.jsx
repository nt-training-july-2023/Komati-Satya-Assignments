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
  const [showPassword, setShowPassword] = useState("false");
  const [showConfirmPassword, setShowConfirmPassword] = useState("false");
  const [formData, setFormData] = useState({
    userName: "",
    email: "",
    password: "",
    confirmPassword: "",
    gender: "",
    role: "student",
    dateOfBirth: "",
    phoneNumber: ""
  });
  const navigate = useNavigate();
  const [errors, setErrors] = useState({});
  const changeData = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };
  const changeDataForRadioButton = (e) => {
    setFormData({ ...formData, gender: e.target.value });
  };
  const togglePassword = () => {
    setShowPassword(!showPassword);
  }
  const toggleConfirmPassword = () => {
    setShowConfirmPassword(!showConfirmPassword);
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
    if (!formData.gender) {
      validationErrors.gender = 'Gender Required';
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
        SweetAlert.fieldsRequired("Password and Confirm Password must be same");
      }
      else if (formData.email && (!/^[a-z][a-zA-Z0-9.]*@nucleusteq\.com$/.test(formData.email))) {
        SweetAlert.fieldsRequired("Email must contain domain @nucleusteq");
      }
      else {
        UserApi.addUser(formData)
          .then((response) => {
            if (response.data.message === "User register successfully") {
              SweetAlert.success("User Registered successfully");
              navigate('/');
            }
          })
          .catch((error) => {
            if (error.response.data.message === "Email already exist") {
              SweetAlert.fieldsRequired("Email already exist");
            }
            if (error.response.data.StatusCode === "400") {
              SweetAlert.fieldsRequired(error.response.data.message);
            }
          });
      }
    }
    else {
      SweetAlert.fieldsRequired("All Credentials required");
    }
  }

  return (
    <div className="user">
        <div className="userData">
        <form>
        <div  className="signUp" >
          <H1Component>Registration</H1Component>
          <div className="details">
            <Input className="values" type="text" placeholder="Enter name" name="userName" value={formData.userName} onChange={changeData} /><br /><br />
            <Input className="values" type="email" placeholder="Enter email" name="email" value={formData.email} onChange={changeData} /><br /><br />
            <div className="password-container2">
              <Input className="values" type={showPassword ? 'password' : 'text'} name="password" placeholder="Enter password" value={formData.password} onChange={changeData} /><br />
              <ButtonComponent className="show-password2" type="button" onClick={togglePassword}>
                {showPassword ? <FaEyeSlash /> : <FaEye />}
              </ButtonComponent>
            </div><br />
            <div className="password-container2">
              <Input className="values" type={showConfirmPassword ? 'password' : 'text'} name="confirmPassword" placeholder="Enter confirm password" value={formData.confirmPassword} onChange={changeData} /><br />
              <ButtonComponent className="show-password2" type="button" onClick={toggleConfirmPassword}>
                {showConfirmPassword ? <FaEyeSlash /> : <FaEye />}
              </ButtonComponent>
            </div><br />
            <Input className="values" type="Date" placeholder="Enter date of birth" name="dateOfBirth" value={formData.dateOfBirth} onChange={changeData} /><br /><br />
            <Input className="values" type="number" name="phoneNumber" placeholder="Enter phone number" value={formData.phoneNumber} onChange={changeData} /><br /><br />
            <div className="gender">
              <Input className="gen1" type="radio" name="gender" value="male" onChange={changeDataForRadioButton} />
              <LabelComponent className="gender">Male </LabelComponent>
              <Input className="gen2" type="radio" name="gender" value="female" onChange={changeDataForRadioButton} />
              <LabelComponent className="gender">Female</LabelComponent>
              <Input className="gen2" type="radio" name="gender" value="other" onChange={changeDataForRadioButton} />
              <LabelComponent className="gender">Other</LabelComponent><br />
            </div>
            <ButtonComponent className="submit" type="button" onClick={submitData}>Register</ButtonComponent>
            <a href="/" className="anchor1">Already have an account? Login here!!!</a>
          </div>
          </div>
          </form>
        </div>
      
      </div>
   
  );
}
export default Registration;
