import React from "react";
const H1Component=(props)=>{
    return(
        <div>
            <h1 className={props.className}> {props.children}</h1>
        </div>
    )
}
export default H1Component