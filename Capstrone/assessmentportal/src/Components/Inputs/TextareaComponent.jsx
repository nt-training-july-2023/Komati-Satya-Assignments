import React from "react";
const TextareaComponent=(props)=>{
    return(
        <div>
            <textarea className={props.className}
             type={props.type}
             name={props.name}
             id={props.id}
             placeholder={props.placeholder}
             value={props.value}
             onChange={props.onChange}
             />
        </div>
    )
}
export default TextareaComponent
