import React, { useEffect, useState } from "react";


export const GetApi=()=>{
    const[data,setData]=useState([]);
    useEffect(()=>{
    fetch('https://jsonplaceholder.typicode.com/todos').then(
        response=>response.json()
        ).then(json=>setData(json))
    },[])

    return(
        <>
         {data.map(item => <li key={item.id}>{item.title}</li>)}
        </>
       
    )
}