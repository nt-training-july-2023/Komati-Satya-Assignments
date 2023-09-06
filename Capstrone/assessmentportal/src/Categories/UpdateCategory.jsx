import React from "react";
import { useParams } from "react-router-dom";
const UpdateCategory=(id)=>{
    const { categoryId1 } = useParams();
    const { categoryId }=id;
    console.log(categoryId);
    return(
        <>
        <h1>hello</h1>
        {categoryId}
        </>
    )
}
export default UpdateCategory;