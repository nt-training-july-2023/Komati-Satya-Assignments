
import axios from "axios";
import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
const UpdateCategory = () => {
  const { categoryId } = useParams();
  console.log(categoryId);
  const [categoryData, setCategoryData] = useState({
    categoryName: "",
    categoryDescription: "",
  });
 const navigate=useNavigate();
  useEffect(() => {
    axios.get(`http://localhost:6002/cat/${categoryId}`)
      .then((response) => {
        
        console.log(response);
        const userInformation = response.data.Category_Information;
        console.log(userInformation)
        const { categoryName, categoryDescription } = userInformation;
        setCategoryData({
          categoryName,
          categoryDescription,
        });
      })
      .catch((error) => {
        console.error("An error occurred:", error);
      });
  }, [categoryId]);
  

  const handleUpdateCategory = () => {
    axios.put(`http://localhost:6002/cat/${categoryId}`,categoryData)
    .then((response) => {
        if(response.data.message==="succcessfully update the data"){
            Swal.fire({
                title: 'Updating data',
                text: 'Successfully updated data',
                icon: 'success',
                confirmButtonText: 'Ok'
              });
             navigate('/Category')
        }
    })
  };
   const cancelUpdate=()=>{
    // Swal.fire({
    //     title: 'Cancel',
    //     text: 'Cancelling updated data',
    //     icon: 'error',
    //     confirmButtonText: 'Cancel',
       
    //   });
    Swal.fire({
        title: 'Do you want to cancel the changes?',
        showDenyButton: true,
        // showCancelButton: true,
        confirmButtonText: 'Yes',
        denyButtonText: 'No',
        customClass: {
          actions: 'my-actions',
          cancelButton: 'order-1 right-gap',
          confirmButton: 'order-2',
          denyButton: 'order-3',
        }
      }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire('Changes are not saved', '', 'info')
            navigate('/Category')
          
        } else if (result.isDenied) {
           
        }
      })
      
    
   }
  return (
    <div>
      <h1>Update Category</h1>
      <form>
        <div>
          <label>Category Name:</label>
          <input
            type="text"
            value={categoryData.categoryName}
            onChange={(e) =>
              setCategoryData({
                ...categoryData,
                categoryName: e.target.value,
              })
            }
          />
        </div>
        <div>
          <label>Category Description:</label>
          <textarea
            value={categoryData.categoryDescription}
            onChange={(e) =>
              setCategoryData({
                ...categoryData,
                categoryDescription: e.target.value,
              })
            }
          />
        </div>
        <button type="button" onClick={handleUpdateCategory}> Update Category</button>
        <button type="button" onClick={cancelUpdate}>Cancel</button>
      </form>
    </div>
  );
};

export default UpdateCategory;
