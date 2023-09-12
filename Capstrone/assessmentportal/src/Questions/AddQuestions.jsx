   
import React, { useState,useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import Swal from "sweetalert2";

import ErrorPage from "../ErrorPage";

const AddQuestions=()=>{
    const verifyRole = localStorage.getItem('userRole');
    const [errors, setErrors] = useState({});
    const { question } = useParams();
    
    // console.log(categoryId);
    const { quizId } = useParams();
    console.log(quizId)
    const [questionData, setQuestionData] = useState({
        question: "",
        option1: "",
        option2: "",
        option3: "",
        option4: "",
        correctOption: "",
        questionId:"",
        quizId:""
       
    });
    const [questionData2, setQuestionData2] = useState({
        question: "",
        option1: "",
        option2: "",
        option3: "",
        option4: "",
        correctOption: ""
       
    });
    const requestData = {
      
         question:questionData2.question,
         option1:questionData2.option1,
         option2:questionData2.option2,
         option3:questionData2.option3,
         option4:questionData2.option4,
         correctOption:questionData2.correctOption,
         qui:{
            quizId:quizId
         }
    };
    
    const navigate = useNavigate();

    const changeData = (e) => {
        setQuestionData2({ ...questionData2, [e.target.name]: e.target.value });
    }
    useEffect(() => {
        if(question){
        axios.get(`http://localhost:6002/questionByName/${question}`)
          .then((response) => {
    
            console.log(response);
            const questionInformation = response.data.Questions_Information;
            
            const {question,option1,option2,option3,option4,correctOption,questionId,quizId} = questionInformation;
            setQuestionData({
                question,
                option1,
                option2,
                option3,
                option4,
                correctOption,
                questionId,quizId
            });
            setQuestionData2({
                question,
                option1,
                option2,
                option3,
                option4,
                correctOption   
            });
          })
          .catch((error) => {
            console.error("An error occurred:", error);
          });
        }
      }, [quizId]);
    const addQuestionData = async (e) => {


//        if(quizId){
//             e.preventDefault();
        
            
//             const validationErrors = {};
        
//              if (!quizData.topicName) {
//                  validationErrors.topicName = 'quiz name Required';
//              }
//              if (!quizData.topicDescription) {
//                  validationErrors.topicDescription = 'quiz description Required';
//              }
//              if (!quizData.maxMarks) {
//                  validationErrors.maxMarks = 'max marks Required';
//              }
//              if (!quizData.passMarks) {
//                  validationErrors.passMarks = 'pass marks Required';
//              }
        
//              if (Object.keys(validationErrors).length > 0) {
//                  setErrors(validationErrors);
//                  // if (validationErrors.topicName && validationErrors.topicDescription && validationErrors.maxMarks &&  validationErrors.passMarks ) {
//                  if(validationErrors){   
//                  await Swal.fire({
//                          title: 'Error!',
//                          text: 'All quiz data required',
//                          icon: 'error',
//                          confirmButtonText: 'Ok'
//                      });
//                  }
        
//              } else {
        if(question){
                setErrors({});
                console.log(questionData2)
            axios.put(`http://localhost:6002/que/${questionData.questionId}`, questionData2)
              .then((response) => {
                console.log(response)
                if (response.data.message === "succcessfully update the data") {
                  Swal.fire({
                    title: 'Updating data',
                    text: 'Successfully updated data',
                    icon: 'success',
                    confirmButtonText: 'Ok'
                  });
                  navigate(`/Questions/${questionData.quizId}`);
                }
              })
            
        
            }

      
//   else{
//         e.preventDefault();
// //        console.log(quizData)
//        const validationErrors = {};

//         if (!quizData.topicName) {
//             validationErrors.topicName = 'quiz name Required';
//         }
//         if (!quizData.topicDescription) {
//             validationErrors.topicDescription = 'quiz description Required';
//         }
//         if (!quizData.maxMarks) {
//             validationErrors.maxMarks = 'max marks Required';
//         }
//         if (!quizData.passMarks) {
//             validationErrors.passMarks = 'pass marks Required';
//         }

//         if (Object.keys(validationErrors).length > 0) {
//             setErrors(validationErrors);
//             // if (validationErrors.topicName && validationErrors.topicDescription && validationErrors.maxMarks &&  validationErrors.passMarks ) {
//             if(validationErrors){   
//             await Swal.fire({
//                     title: 'Error!',
//                     text: 'All quiz data required',
//                     icon: 'error',
//                     confirmButtonText: 'Ok'
//                 });
//             }

//         } else {
//         setErrors({});
else{
        const response = await axios.post('http://localhost:6002/que', requestData);
        console.log(response.data.message);
         
        if (response.data.message === "succcessfully add the data") {
            console.log("jygd")
            await Swal.fire({
                title: 'Add Quiz',
                text: 'Added Quiz',
                icon: 'success',
                confirmButtonText: 'Ok'
            });
            navigate(`/Questions/${quizId}`);
            
        } else if (response.data.message === "question already exist") {
            await Swal.fire({
                title: 'Error!',
                text: 'Question already present',
                icon: 'error',
                confirmButtonText: 'Ok'
            });
        }
    }
}
const cancelAddQuestion = () => {
    Swal.fire({
        title: 'Do you want to cancel the  question?',
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
            navigate(`/Questions/${quizId}`);

        } else if (result.isDenied) {

        }
    })
}
    return (
        <div className="login3">
           
            {verifyRole === 'Admin' ? (
                <div className="loginData3">
                    <h1 className="heading3">
                        {quizId?  "Add Quiz": "Update Quiz" }</h1>
                    <form>
                        <div className="signin3">
                            <label className="head3">Question</label><br /><br />
                            <input
                                className="data3"
                                type="text"
                                name="question"
                                value={questionData2.question}
                                placeholder="Enter question"
                                onChange={changeData}
                            /><br /><br />
                            <label className="head3">Option1</label><br /><br />
                            <input
                                className="data3"
                                type="text"
                                name="option1"
                                value={questionData2.option1}
                                placeholder="Enter option1"
                                onChange={changeData}
                            /><br /><br />
                            <label className="head3">Option2</label><br /><br />
                            <input
                                className="data3"
                                type="text"
                                name="option2"
                                value={questionData2.option2}
                                placeholder="Enter option2"
                                onChange={changeData}
                            /><br /><br />
                            <label className="head3">Option3</label><br /><br />
                            <input
                                className="data3"
                                type="text"
                                name="option3"
                                value={questionData2.option3}
                                placeholder="Enter option3"
                                onChange={changeData}
                            /><br /><br />
                            <label className="head3">Option4</label><br /><br />
                            <input
                                className="data3"
                                type="text"
                                name="option4"
                                value={questionData2.option4}
                                placeholder="Enter option4"
                                onChange={changeData}
                            /><br /><br />
                            <label className="head3">Correct Option</label><br /><br />
                            <input
                                className="data3"
                                type="text"
                                name="correctOption"
                                value={questionData2.correctOption}
                                placeholder="Enter correct option"
                                onChange={changeData}
                            /><br /><br />
                            <button className="btn4" type="button" onClick={addQuestionData}>
                            {quizId ? "Add Quiz" :"update Quiz" }
                            </button>
                            <button className="btn5" type="button" onClick={cancelAddQuestion}>Cancel</button>
                        </div>
                    </form>
                </div>
            ) : (
                <ErrorPage />
            )}
        </div>
    );
}


export default AddQuestions