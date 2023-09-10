
import axios from "axios";
import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import './UpdateCategoryStyle.css';
import ErrorPage from "../ErrorPage";
const UpdateCategory = () => {
  const verifyRole = localStorage.getItem('userRole');
  const [errors, setErrors] = useState({});
  const { categoryId } = useParams();
  console.log(categoryId);
  const [categoryData, setCategoryData] = useState({
    categoryName: "",
    categoryDescription: "",
  });
  const navigate = useNavigate();
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


  const handleUpdateCategory = async(e) => {
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
  const cancelUpdate = () => {
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

    <div className="login2">
      {verifyRole === 'Admin' ? <>
        <div className="loginData2">
          <h1 className="heading2">Update Category</h1>
          <form>
            <div className="signin2">
              <div>
                <label className="head2">Category Name:</label>
                <input
                  className="data2"
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
                <label className="head2">Category Description:</label>
                <textarea
                  className="data2"
                  value={categoryData.categoryDescription}
                  onChange={(e) =>
                    setCategoryData({
                      ...categoryData,
                      categoryDescription: e.target.value,
                    })
                  }
                />
              </div>
              <button className="btn2" type="button" onClick={handleUpdateCategory}> Update Category</button>
              <button className="btn3" type="button" onClick={cancelUpdate}>Cancel</button>
            </div>
          </form>
        </div>
      </> : <ErrorPage />}
    </div>
  );
};

export default UpdateCategory;
