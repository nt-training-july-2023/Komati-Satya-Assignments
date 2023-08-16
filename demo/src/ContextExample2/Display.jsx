import React, { useContext, useState } from "react";
import { store } from "./Sample";

const Display=()=>{
     const[data,setData]=useContext(store);
     const [name,setName]=useState('');
     const submitHandler = e =>{
        e.preventDefault();
        setData([...data,{brandName:name}]);
     }
     return(
      <>
        {data.map(item=> <h3>{item.brandName}</h3>)  }
        <form className="abc"  onSubmit={submitHandler}>
            <input type="text" onChange={(e)=>setName(e.target.value)} placeholder="Enter a brand name"/>
            <input type="submit" value="Add"/>
        </form>

      </>
     );

}

export default Display;