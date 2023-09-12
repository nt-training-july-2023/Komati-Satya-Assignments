
import React, { useState,useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import Swal from "sweetalert2";

import ErrorPage from "../ErrorPage";

const AddQuiz = () => {
    const verifyRole = localStorage.getItem('userRole');
    const [errors, setErrors] = useState({});
    const { categoryId } = useParams();
    console.log(categoryId);
    const { quizId } = useParams();
    const [quizData, setQuizData] = useState({
        topicName: "",
        topicDescription: "",
        maxMarks: "",
        passMarks: "",
        categoryId:parseInt(categoryId, 10)
    });
    const requestData = {
      
        topicName: quizData.topicName,
        topicDescription: quizData.topicDescription,
        maxMarks: quizData.maxMarks,
        passMarks: quizData.passMarks,
        cate: {
            categoryId: quizData.categoryId
        }
    };
    console.log("yfu",quizData.Id)
    const navigate = useNavigate();

    const changeData = (e) => {
        setQuizData({ ...quizData, [e.target.name]: e.target.value });
    }
    useEffect(() => {
        if(quizId){
        axios.get(`http://localhost:6002/quiz/${quizId}`)
          .then((response) => {
    
            console.log(response);
            const quizInformation = response.data.Quiz_topic_Information;
            console.log(quizInformation)
            const { topicName, topicDescription,maxMarks,passMarks ,categoryId} = quizInformation;
            setQuizData({
                topicName,
                topicDescription,
                maxMarks,
                passMarks,
                categoryId
            });
          })
          .catch((error) => {
            console.error("An error occurred:", error);
          });
        }
      }, [quizId]);
    const addQuizData = async (e) => {


       if(quizId){
            e.preventDefault();
        
            
            const validationErrors = {};
        
             if (!quizData.topicName) {
                 validationErrors.topicName = 'quiz name Required';
             }
             if (!quizData.topicDescription) {
                 validationErrors.topicDescription = 'quiz description Required';
             }
             if (!quizData.maxMarks) {
                 validationErrors.maxMarks = 'max marks Required';
             }
             if (!quizData.passMarks) {
                 validationErrors.passMarks = 'pass marks Required';
             }
        
             if (Object.keys(validationErrors).length > 0) {
                 setErrors(validationErrors);
                 // if (validationErrors.topicName && validationErrors.topicDescription && validationErrors.maxMarks &&  validationErrors.passMarks ) {
                 if(validationErrors){   
                 await Swal.fire({
                         title: 'Error!',
                         text: 'All quiz data required',
                         icon: 'error',
                         confirmButtonText: 'Ok'
                     });
                 }
        
             } else {
        
                setErrors({});
            axios.put(`http://localhost:6002/quiz/${quizId}`, quizData)
              .then((response) => {
                if (response.data.message === "succcessfully update the data") {
                  Swal.fire({
                    title: 'Updating data',
                    text: 'Successfully updated data',
                    icon: 'success',
                    confirmButtonText: 'Ok'
                  });
                  navigate(`/Quiz/${quizData.categoryId}`)
                }
              })
            }
        }
    

      
  else{
        e.preventDefault();
       console.log(quizData)
       const validationErrors = {};

        if (!quizData.topicName) {
            validationErrors.topicName = 'quiz name Required';
        }
        if (!quizData.topicDescription) {
            validationErrors.topicDescription = 'quiz description Required';
        }
        if (!quizData.maxMarks) {
            validationErrors.maxMarks = 'max marks Required';
        }
        if (!quizData.passMarks) {
            validationErrors.passMarks = 'pass marks Required';
        }

        if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors);
            // if (validationErrors.topicName && validationErrors.topicDescription && validationErrors.maxMarks &&  validationErrors.passMarks ) {
            if(validationErrors){   
            await Swal.fire({
                    title: 'Error!',
                    text: 'All quiz data required',
                    icon: 'error',
                    confirmButtonText: 'Ok'
                });
            }

        } else {
        setErrors({});

        const response = await axios.post('http://localhost:6002/quiz', requestData);
        console.log(response.data.message);
         
        if (response.data.message === "succcessfully add the data") {
            console.log("jygd")
            await Swal.fire({
                title: 'Add Quiz',
                text: 'Added Quiz',
                icon: 'success',
                confirmButtonText: 'Ok'
            });
            navigate(`/Quiz/${quizData.categoryId}`);
            
        } else if (response.data.message === "Topic is already exist,enter a new topic") {
            await Swal.fire({
                title: 'Error!',
                text: 'Quiz already present',
                icon: 'error',
                confirmButtonText: 'Ok'
            });
        }
    }
}
    }

const cancelAddQuiz = () => {
    Swal.fire({
        title: 'Do you want to cancel the  quiz?',
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
            navigate(`/Quiz/${quizData.categoryId}`);

        } else if (result.isDenied) {

        }
    })
}
    return (
        <div className="login3">
            {verifyRole === 'Admin' ? (
                <div className="loginData3">
                    <h1 className="heading3">
                        {quizId? "Update Quiz": "Add Quiz"}</h1>
                    <form>
                        <div className="signin3">
                            <label className="head3">Topic Name</label><br /><br />
                            <input
                                className="data3"
                                type="text"
                                name="topicName"
                                value={quizData.topicName}
                                placeholder="Enter topic name"
                                onChange={changeData}
                            /><br /><br />
                            <label className="head3">Topic Description</label><br /><br />
                            <textarea
                                className="data3"
                                type="text"
                                name="topicDescription"
                                value={quizData.topicDescription}
                                placeholder="Enter topic description"
                                onChange={changeData}
                            ></textarea>
                            <label className="head3">Max Marks</label><br /><br />
                            <input
                                className="data3"
                                type="text"
                                name="maxMarks"
                                value={quizData.maxMarks}
                                placeholder="Enter max marks"
                                onChange={changeData}
                            /><br /><br />
                            <label className="head3">Pass Marks</label><br /><br />
                            <input
                                className="data3"
                                type="text"
                                name="passMarks"
                                value={quizData.passMarks}
                                placeholder="Enter pass marks"
                                onChange={changeData}
                            /><br /><br />
                            <button className="btn4" type="button" onClick={addQuizData}>
                            {quizId ? "update Quiz" : "Add Quiz"}
                            </button>
                            <button className="btn5" type="button" onClick={cancelAddQuiz}>Cancel</button>
                        </div>
                    </form>
                </div>
            ) : (
                <ErrorPage />
            )}
        </div>
    );
}

export default AddQuiz
