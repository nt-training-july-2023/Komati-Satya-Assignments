import React, { useEffect, useState } from "react";
import axios from "axios";

const api="https://jsonplaceholder.typicode.com/todos";
const api1=`https://jsonplaceholder.typicode.com/todos/${1}`
export const AxiosPost=()=>{
    const[data,setData]=useState({});
    
    
    function PostAxios(){
        axios.post(api,{
            title:"hii"
        }).then(response=>setData(response.data));
        console.log(data);
    }
    function getAxios(){
        axios.get(api1).then(
            response=>setData(response.data));
    }
    useEffect(()=>{
       getAxios();
    },[]);
    return(
        <>
        <h2>{data.title}</h2>
        <button onClick={PostAxios}>click here</button>
        {/* { data.map(item=><li key={item.id}>{item.title}</li>)}  */}
        
        </>
    )
}