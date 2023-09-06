import React from "react";
import './AdminStyles.css';
import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";


const AdminDashBoard=()=>
{

  const navigate=useNavigate();
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
         
          navigate('/')
        
      } else if (result.isDenied) {
         
      }
    })
    
  }
  

    return(
        <>
       
         <ul>
            <li><a href="/">Home</a></li>
            <li><a href="/Category">Categories</a></li>
            <li><a href="#" onClick={logoutPage}>Logout</a></li>
          </ul>
          {/* <button className="loginButton" onClick={logoutPage}>Logout</button> */}
          <div className="adminBackground">
            <h2>welcome to AdminDashBoard</h2>
          </div>
        </>
    )
}
export default AdminDashBoard