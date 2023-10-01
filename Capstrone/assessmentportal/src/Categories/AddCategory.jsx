import React, { useState ,useEffect} from "react";
import { useNavigate, useParams } from "react-router-dom";
import './AddCategoryStyles.css';
import ErrorPage from "../ErrorPage";
import CategoryApi from "../APIs/CategoryApi";
import Input from "../Inputs/Input";
import ButtonComponent from "../Inputs/ButtonComponent";
import TextareaComponent from "../Inputs/TextareaComponent";
import SweetAlert from "../SweetAlertComponents/SweetAlert";
const AddCategory = () => {
    const verifyRole = localStorage.getItem('userRole');
    const { categoryId } = useParams();
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
             CategoryApi.getCategoryById(categoryId)
          .then((response) => {
    
            const userInformation = response.data;
            const { categoryName, categoryDescription } = userInformation;
            setCategoryData({
              categoryName,
              categoryDescription,
            });
          })
         
        }
      }, [categoryId]);
      const showErrors=(e)=>{
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
                 SweetAlert.fieldsRequired("Category data required");
            }
            else if (validationErrors.categoryName && !(validationErrors.categoryDescription)) {
                SweetAlert.fieldsRequired("Category name required");
            }
            if (validationErrors.categoryDescription && !(validationErrors.categoryName)) {
                SweetAlert.fieldsRequired("Category description required");
            }
        } 
      }
    const handleSubmit=async(e)=>{
    if(categoryId) {          
   
                 showErrors(e);
                setErrors({});
            CategoryApi.updateCategory(categoryId,categoryData)
              .then((response) => {
                if (response.data.message === "updated category") {
                  SweetAlert.success("Category updated successfully");
                  navigate('/Category')
                }
              }).catch((error)=>{
                if(error.response.data.message=="Category already exists"){
                   SweetAlert.fieldsRequired("Category already present");
                }
              })
            }
          
else{
          showErrors(e);
            setErrors({});
       CategoryApi.addCategory(categoryData).then(response=>{
        
          if (response.data.message === "category added successfully") {
            SweetAlert.success("Category added successfully");
            navigate('/Category');
           
        }
      
    }).catch((error)=>{
       if (error.response.status === 302) {
        SweetAlert.fieldsRequired("Category already present");
    }
    })    

}
    }
    const cancelAddCategory = () => {
        SweetAlert.cancel("category",navigate,'/Category')
    }
    return (
        <div className="login3">
            {verifyRole === 'Admin' ? <>
                <div className="loginData3">
                    <h1 className="heading3">
                    {categoryId ? "Update category" : "Add Category"}</h1>
                    <form>
                        <div className="signin3">
                            <label className="head3">Category Name</label><br></br><br></br>

                            <Input className="data3" type="text" name="categoryName" value={categoryData.categoryName} placeholder="Enter category name" onChange={changeData}/><br></br><br></br>
                            <label className="head3">Category description</label><br></br><br></br>
                            <TextareaComponent className="data3" type="text" name="categoryDescription" value={categoryData.categoryDescription} placeholder="Enter category description" onChange={changeData}></TextareaComponent>
                            <ButtonComponent className="btn4" type="button" onClick={handleSubmit} >
                                {categoryId ? "Update Category" : "Add Category"}
                                </ButtonComponent>
                            
                            <ButtonComponent className="btn5" type="button" onClick={cancelAddCategory}>Cancel</ButtonComponent>
                        </div>
                    </form>
                </div>
            </> : <ErrorPage />}
        </div>
    );

}

export default AddCategory;