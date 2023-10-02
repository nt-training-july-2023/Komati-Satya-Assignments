import React from "react";

 const LabelComponent = (props) => {
  return (
      <label className={props.className}>{props.children}</label>
  );
};
export default LabelComponent