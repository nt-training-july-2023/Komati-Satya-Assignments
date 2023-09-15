   
import React, { useState,useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import Swal from "sweetalert2";
import './Questions.css';
import ErrorPage from "../ErrorPage";
import QuestionsApi from "../APIs/QuestionsApi";

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
        // axios.get(`http://localhost:6002/questionByName/${question}`)
        QuestionsApi.getByQuestion(question)
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
                questionId,
                quizId
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
     if(question){
            e.preventDefault();
           const validationErrors = {};
        
             if (!questionData2.question) {
                 validationErrors.question = 'queation name Required';
             }
             if (!questionData2.option1) {
                 validationErrors.option1 = 'option1 Required';
             }
             if (!questionData2.option2) {
                validationErrors.option2 = 'option2 Required';
            }
            if (!questionData2.option3) {
                validationErrors.option3 = 'option3 Required';
            }
            if (!questionData2.option4) {
                validationErrors.option4 = 'option4 Required';
            }
            if (!questionData2.correctOption) {
                validationErrors.correctOption = 'correctOption Required';
            }
        
             if (Object.keys(validationErrors).length > 0) {
                 setErrors(validationErrors);
                 // if (validationErrors.topicName && validationErrors.topicDescription && validationErrors.maxMarks &&  validationErrors.passMarks ) {
                 if(validationErrors){   
                  Swal.fire({
                         title: 'Error!',
                         text: 'All question data required',
                         icon: 'error',
                         confirmButtonText: 'Ok'
                     });
                 }
        
             }
              else {
       
                setErrors({});
                console.log(questionData2)
           // axios.put(`http://localhost:6002/que/${questionData.questionId}`, questionData2)
           QuestionsApi.updateQuestion(questionData.questionId,questionData2)
              .then((response) => {
                console.log(response)
                if(response.data.status==207){
                    Swal.fire({
                        title: 'Error',
                        text: 'questions Already present',
                        icon: 'error',
                        confirmButtonText: 'Ok'
                      });
                }
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
            }
        
        
  else{
        e.preventDefault();

       const validationErrors = {};

       if (!questionData2.question) {
        validationErrors.question = 'queation name Required';
    }
    if (!questionData2.option1) {
        validationErrors.option1 = 'option1 Required';
    }
    if (!questionData2.option2) {
       validationErrors.option2 = 'option2 Required';
   }
   if (!questionData2.option3) {
       validationErrors.option3 = 'option3 Required';
   }
   if (!questionData2.option4) {
       validationErrors.option4 = 'option4 Required';
   }
   if (!questionData2.correctOption) {
       validationErrors.correctOption = 'correctOption Required';
   }

    if (Object.keys(validationErrors).length > 0) {
        setErrors(validationErrors);
        // if (validationErrors.topicName && validationErrors.topicDescription && validationErrors.maxMarks &&  validationErrors.passMarks ) {
        if(validationErrors){   
        await Swal.fire({
                title: 'Error!',
                text: 'All question data required',
                icon: 'error',
                confirmButtonText: 'Ok'
            });
        }

        } else {
        setErrors({});

      //  const response = await axios.post('http://localhost:6002/que', requestData);
      QuestionsApi.addQuestion(requestData).then(requestData).then(response=>{
        console.log(response.data.message);
         
        if (response.data.message === "succcessfully add the data") {
            console.log("jygd")
            Swal.fire({
                title: 'Add Question',
                text: 'Added Question',
                icon: 'success',
                confirmButtonText: 'Ok'
            });
            navigate(`/Questions/${quizId}`);
            
        } else if (response.data.message === "question already exist") {
             Swal.fire({
                title: 'Error!',
                text: 'Question already present',
                icon: 'error',
                confirmButtonText: 'Ok'
            });
        }
    }).catch(error=>{
        console.log(error)
    })
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
            navigate(`/Questions/${questionData.quizId}`);

        } else if (result.isDenied) {

        }
    })
}
    
    return (
        <div className="login3">
           
            {verifyRole === 'Admin' ? (
                <div className="loginData3">
                    <h1 className="heading3">
                        {quizId?  "Add Question": "Update Question" }</h1>
                    <form>
                        <div className="signin4">
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
                            {quizId ? "Add Question" :"update Question" }
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