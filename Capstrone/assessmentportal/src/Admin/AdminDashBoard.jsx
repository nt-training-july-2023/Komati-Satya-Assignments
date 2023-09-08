import React from "react";
import './AdminStyles.css';
import Swal from "sweetalert2";
import { useNavigate,useLocation} from "react-router-dom";
import ErrorPage from "../ErrorPage";
import { Link } from "react-router-dom";

const AdminDashBoard=()=>
{

  const navigate=useNavigate();
  const location=useLocation();
  const searchParams = new URLSearchParams(location.search);
    const dataString = searchParams.get('data');
    const data = JSON.parse(dataString);
    const userInformation = data && data.User_Information;
    const { userName, email,userId,role,phoneNumber,dateOfBirth,gender } = data?.User_Information || {};
    const verifyRole = localStorage.getItem('userRole');
  const logoutPage=()=>{
    
    Swal.fire({
      title: 'Do you want to logout page??',
      showDenyButton: true,
      // showCancelButton: true,
      confirmButtonText: 'Yes',
      denyButtonText: 'No',
      customClass: {
        actions: 'my-actions',
        cancelButton: 'order-1 right-gap',
        confirmButton: 'order-2',
        denyButton: 'order-3',
      }
    }).then((result) => {
      if (result.isConfirmed) {
        localStorage.removeItem('userRole');
          navigate('/')
        
      } else if (result.isDenied) {
         
      }
    })
    
  }

    return(
      
        <div className="admin">
          {verifyRole === 'Admin' ? <>
         <ul className="nav-bar">
            <li><a href="/">Home</a></li>
            <li><a href="/Category">Categories</a></li>
            <li><a href="/Quiz">Quiz</a></li>
            <li><a href="/Questions">Questions</a></li>
            {/* <li><a href="/Student">Student Details</a></li> */}
            <li><a href="/Result">Result</a></li>
            <li><a className="logout" href="#" onClick={logoutPage}>Logout</a></li>
            
          </ul>
          <div className="adminBackground">
            <h2>Welcome to AdminDashBoard </h2>
            <div>

                    {/* <pre>{JSON.stringify(userInformation, null, 2)}</pre> */}
                   <img className="image" src="data:image/png;base64,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"/>
                   <h1>Admin Information:</h1>
                </div>
                {/* <div className="information">
                    <strong>Name:</strong> {userName}<br/>
                    <strong>Email:</strong> {email}<br/>
                    <strong>gender:</strong> {gender}<br/>
                    <strong>phoneNumber:</strong> {phoneNumber}<br/>
                    <strong>userId:</strong> {userId}<br/>
                    <strong>Date Of Birth:</strong> {dateOfBirth}<br/>
                </div> */}
                <table className="information">
                  <tbody className="details">
                  <tr>
                  <td> <strong>Admin Name:</strong> {userName}<br/></td>
                  </tr>
                  
                   <tr>
                    <td><strong>UserId:</strong> {userId}<br/></td>
                    </tr>
                    <tr>
                   <td> <strong>Email:</strong> {email}<br/></td>
                   </tr>
                   <tr>
                   <td> <strong>Gender:</strong> {gender}<br/></td>
                   </tr>
                   <tr>
                    <td><strong>PhoneNumber:</strong> {phoneNumber}<br/></td>
                    </tr>
                    
                    <tr>
                    <td><strong>Date Of Birth:</strong> {dateOfBirth}<br/></td>
                    </tr>
                  </tbody>
                </table>

          </div></>: <ErrorPage/>}
        </div>
    )
}
export default AdminDashBoard