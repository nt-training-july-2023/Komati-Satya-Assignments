import React from "react";

const LabelComponent = (props) => {
  return (
    <label htmlFor={props.htmlFor} className={props.className}>{props.children}</label>
  );
};
export default LabelComponent