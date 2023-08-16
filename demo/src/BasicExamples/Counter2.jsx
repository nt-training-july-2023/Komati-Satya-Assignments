import React, { useState } from "react";


export default function Counter2(){
    const[count,setCount]=useState(0);

    function onClickHandler(){
        setCount(count+1);
    }
    return(
        <>
        <MyButton count={count} onClick={onClickHandler}/>
        <MyButton count={count} onClick={onClickHandler}/>

        </>
    )
}

function MyButton({count,onClick}){
    return(
        <>
        <button onClick={onClick}>clicked {count} times</button>
        </>
        
    )
}