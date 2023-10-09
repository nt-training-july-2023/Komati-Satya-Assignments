import React from "react";
const H2Component = (props) => {
    return (
        <div>
            <h2 className={props.className}> {props.children}</h2>
        </div>
    )
}
export default H2Component