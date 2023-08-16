import React,{useState} from "react";



export function Counter(){
    const[count,setCount]=useState(0);
    function onClickHandler(){
        setCount(count+1);
    }
    return(
        <>
        <button onClick={onClickHandler}>Click here:{count}</button>
        </>
    )
}