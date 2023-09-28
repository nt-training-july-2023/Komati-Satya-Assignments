import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Swal from "sweetalert2";
import ErrorPage from "../ErrorPage";
import QuizApi from "../APIs/QuizApi";
import Input from "../Inputs/Input";
import ButtonComponent from "../Inputs/ButtonComponent";
import TextareaComponent from "../Inputs/TextareaComponent";
import SweetAlert from "../SweetAlertComponents/SweetAlert";

const AddQuiz = () => {
    const verifyRole = localStorage.getItem('userRole');
    const [errors, setErrors] = useState({});
    const { categoryId } = useParams();
    const { quizId } = useParams();
    const [quizData, setQuizData] = useState({
        topicName: "",
        topicDescription: "",
        timer: "",
        categoryId: parseInt(categoryId, 10)
    });
    const requestData = {
        topicName: quizData.topicName,
        topicDescription: quizData.topicDescription,
        timer: quizData.timer,
        cate: {
            categoryId: quizData.categoryId
        }
    };
    const navigate = useNavigate();
    const changeData = (e) => {
        setQuizData({ ...quizData, [e.target.name]: e.target.value });
    }
    useEffect(() => {
        if (quizId) {
            QuizApi.getQuiz(quizId)
                .then((response) => {
                    const quizInformation = response.data;
                    const { topicName, topicDescription, timer, categoryId } = quizInformation;
                    setQuizData({
                        topicName,
                        topicDescription,
                        timer,
                        categoryId
                    });
                })
                .catch((error) => {
                    console.error("An error occurred:", error);
                });
        }
    }, [quizId]);
    const showErrors=(e)=>{
        e.preventDefault();
         const validationErrors = {};

        if (!quizData.topicName) {
            validationErrors.topicName = 'quiz name Required';
        }
        if (!quizData.topicDescription) {
            validationErrors.topicDescription = 'quiz description Required';
        }
        if (!quizData.timer) {
            validationErrors.timer = 'timer Required';
        }

        if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors);
            if (validationErrors) {
                SweetAlert.fieldsRequired("All quiz data required")
            }

        }
    }
    const addQuizData = async (e) => {
        if (quizId) {
                showErrors(e)
                setErrors({});
                QuizApi.updateQuiz(quizId, quizData)
                    .then((response) => {
                      
                        if (response.data === "quiz updated successfully") {
                            SweetAlert.success("Successfully updated data")
                            navigate(`/Quiz/${quizData.categoryId}`)
                        }
                    }).catch((error)=>{
                       
                        if (error.response.data.errorMessage === "Quiz already exist") {
                           SweetAlert.fieldsRequired("Quiz already present")
                        }
                        if(error.response.status === 400){
                            SweetAlert.fieldsRequired("Time limit greaterthan 0")
                        }
                    })
            }
        
        else {
        
               showErrors(e);
                setErrors({});
                QuizApi.addQuiz(requestData).then(response => {
                    if (response.data === "succcessfully add the data") {
                        SweetAlert.success("Quiz added successfully");
                        navigate(`/Quiz/${quizData.categoryId}`);

                    } 
                }).catch(error => {
                    console.log(error)
                    if (error.response.status === 302) {
                        SweetAlert.fieldsRequired("Quiz already exist")
                    }
                    if(error.response.status === 400){
                        SweetAlert.fieldsRequired("Time limit greaterthan 0")
                    }
                })
            }
        
    }

    const cancelAddQuiz = () => {
        SweetAlert.cancel("quiz",navigate,`/Quiz/${quizData.categoryId}`)
    }

    return (
        <div className="login3">
            {verifyRole === 'Admin' ? (
                <div className="loginData3">
                    <h1 className="heading3">
                        {quizId ? "Update Quiz" : "Add Quiz"}</h1>
                    <form>
                        <div className="signin3">
                            <label className="head3">Topic Name</label><br /><br />
                            <Input
                                className="data3"
                                type="text"
                                name="topicName"
                                value={quizData.topicName}
                                placeholder="Enter topic name"
                                onChange={changeData}
                            /><br /><br />
                            <label className="head3">Topic Description</label><br /><br />
                            <TextareaComponent
                                className="data3"
                                type="text"
                                name="topicDescription"
                                value={quizData.topicDescription}
                                placeholder="Enter topic description"
                                onChange={changeData}
                            ></TextareaComponent>
                            <div>
                                <label className="head3">Time</label><br /><br />
                                <Input
                                    className="data3"
                                    type="number"
                                    name="timer"
                                    value={quizData.timer}
                                    placeholder="Enter time in min"
                                    onChange={changeData}
                                /><br /><br />
                            </div>
                            <ButtonComponent className="btn4" type="button" onClick={addQuizData}>
                                {quizId ? "update Quiz" : "Add Quiz"}
                            </ButtonComponent>
                            <ButtonComponent className="btn5" type="button" onClick={cancelAddQuiz}>Cancel</ButtonComponent>
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