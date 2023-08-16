import React from "react";
// import { useNavigate } from "react-router-dom";


function fun(){
   // alert("hello");
   console.log("hello");
}

function Not(){
    
    return(
       <>
       <button onClick={fun}>Hello</button>
       </>
    );
}

export default Not;