import React from "react";
const Input=(props)=>{
    return(
        <div>
            <input className={props.className}
             type={props.type}
             name={props.name}
             id={props.id}
             placeholder={props.placeholder}
             value={props.value}
             onChange={props.onChange}
             defaultValue={props.defaultValue}
           checked={props.checked}
             />
             
        </div>
    )
}
export default Input