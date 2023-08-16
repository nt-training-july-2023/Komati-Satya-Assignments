import axios from "axios";
import React, { useEffect ,useState} from "react";

export const AxiosGet=()=>
{
   const[data,setData]=useState([]);
  useEffect(()=>{
    axios.get('https://jsonplaceholder.typicode.com/todos').then(
        response=>setData(response.data)
    )
  },[])

  return(
    <>
    {data.map(item => <li key={item.id}>{item.title}</li>)}
    </>
  )
  
    
}