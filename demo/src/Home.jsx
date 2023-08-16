import React from 'react';
import {useNavigate} from 'react-router-dom';

function Home(){
     const navigate = useNavigate();
    return (
        <>
        <h2>Home</h2>
       
       
     <button onClick={()=>{navigate("/login")}}>Login</button>
    <button onClick={()=>{navigate("/about")}}>About</button>  
    </>

    );
}
export default Home;