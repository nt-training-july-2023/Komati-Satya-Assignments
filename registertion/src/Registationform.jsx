import React, { useState } from "react";
import './Registarationform.css';
const Registration = () => {
    const [data, setData] = useState({
        fullName: '',
        userName: '',
        email: '',
        phoneNumber: '',
        password: '',
        confirmPassword: '',
    })
    const { fullName, userName, email, phoneNumber, password, confirmPassword } = data;
    const changeHandler = e => {
        setData({ ...data, [e.target.name]: [e.target.value] })
    }
    const submitHandler = e => {
        e.preventDefault();
        console.log(data);
    }
    return (
        <div className="clr">

            <div className="abc">
                <form>
                    <div className="def">
                        <div className="fir">
                            <h2><u className="un">Re</u>gistration</h2>
                            <label><b>Full Name</b></label><br />
                            <input className="in" type="text" name="fullName" value={fullName} onChange={changeHandler} placeholder="Enter your name" /><br /><br />
                            <label><b>Email</b></label><br />
                            <input className="in" type="email" name="email" value={email} onChange={changeHandler} placeholder="Enter your email" /><br /><br />
                            <label><b>Password</b></label><br />
                            <input className="in" type="password" name="password" value={password} onChange={changeHandler} placeholder="Enter password" /><br /><br />
                        </div>
                        <div className="sec">
                            <label><b>User Name</b></label><br />
                            <input className="in" type="text" name="userName" value={userName} onChange={changeHandler} placeholder="Enter user name" /><br /><br />
                            <label><b>Phone Number</b></label><br />
                            <input className="in" type="text" name="phoneNumber" value={phoneNumber} onChange={changeHandler} placeholder="Enter phoneNumber" /><br /><br />
                            <label><b>Confirm Password</b></label><br />
                            <input className="in" type="password" placeholder="Confirm password" name="confirmPassword" value={confirmPassword} onChange={changeHandler} /><br /><br />
                        </div>
                        <div class="gen">
                            <label><b>Gender</b></label><br /><br/>
                            <input type="radio" name="gender" value="male"/>
                            <label>Male</label>
                            <input class="gen1" type="radio" name="gender" value="female"/>
                            <label>Female</label>
                            <input class="gen1" type="radio" name="gender" value="no"/>
                            <label>Prefer not to say</label>
                        </div>
                            <input className="btn" type="submit"  onSubmit={submitHandler}/>
                    </div>
                </form>
            </div>
        </div>
    )
}
export default Registration;