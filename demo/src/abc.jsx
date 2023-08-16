import React from "react";
import {Link} from 'react-router-dom';
function Abc(){
    return(
        <>
 <Link to="/">Home</Link>
       <Link to="/login">Login</Link>
       <Link to="/about">About</Link> 
       </>
    );
}
export default Abc;