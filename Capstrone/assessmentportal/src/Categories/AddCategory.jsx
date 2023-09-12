
import React, { useState ,useEffect} from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import Swal from "sweetalert2";
import './AddCategoryStyles.css';
import ErrorPage from "../ErrorPage";
const AddCategory = () => {
    const verifyRole = localStorage.getItem('userRole');
    const { categoryId } = useParams();
  console.log(categoryId);
    const [errors, setErrors] = useState({});
    const [categoryData, setCategoryData] = useState({
        categoryName: "",
        categoryDescription: ""
    });
    const navigate = useNavigate();
    const changeData = (e) => {
        setCategoryData({ ...categoryData, [e.target.name]: e.target.value });
    }
   
    useEffect(() => {
        if(categoryId){
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
        }
      }, [categoryId]);


    
    const handleSubmit=async(e)=>{
    if(categoryId) {


          
            e.preventDefault();
        
            const validationErrors = {};
        
            if (!categoryData.categoryName) {
                validationErrors.categoryName = 'category name Required';
            }
            if (!categoryData.categoryDescription) {
                validationErrors.categoryDescription = 'category description Required';
            }
        
            if (Object.keys(validationErrors).length > 0) {
                setErrors(validationErrors);
                if (validationErrors.categoryName && validationErrors.categoryDescription) {
                    await Swal.fire({
                        title: 'Error!',
                        text: 'data required',
                        icon: 'error',
                        confirmButtonText: 'Ok'
                    });
                }
                else if (validationErrors.categoryName && !(validationErrors.categoryDescription)) {
                    await Swal.fire({
                        title: 'Error!',
                        text: 'category name is required',
                        icon: 'error',
                        confirmButtonText: 'Ok'
                    });
                }
                if (validationErrors.categoryDescription && !(validationErrors.categoryName)) {
                    await Swal.fire({
                        title: 'Error!',
                        text: 'category description is required',
                        icon: 'error',
                        confirmButtonText: 'Cool'
                    });
                }
            } else {
        
                setErrors({});
            axios.put(`http://localhost:6002/cat/${categoryId}`, categoryData)
              .then((response) => {
                if (response.data.message === "succcessfully update the data") {
                  Swal.fire({
                    title: 'Updating data',
                    text: 'Successfully updated data',
                    icon: 'success',
                    confirmButtonText: 'Ok'
                  });
                  navigate('/Category')
                }
              })
            }
          }
        
        
    
else{

    
        e.preventDefault();

        const validationErrors = {};

        if (!categoryData.categoryName) {
            validationErrors.categoryName = 'category name Required';
        }
        if (!categoryData.categoryDescription) {
            validationErrors.categoryDescription = 'category description Required';
        }

        if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors);
            if (validationErrors.categoryName && validationErrors.categoryDescription) {
                await Swal.fire({
                    title: 'Error!',
                    text: 'data required',
                    icon: 'error',
                    confirmButtonText: 'Ok'
                });
            }
            else if (validationErrors.categoryName && !(validationErrors.categoryDescription)) {
                await Swal.fire({
                    title: 'Error!',
                    text: 'category name is required',
                    icon: 'error',
                    confirmButtonText: 'Ok'
                });
            }
            if (validationErrors.categoryDescription && !(validationErrors.categoryName)) {
                await Swal.fire({
                    title: 'Error!',
                    text: 'category description is required',
                    icon: 'error',
                    confirmButtonText: 'Cool'
                });
            }
        } else {

            setErrors({});
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
        else if (response.data.message === "Category already present") {
            await Swal.fire({
                title: 'Error!',
                text: 'Category already present',
                icon: 'error',
                confirmButtonText: 'Ok'
            });
        }
    }

}
    }
    const cancelAddCategory = () => {
        Swal.fire({
            title: 'Do you want to cancel the category?',
            showDenyButton: true,
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

    console.log(categoryData)
    return (
        <div className="login3">
            {verifyRole === 'Admin' ? <>
                <div className="loginData3">
                    <h1 className="heading3">
                    {categoryId ? "Update category" : "Add Category"}</h1>
                    <form>
                        <div className="signin3">
                            <label className="head3">Category Name</label><br></br><br></br>

                            <input className="data3" type="text" name="categoryName" value={categoryData.categoryName} placeholder="Enter category name" onChange={changeData}></input><br></br><br></br>
                            <label className="head3">Category description</label><br></br><br></br>
                            <textarea className="data3" type="text" name="categoryDescription" value={categoryData.categoryDescription} placeholder="Enter category description" onChange={changeData}></textarea>
                            <button className="btn4" type="button" onClick={handleSubmit} >
                                {categoryId ? "update category" : "Add Category"}
                                </button>
                            
                            <button className="btn5" type="button" onClick={cancelAddCategory}>Cancel</button>
                        </div>
                    </form>
                </div>
            </> : <ErrorPage />}
        </div>
    );

}

export default AddCategory;