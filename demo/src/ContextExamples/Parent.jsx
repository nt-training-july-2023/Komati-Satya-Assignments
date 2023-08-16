import React from "react";
import Child3 from "./child3";
export const userContext=React.createContext();
function parent(){
return(
    <div>
        <userContext.Provider value={"Satya"}>
        <Child3/>
        </userContext.Provider>
    </div>
);
}

export default parent;