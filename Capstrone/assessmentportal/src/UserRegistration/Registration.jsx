import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import './RegistrationStyles.css'
const Registration=()=>{
    const[formData,setFormData]=useState({
        userName:"",
        email:"",
        password:"",
        confirmPassword:"",
        gender:"male",
         role:"student",
         dateOfBirth:"",
         phoneNumber:""
    })
    const navigate=useNavigate();
    const [errors, setErrors] = useState({});
    const changeData=(e)=>{
    setFormData({...formData,[e.target.name]:e.target.value});
    }
    console.log(formData);
    const submitData=(e)=>{
        const validationErrors={};
        if (!formData.email) {
          validationErrors.email = 'Email Required'
           
        }
        if (!formData.password) {
          validationErrors.password= 'password Required'
        }
        if (!formData.confirmPassword) {
            validationErrors.confirmPassword= 'confirm password Required'
          }
          if (!formData.userName) {
            validationErrors.userName= 'userName Required'
          }
          if (!formData.dateOfBirth) {
            validationErrors.dateOfBirth= 'Date of birth Required'
          }
          if (!formData.gender) {
            validationErrors.gender= 'Gender Required'
          }
          if (!formData.phoneNumber) {
            validationErrors.phoneNumber= 'phone number Required'
          }
          if(formData.email && !/^[A-Z0-9a-z+_-]+@nucleusteq[.]com$/.test(e.target.value)){
            validationErrors.email= 'Email does not match'
          }
          console.log(formData.phoneNumber.length)
          if(formData.phoneNumber){
          if(formData.phoneNumber.length!=10){
            validationErrors.phoneNumber= 'phone number must be 10 numbers'
          }
          if(formData.phoneNumber.length>=10){
            validationErrors.phoneNumber= 'phone number must be 10 numbers'
          }
        }
          var re = {
            capital: /(?=.*[A-Z])/,
            length: /(?=.{7,40}$)/,
            specialChar: /[ -\/:-@\[-\`{-~]/,
            digit: /(?=.*[0-9])/,
        };
        if(formData.password){
            if((!re.capital.test(e.target.value))||(!re.specialChar.test(e.target.value))||(!re.length.test(e.target.value))||(!re.digit.test(e.target.value))){
                validationErrors.password='Password must contain atleast a Capital, Special character, Number and minimum 8 characters ';
            }
        }
        if(formData.password && formData.confirmPassword){
            if(formData.password != formData.confirmPassword){
                validationErrors.confirmPassword='password must be same'
            }
        }
      if(Object.keys(validationErrors)){
          setErrors(validationErrors)
      }
        axios.post('http://localhost:6002/student',formData)
        .then(response=>{
            if(response.data.message=="successfully added data"){
                navigate('/Login');
            }
            if(response.data.message=="Email already exist"){
                window.alert("Email already exist");
            }
        })
    }
    return(
        <>
       <div className="userform">
        <form>
            <div className="signUp">
            <h1>Registration</h1>
            <div className="details">
            <label className="side" >UserName</label><br/><br/>
            <input  className="values" type="text" placeholder="enter a username" name="userName" value={formData.userName} onChange={changeData}/><br/><br/>
            <p style={{ color: "red", fontSize: "14px" }}>{errors.userName}</p>
            <label className="side">Email</label><br/><br/>
            <input className="values" type="email" placeholder="enter a email" name="email" value={formData.email} onChange={changeData}/><br/><br/>
            <p style={{ color: "red", fontSize: "14px" }}>{errors.email}</p>
            <label className="side">Password</label><br/><br/>
            <input className="values" type="password" placeholder="enter a password" name="password" value={formData.password} onChange={changeData}/><br/><br/>
            <p style={{ color: "red", fontSize: "14px" }}>{errors.password}</p>
            <label className="side">Confirm Password</label><br/><br/>
            <input className="values" type="password" placeholder="enter a confirm password" name="confirmPassword" value={formData.confirmPassword} onChange={changeData}/><br/><br/>
            <p style={{ color: "red", fontSize: "14px" }}>{errors.confirmPassword}</p>
            <label className="side">DateOfBirth</label><br/><br/>
            <input className="values" type="Date" placeholder="enter a date of birth" name="dateOfBirth" value={formData.dateOfBirth} onChange={changeData}/><br/><br/>
            <p style={{ color: "red", fontSize: "14px" }}>{errors.dateOfBirth}</p>
            <label className="side">Gender</label><br/><br/>
            <p style={{ color: "red", fontSize: "14px" }}>{errors.gender}</p>
            <div className="gen">
            <input className="gen2" type="radio" name="gender" value={formData.gender} onChange={changeData}/>
            <label>Male</label>
            <input className="gen2" type="radio" name="gender" value={formData.gender} onChange={changeData}/>
            <label>Female</label>
            <input className="gen2" type="radio" name="gender" value={formData.gender} onChange={changeData}/>
            <label>Other</label><br/><br/>
            </div>
            <label className="side">Phone number</label><br/><br/>
            <input className="values" type="text" name="phoneNumber" value={formData.phoneNumber} onChange={changeData}/><br/><br/>
            <p style={{ color: "red", fontSize: "14px" }}>{errors.phoneNumber}</p>
            <button type="button" onClick={submitData}>Register</button>
            </div>
            </div>
        </form>
        <a href="Login" className="anchor1">Already have an account Login here!!!</a>
       </div>
        </>
    )
}
export default Registration