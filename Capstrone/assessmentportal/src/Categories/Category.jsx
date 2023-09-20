
import axios from "axios";
import React, { useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import './CategoryStyles.css'
import ErrorPage from "../ErrorPage";
import CategoryApi from "../APIs/CategoryApi";
import DisableBackButton from "../APIs/disableBackButton";
function Category() {
  const verifyRole = localStorage.getItem('userRole');
  const [category, setCategory] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [searchText, setSearchText] = useState("");
  const [originalCategory, setOriginalCategory] = useState([]);
  const {userId} =useParams()
  console.log(userId)
  useEffect(() => {
    getCategories();
  }, []);
  const getCategories = async () => {
    
      // const response = await axios.get('http://localhost:6002/cat');

       CategoryApi.getAllCategories().then(
        response=>{
      setCategory(response.data.Category_Information || []);
      setOriginalCategory(response.data.Category_Information || []);
    } ).catch (error=> {
      console.error('An error occurred:', error);
    } ).finally (()=>{
      setIsLoading(false);
    })
  };
  const deleteData = async (id) => {
    Swal.fire({
      title: 'Do you want to delete category?',
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
            // const response = await axios.delete(`http://localhost:6002/cat/${id}`);
      CategoryApi.deleteCategory(id).then(response=>{
        console.log(response);
        if (response.data.message === "succcessfully delete the data") {
          Swal.fire({
            title: 'Deleting data',
            text: 'Successfully deleted data',
            icon: 'success',
            confirmButtonText: 'Ok'
          });
        }
        getCategories();
      }).catch (error=> {
        console.log(error);
      })

      } else if (result.isDenied) {

      }
  })
    //   // const response = await axios.delete(`http://localhost:6002/cat/${id}`);
    //   CategoryApi.deleteCategory(id).then(response=>{
    //   console.log(response);
    //   if (response.data.message === "succcessfully delete the data") {
    //     Swal.fire({
    //       title: 'Deleting data',
    //       text: 'Successfully deleted data',
    //       icon: 'success',
    //       confirmButtonText: 'Ok'
    //     });
    //   }
    //   getCategories();
    // }).catch (error=> {
    //   console.log(error);
    // })
  }
  const navigate = useNavigate();
  const addData = () => {
    navigate('/AddCategory');
  }
  const handleSearch = async () => {
    console.log(searchText)
    const filteredCategory = category.filter(item =>
      item.categoryName.toLowerCase().includes(searchText.toLowerCase())
    );
    setCategory(filteredCategory);
  };
  const clearSearch = () => {
    setCategory(originalCategory);
    setSearchText("");
  }
  const backTo = () => {
    {
      verifyRole === 'Admin' &&
      navigate('/UserDashBoard');
    }
    {
      verifyRole === 'student' &&
      navigate('/UserDashBoard');
    }
  }
  const handleViewTopic=(categoryName)=>{
    { localStorage.setItem('categoryName', categoryName)}
  }
  return (
    <div className="App">
      
    <div className="categoryData">
    <DisableBackButton/>
      {(verifyRole === 'Admin' || verifyRole === 'student') ?
        <>
          <h1 className="addHead">Category Details</h1>
          <button className="addButton" onClick={() => backTo()}>BackToDashBoard</button>
          {verifyRole === 'Admin' && <button className="addButton" onClick={() => addData()}>Add category</button>}
          <div className="searchContainer">
            <input
              className="search"
              type="text"
              placeholder="Search by Category Name"
              value={searchText}
              onChange={(e) => setSearchText(e.target.value)}
            />
            <button className="searchButton" onClick={handleSearch}>Search</button>
            <button className="searchButton" onClick={clearSearch}>Clear Search</button>

          </div>
          {isLoading ? (
            <p>Loading...</p>
          ) : (
            <div className="tableContainer">
                {category.length !== 0 ? (
              <table className="tableData">
                <thead className="headData">
                  <tr className="rowData">
                    <th>Category Name</th>
                    <th>Category Description</th>
                    {/* <th>Delete</th>
              <th>Update</th> */}
                  </tr>
                </thead>
                <tbody className="bodyData">
                  {category.map(item => (

                    <tr key={item.categoryId}>
                      <td>{item.categoryName}</td>
                      <td>{item.categoryDescription}</td>
                      {verifyRole === 'Admin' && <>
                        <td><button className="deleteData" type="button" onClick={() => deleteData(item.categoryId)}>Delete</button></td>
                        <td><Link to={`/UpdateCategory/${item.categoryId}`} className="updateData">Update</Link></td></>}
                        {verifyRole === 'Admin' || verifyRole === 'student' ? (
                  <td><Link to={`/Quiz/${item.categoryId}`} className="updateData" onClick={()=>handleViewTopic(item.categoryName)}>
                   
                    View Quiz Topics</Link></td>
) : (
  <></>
)}

                    </tr>
                  ))}
                </tbody>
              </table>
               ) : (
                <h1>No Category</h1>
              )}
            </div>
           )} 
        </> : <ErrorPage />}
    </div>
    
    </div>
  );
}

export default Category;

