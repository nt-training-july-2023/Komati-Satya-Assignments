import React, { useState } from "react";
import Count from './Count.jsx';
import Display from './Display.jsx';

export const store=React.createContext();
const Sample=()=>{
  const [data,setData]=useState([
    {
        brandName:"Samsung"
    },
    {
        brandName:"Mi"
    },
    {
        brandName:"Oppo"
    }
  ]) 
    return(
        <>
        <store.Provider value={[data,setData]}>
            <Count/>
            <Display/> 
        </store.Provider>
        </>
    )
}
export default Sample;