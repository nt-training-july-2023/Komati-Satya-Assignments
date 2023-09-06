
import axios from "axios";
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
function Category() {
  const [category, setCategory] = useState([]);
  const [isLoading, setIsLoading] = useState(true); 

  useEffect(() => {
    getCategories();
  }, []);
  const getCategories = async () => {
    try {
      const response = await axios.get('http://localhost:6002/cat');
      setCategory(response.data.Category_Information || []); 
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
  return (
    <>
      <button onClick={()=>addData()}>Add category</button>
      {isLoading ? (
        <p>Loading...</p>
      ) : (
        <table>
          <thead>
            <tr>
              <th>Category Name</th>
              <th>Category Description</th>
              <th>Delete</th>
            </tr>
          </thead>
          <tbody>
            {category.map(item => (
              <tr key={item.categoryId}>
                <td>{item.categoryName}</td>
                <td>{item.categoryDescription}</td>
                <td><button type="button" onClick={() => deleteData(item.categoryId)}>Delete</button></td>
                {/* <td><Link to={`/UpdateCategory/${item.categoryId}`}>Update</Link></td> */}
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </>
  );
}

export default Category;

