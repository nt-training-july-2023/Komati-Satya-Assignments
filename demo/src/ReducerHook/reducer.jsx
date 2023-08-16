
import React,{useReducer } from "react";

const initialState={count:0};

function abcD(state,action){
    
    switch(action.type){
        case 'increment':
            return {count:state.count+1};
        case 'decrement':
            return {count:state.count-1};
        default:
            throw new Error();
    }

}

function Red(){
    const[state,dispatch]=useReducer(abcD,initialState);
    return(
       <center>
           count:{state.count}
           <button onClick={()=>dispatch({type:'increment'})}>+</button>
           <button onClick={()=>dispatch({type:'decrement'})}>-</button>      
      </center>

    );
}

export default Red;