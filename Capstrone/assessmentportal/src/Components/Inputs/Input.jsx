import React from "react";
const Input = (props) => {
    return (
        <div>
            <input className={props.className}
                type={props.type}
                name={props.name}
                id={props.id}
                placeholder={props.placeholder}
                value={props.value}
                onChange={props.onChange}
                disabled={props.disabled}
                checked={props.checked}
                onFocus={props.onFocus}
                onBlur={props.onBlur}
            />
        </div>
    )
}
export default Input