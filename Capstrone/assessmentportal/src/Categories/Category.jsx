
import axios from "axios";
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import './CategoryStyles.css'
function Category() {
  const [category, setCategory] = useState([]);
  const [isLoading, setIsLoading] = useState(true); 
  const [searchText,setSearchText]=useState("");
  const [originalCategory,setOriginalCategory]=useState([]);
  useEffect(() => {
    getCategories();
  }, []);
  const getCategories = async () => {
    try {
      const response = await axios.get('http://localhost:6002/cat');
      setCategory(response.data.Category_Information || []); 
      setOriginalCategory(response.data.Category_Information || []);
    } catch (error) {
      console.error('An error occurred:', error);
    } finally {
      setIsLoading(false);
    }
  };
  const deleteData = async (id) => {
    try{
    const response=await axios.delete(`http://localhost:6002/cat/${id}`);
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
    }
    catch(error){
      console.log(error);
    }
  };
  const navigate=useNavigate();
   const addData=()=>{
      navigate('/AddCategory');
   }
   const handleSearch = async() => {
   
    const filteredCategory = category.filter(item =>
      item.categoryName.toLowerCase().includes(searchText.toLowerCase())
    );
    setCategory(filteredCategory);
  };
  const clearSearch=()=>{
    setCategory(originalCategory);
    setSearchText("");
  }
  const backTo=()=>{
    navigate('/AdminDashBoard');
  }
  return (
    <div className="categoryData">
      <h1 className="addHead">Category Details</h1>
      <button className="addButton" onClick={()=>backTo()}>BackToDashBoard</button>
      <button className="addButton" onClick={()=>addData()}>Add category</button>
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
                <td><button className="deleteData" type="button" onClick={() => deleteData(item.categoryId)}>Delete</button></td>
                <td><Link to={`/UpdateCategory/${item.categoryId}`} className="updateData">Update</Link></td>
              </tr>
            ))}
          </tbody>
        </table>
        </div>
      )}
    </div>
  );
}

export default Category;

