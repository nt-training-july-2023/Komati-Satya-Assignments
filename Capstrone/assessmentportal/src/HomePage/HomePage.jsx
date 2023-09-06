import React from "react";
import { useNavigate } from "react-router-dom";
import './HomePage.css';


const HomePage=()=>{

  
   return(
       <div className="body">
          <h2 className="heading">Assessment PlatForm</h2>
          <ul>
            <li><a href="/">Home</a></li>
            <li><a href="/Login">SignIn</a></li>
            <li><a href="/Registration">SignUp</a></li>
            <li style={{float:"right"}}><a class="active" href="/About">About</a></li>
          </ul>

       </div>
 
       
    )
}
export default HomePage;