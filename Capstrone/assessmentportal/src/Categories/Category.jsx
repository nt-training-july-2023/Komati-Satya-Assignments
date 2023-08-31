import axios from "axios";
import React, { useState ,useEffect} from "react";

const Category=()=>{
    const [category,setCategory]=useState([
        
    ])
   useEffect(()=>{
    console.log("canged",category);
   },[category])
    const getCategories=(e)=>{
          axios.get('http://localhost:6002/cat')
          .then(response=>{
            console.log(response)
            setCategory(response.data.Category_Information)
            console.log(category)
          })
          console.log(category)
    }
     
    const deleteData=(id)=>{
        axios.delete(`http://localhost:6002/cat/${id}`).then(
            res=>console.log(res.data)
         )
        

    }

    return (
        <>
        <button type="button" onClick={getCategories}>Get All Categories</button>
        <table>
            <th>Category Name</th>
            <th>Category Description</th>
             <tbody>
                {category.map((item)=>{
                    return <tr><td>{item.categoryName}</td><td>{item.categoryDescription}</td><td><button type="submit" onClick={()=>deleteData(item.categoryId)}>delete</button></td></tr>
                })}
            </tbody> 
        </table>
        </>
    )
}
export default Category;