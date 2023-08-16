import React from "react";
import {userContext} from './Parent.jsx';
const child3=()=>{
    return(
  <>
    <userContext.Consumer>
        {value => <div>{value}</div>}

    </userContext.Consumer>
  </>
    );
}
export default child3;