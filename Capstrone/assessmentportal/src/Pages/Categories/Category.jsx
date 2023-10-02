import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import './CategoryStyles.css'
import ErrorPage from "../../ErrorPage";
import SweetAlert from "../../Components/SweetAlertComponents/SweetAlert";
import DisableBackButton from "../../Components/disableBackButton";
import CategoryApi from "../../Service/CategoryApi";
import Navbar from "../../Components/Navbar/Navbar";
import Input from "../../Components/Inputs/Input";
import ButtonComponent from "../../Components/Inputs/ButtonComponent";
import H1Component from "../../Components/HeadingComponent/H1component";
import { FaPlus, FaPencilAlt,FaTrash ,FaList, FaPlusCircle} from 'react-icons/fa';

function Category() {
  const verifyRole = localStorage.getItem('userRole');
  const [category, setCategory] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [searchText, setSearchText] = useState("");
  const [originalCategory, setOriginalCategory] = useState([]);
  useEffect(() => {
    getCategories();
  }, []);
  const getCategories = async () => {
    CategoryApi.getAllCategories().then(
      response => {
        setCategory(response.data.data || []);
        setOriginalCategory(response.data.data || []);
      }).finally(() => {
        setIsLoading(false);
      })
  };
  const deleteData = async (id) => {
          SweetAlert.deleteData("category",deleteCategory,id)
  }
  const deleteCategory=(id)=>{
    CategoryApi.deleteCategory(id).then(response => {
      if (response.data.message === "deleted category") {
       SweetAlert.success("Deleted successfully")
      }
      getCategories();
    }).catch(error => {

    })
  }
  const navigate = useNavigate();
  const addData = () => {
    navigate('/AddCategory');
  }
  const handleSearch = async () => {

    const filteredCategory = category.filter(item =>
      item.categoryName.toLowerCase().includes(searchText.toLowerCase())
    );
    setCategory(filteredCategory);
  };
  const clearSearch = () => {
    setCategory(originalCategory);
    setSearchText("");
  }
  const handleViewTopic = (categoryName) => {
    { localStorage.setItem('categoryName', categoryName) }
  }
  return (
    <div className="App">

      <div className="categoryData">
        <DisableBackButton />
        <Navbar />
        {(verifyRole === 'Admin' || verifyRole === 'student') ?
          <>
            <H1Component className="addHead">Category Details</H1Component>
            {verifyRole === 'Admin' && <ButtonComponent className="addButton" onClick={() => addData()} ><FaPlusCircle className="delete-icon"/> Add category</ButtonComponent>}
            <div className="searchContainer">
              <Input
                className="search"
                type="text"
                placeholder="Search by Category Name"
                value={searchText}
                onChange={(e) => setSearchText(e.target.value)}
              />
              <ButtonComponent className="searchButton" onClick={handleSearch}>Search</ButtonComponent>
              <ButtonComponent className="searchButton" onClick={clearSearch}>Clear Search</ButtonComponent>

            </div>
            {isLoading ? (
              <p>Loading...</p>
            ) : (
              <div className="tableContainer">
                {category.length !== 0 ? (
                  <table className="tableData">
                    <thead className="headData">
                      <tr className="rowData">
                        <th >Category Name</th>
                        <th>Category Description</th>
                        {(verifyRole==="Admin" &&
                        <>
                        <th></th>
                        <th>Actions</th>
                        <th></th>
                        </>
                        )}
                        {(verifyRole==="student" &&
                        <>
                        
                        <th>Actions</th>
                        <th></th>
                        </>
                        )}

                      </tr>
                    </thead>
                    <tbody className="bodyData">
                      {category.map(item => (

                        <tr key={item.categoryId}>
                          <td>{item.categoryName}</td>
                          <td>{item.categoryDescription}</td>
                          {verifyRole === 'Admin' && <>
                            <td><ButtonComponent className="deleteData" type="button" onClick={() => deleteData(item.categoryId)}><FaTrash className="delete-icon"/> Delete</ButtonComponent></td>
                            <td><Link to={`/UpdateCategory/${item.categoryId}`} className="updateData"><FaPencilAlt className="update-icon" /> Update</Link></td></>}
                          {verifyRole === 'Admin' || verifyRole === 'student' ? (
                            <td><Link to={`/Quiz/${item.categoryId}`} className="updateData" onClick={() => handleViewTopic(item.categoryName)}>
                              <FaList className="delete-icon" /> Quizes</Link></td>
                          ) : (
                            <></>
                          )}

                        </tr>
                      ))}
                    </tbody>
                  </table>
                ) : (
                  <h1 className="no-category">No Category</h1>
                )}
              </div>
            )}
          </> : <ErrorPage />}
      </div>

    </div>
  );
}
export default Category;

