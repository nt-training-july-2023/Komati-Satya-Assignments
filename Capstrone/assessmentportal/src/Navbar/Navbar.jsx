
import React from "react";

import Swal from "sweetalert2";
import { useNavigate, useLocation } from "react-router-dom";
import ErrorPage from "../ErrorPage";
import { useEffect,useState } from "react";
import axios from "axios";
import UserApi from "../APIs/UserApi";
import DisableBackButton from "../APIs/disableBackButton";

const Navbar = () => {
  const location=useLocation();
  const navigate = useNavigate();
const verifyUserId=localStorage.getItem('userId')
  const verifyRole = localStorage.getItem('userRole');
  const logoutPage = () => {
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
        localStorage.removeItem('userEmail')
        localStorage.removeItem('userName')
        localStorage.removeItem('userId')
        localStorage.removeItem('categoryName')
        localStorage.removeItem('quizName')
        navigate('/')

      } else if (result.isDenied) {

      }
    })

  }
  

  return (
    
    <div className="admin">
      <DisableBackButton/>
      {(verifyRole === 'Admin' || verifyRole === 'student') ?
      <>
        <ul className="nav-bar">
          {/* <li><a href="/">Home</a></li> */}
          <li className="aa"><a className={location.pathname==='/UserDashBoard' ? 'active':''} href="/UserDashBoard">Profile</a></li>
          <li className="aa"><a className={location.pathname==='/Category' ? 'active':''} href="/Category">Categories</a></li>
          {verifyRole === 'Admin' && <>
          <li className="aa"><a className={location.pathname==='/Students' ? 'active':''} href="/Students">Students</a></li>
        </>}
          {verifyRole=='Admin' && <>
          <li className="aa"><a className={location.pathname==='/Result' ? 'active':''} href="/Result">Result</a></li>
          </>}
          {verifyRole=='student' && <>
          <li className="aa"><a className={location.pathname===`/Result/${verifyUserId}` ? 'active':''} href={`/Result/${verifyUserId}`}>Result</a></li>
          </>}
          <li><a className="logout" href="#" onClick={logoutPage}>Logout</a></li>
        </ul>
        </> : <ErrorPage />}
        </div>
        )
  }
  export default Navbar;