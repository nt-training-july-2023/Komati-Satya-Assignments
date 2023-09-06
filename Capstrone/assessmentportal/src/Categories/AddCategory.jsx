
import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
const AddCategory=()=>{
    const[categoryData,setCategoryData]=useState({
        categoryName:"",
        categoryDescription:""
    });
    const navigate=useNavigate();
    const changeData = (e) => {
        setCategoryData({ ...categoryData, [e.target.name]: e.target.value });
    }
    const addCategoryData= async() =>{
        const response = await axios.post('http://localhost:6002/category', categoryData);
        console.log(response);

        if (response.data.message === "succcessfully added data") {
            await Swal.fire({
                title: 'Add category',
                text: 'Added category',
                icon: 'success',
                confirmButtonText: 'Ok'
            });
             navigate('/Category');
         }
         else if(response.data.message === "Category already present")
         {
            await Swal.fire({
                title: 'Error!',
                text: 'Category already present',
                icon: 'error',
                confirmButtonText: 'Ok'
            });
         }
    }
    console.log(categoryData)
    return(
        <div className="formData">
            <form>
           <label>Category Name</label><br></br><br></br>
          
           <input type="text" name="categoryName" value={categoryData.categoryName} placeholder="Enter category name" onChange={changeData}></input><br></br><br></br>
           <label>Category description</label><br></br><br></br>
           <textarea type="text" name="categoryDescription" value={categoryData.categoryDescription} placeholder="Enter category description" onChange={changeData}></textarea>
           <button type="button" onClick={addCategoryData} >Add</button>
           </form>
        </div>
    );

}

export default AddCategory;