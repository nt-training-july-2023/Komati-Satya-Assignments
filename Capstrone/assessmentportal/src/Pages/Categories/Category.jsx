import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import './CategoryStyles.css'
import ErrorPage from "../../Components/ErrorPage";
import SweetAlert from "../../Components/SweetAlertComponents/SweetAlert";
import DisableBackButton from "../../Components/disableBackButton";
import CategoryApi from "../../Service/CategoryApi";
import Navbar from "../../Components/Navbar/Navbar";
import ButtonComponent from "../../Components/Inputs/ButtonComponent";
import H1Component from "../../Components/HeadingComponent/H1component";
import { FaPlusCircle } from 'react-icons/fa';
import Table from "../../Components/TableComponent/Table";
import SearchButton from "../../Components/SearchButton/SearchButton";

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
      }).catch((error)=>{
        console.error(error);
     })
      .finally(() => {
        setIsLoading(false);
      })
  };
  const deleteData = async (id) => {
    SweetAlert.deleteData("category", deleteCategory, id)
  }
  const deleteCategory = (id) => {
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
  const handleViewTopic = (categoryName,categoryId) => {
     localStorage.setItem('categoryName', categoryName) 
     localStorage.setItem('categoryId', categoryId) 
  }
  const rows = [
    'categoryName',
    'categoryDescription'
  ];
  const columns = [
    'Category Name',
    'Category Description'
  ]
  return (
    <div className="App">

      <div className="categoryData">
        <DisableBackButton />
        <Navbar />
        {(verifyRole === 'Admin' || verifyRole === 'student') ?
          <>
            <H1Component className="addHead">Category Details</H1Component>
            {verifyRole === 'Admin' && <ButtonComponent className="addButton" onClick={() => addData()} ><FaPlusCircle className="delete-icon" /> Add category</ButtonComponent>}
            <div className="searchContainer">
                <SearchButton
                originalData={originalCategory}
                setItem={setCategory}
                setText={setSearchText}
                placeholder="serach by category name"
                category={true}
              />    
            </div>
            {isLoading ? (
              <p>Loading...</p>
            ) : (
              <div className="tableContainer">
                {category.length !== 0 ? (
                  <Table columns={columns} data={category} rows={rows} category={true}
                    deleteData={deleteData}
                    viewQuizes={handleViewTopic}
                    role={verifyRole}
                    isTrue={true} />
                ) : (
                  <H1Component className="no-category">No Category</H1Component>
                )}
              </div>
            )}
          </> : <ErrorPage />}
      </div>
    </div>
  );
}
export default Category;

