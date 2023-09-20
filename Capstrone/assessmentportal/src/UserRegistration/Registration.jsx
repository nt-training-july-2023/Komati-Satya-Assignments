
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Swal from "sweetalert2";
import './RegistrationStyles.css'
import { FaEye, FaEyeSlash } from 'react-icons/fa';
import UserApi from "../APIs/UserApi";
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
    // } else if (!/^[A-Z0-9a-z+_-]+@nucleusteq[.]com$/.test(formData.email)) {
    //   validationErrors.email = 'Email must contain @nucleusteq.com';
    // }

    if (!formData.password) {
      validationErrors.password = 'Password Required';
      // } else {
      //   const re = {
      //     capital: /(?=.*[A-Z])/,
      //     length: /(?=.{8,40}$)/,
      //     specialChar: /[ -\/:-@\[-\`{-~]/,
      //     digit: /(?=.*[0-9])/
      //   };

      //   if (
      //     !re.capital.test(formData.password) ||
      //     !re.specialChar.test(formData.password) ||
      //     !re.length.test(formData.password) ||
      //     !re.digit.test(formData.password)
      //   ) {
      //     validationErrors.password =
      //       'Password must contain at least a Capital, Special character, Number, and minimum 8 characters';
      //   }
    }

    if (!formData.confirmPassword) {
      validationErrors.confirmPassword = 'Confirm Password Required';
    }
    // } else if (formData.password !== formData.confirmPassword) {
    //   validationErrors.confirmPassword = 'Passwords do not match';
    // }

    if (!formData.userName) {
      validationErrors.userName = 'Username Required';
    }

    if (!formData.dateOfBirth) {
      validationErrors.dateOfBirth = 'Date of Birth Required';
    }

    if (!formData.phoneNumber) {
      validationErrors.phoneNumber = 'Phone Number Required';
    }
    // } else if (formData.phoneNumber.length > 10) {
    //   validationErrors.phoneNumber = 'Phone Number must be 10 digits';
    // } else if (formData.phoneNumber.length < 10) {
    //   validationErrors.phoneNumber = 'Phone Number must be 10 digits';
    // } 

    setErrors(validationErrors);

    return Object.keys(validationErrors).length === 0;
  };
  const [value, setValue] = useState(true);
  const [value2, setValue2] = useState(true);
  const re = {
    capital: /(?=.*[A-Z])/,
    length: /(?=.{8,40}$)/,
    specialChar: /[ -\/:-@\[-\`{-~]/,
    digit: /(?=.*[0-9])/
  };
  const submitData = async () => {

    if (validateForm()) {
      if (formData.phoneNumber.length < 10) {
        Swal.fire({
          title: 'Error!',
          text: 'phone number must be 10 digits',
          icon: 'error',
          confirmButtonText: 'Ok'
        });
      }
      else if (formData.phoneNumber.length > 10) {
        Swal.fire({
          title: 'Error!',
          text: 'phone number must be 10 digits',
          icon: 'error',
          confirmButtonText: 'Ok'
        });
      }

      else if (formData.password && !re.capital.test(formData.password) ||
        !re.specialChar.test(formData.password) ||
        !re.length.test(formData.password) ||
        !re.digit.test(formData.password)) {
        // const re = {
        //   capital: /(?=.*[A-Z])/,
        //   length: /(?=.{8,40}$)/,
        //   specialChar: /[ -\/:-@\[-\`{-~]/,
        //   digit: /(?=.*[0-9])/
        // };

        // if (
        //   !re.capital.test(formData.password) ||
        //   !re.specialChar.test(formData.password) ||
        //   !re.length.test(formData.password) ||
        //   !re.digit.test(formData.password)
        // ) {
        Swal.fire({
          title: 'Error!',
          text: 'Password must contain at least a Capital, Special character, Number, and minimum 8 characters',
          icon: 'error',
          confirmButtonText: 'Ok'
        });

      }
      else if (formData.password !== formData.confirmPassword) {
        Swal.fire({
          title: 'Error!',
          text: 'password must be same',
          icon: 'error',
          confirmButtonText: 'Ok'
        });
      }
      else if (formData.email && (!/^[A-Z0-9a-z+_-]+@nucleusteq[.]com$/.test(formData.email))) {
        Swal.fire({
          title: 'Error!',
          text: 'Email must contain domain @nucleusteq.com',
          icon: 'error',
          confirmButtonText: 'Ok'
        });

      }

      else {
        // axios
        //   .post('http://localhost:6002/student', formData)
        UserApi.addUser(formData)
          .then((response) => {
            console.log(response)
            if (response.data.message === "successfully added data") {
              Swal.fire({
                title: 'Registration',
                text: 'User Registred Successfully',
                icon: 'success',
                confirmButtonText: 'Ok'
              });
              navigate('/');
            }
            if (response.data.message === "Email already exist") {
              Swal.fire({
                title: 'Error!',
                text: 'Email already exists',
                icon: 'error',
                confirmButtonText: 'Ok'
              });
            }
          })
          .catch((error) => {
            console.error('Axios Error:', error);
          });
      }
    }


    else {
      Swal.fire({
        title: 'Error!',
        text: 'All credentials are required',
        icon: 'error',
        confirmButtonText: 'Ok'
      });
    }
  }

  return (
    <div className="userform">
      <form>
        <div className="signUp">
          <h1>Registration</h1>
          <div className="details">
            <label className="side">Name</label><br /><br />
            <input className="values" type="text" placeholder="enter a name" name="userName" value={formData.userName} onChange={changeData} /><br /><br />
            {/* <p style={{ color: "red", fontSize: "15px" }}>{errors.userName}</p> */}
            <label className="side">Email</label><br /><br />
            <input className="values" type="email" placeholder="enter a email" name="email" value={formData.email} onChange={changeData} /><br /><br />

            {/* <label className="side">Password</label><br /><br />
            <input className="values" type="password" placeholder="enter a password" name="password" value={formData.password} onChange={changeData} /><br /><br /> */}
            <div className="password-container2">
              <label className="side">Password</label><br /><br />
              <input className="values" type={showPassword ? 'password' : 'text'} name="password" placeholder="enter a password" value={formData.password} onChange={changeData} /><br />
              <button className="show-password2" type="button" onClick={togglePassword}>

                {showPassword ? <FaEyeSlash /> : <FaEye />}
              </button>
            </div><br/>
            {/* <label className="side">Confirm Password</label><br /><br />
            <input className="values" type="password" placeholder="enter a confirm password" name="confirmPassword" value={formData.confirmPassword} onChange={changeData} /><br /><br /> */}
            <div className="password-container2">
              <label className="side">Confirm Password</label><br /><br />
              <input className="values" type={showPassword ? 'password' : 'text'} name="confirmPassword" placeholder="enter confirm password" value={formData.confirmPassword} onChange={changeData} /><br />
              <button className="show-password2" type="button" onClick={togglePassword}>

                {showPassword ? <FaEyeSlash /> : <FaEye />}
              </button>
            </div><br/>
            <label className="side">DateOfBirth</label><br /><br />
            <input className="values" type="Date" placeholder="enter a date of birth" name="dateOfBirth" value={formData.dateOfBirth} onChange={changeData} /><br /><br />
            <label className="side">Phone number</label><br /><br />
            <input className="values" type="text" name="phoneNumber" placeholder="enter a phone number" value={formData.phoneNumber} onChange={changeData} /><br /><br />
            <label className="side">Gender</label><br /><br />

            <div className="gen">
              <input className="gen1" type="radio" name="gender" value={formData.gender} onChange={changeData} />
              <label className="gender">Male</label>
              <input className="gen2" type="radio" name="gender" value={formData.gender} onChange={changeData} />
              <label className="gender">Female</label>
              <input className="gen2" type="radio" name="gender" value={formData.gender} onChange={changeData} />
              <label className="gender">Other</label><br />
            </div>
            <button className="submit" type="button" onClick={submitData}>Register</button>
            <a href="/" className="anchor1">Already have an account? Login here!!!</a>
          </div>
        </div>
      </form>
    </div>
  );
}

export default Registration;
