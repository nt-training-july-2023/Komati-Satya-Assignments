import React,{useContext} from "react";
import { store} from "./Sample";


const Count=()=>{
    const [data,setData]=useContext(store);
    return(
        <>
        <div>
         
        <h3>count: {data.length}</h3>
        </div>
        </>
    )
}
export default Count;


