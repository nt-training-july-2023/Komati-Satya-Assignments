import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import QuizApi from "../../Service/QuizApi";
import Input from "../../Components/Inputs/Input";
import ButtonComponent from "../../Components/Inputs/ButtonComponent";
import TextareaComponent from "../../Components/Inputs/TextareaComponent";
import SweetAlert from "../../Components/SweetAlertComponents/SweetAlert";
import ErrorPage from "../../Components/ErrorPage";
import LabelComponent from "../../Components/LabelComponent/LabelComponent";
import H1Component from "../../Components/HeadingComponent/H1component";
import './Quiz.css'
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
    const navigate = useNavigate();
    const changeData = (e) => {
        setQuizData({ ...quizData, [e.target.name]: e.target.value });
    }
    useEffect(() => {
        if (quizId) {
            QuizApi.getQuiz(quizId)
                .then((response) => {
                    const quizInformation = response.data.data;
                    const { topicName, topicDescription, timer, categoryId } = quizInformation;
                    setQuizData({
                        topicName,
                        topicDescription,
                        timer,
                        categoryId
                    });
                })
        }
    }, [quizId]);
    const showErrors = (e) => {
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
                    if (response.data.message === "quiz updated successfully") {
                        SweetAlert.success("Successfully updated data")
                        navigate(`/Quiz/${quizData.categoryId}`)
                    }
                }).catch((error) => {
                    if (error.response.data.message === "Quiz already exist") {
                        SweetAlert.fieldsRequired("Quiz already present")
                    }
                    if (error.response.status === 400) {
                        SweetAlert.fieldsRequired(error.response.data.message)
                    }
                })
        }
        else {
            showErrors(e);
            setErrors({});
            QuizApi.addQuiz(quizData).then(response => {
                if (response.data.message === "succcessfully add the data") {
                    SweetAlert.success("Quiz added successfully");
                    navigate(`/Quiz/${quizData.categoryId}`);
                }
            }).catch(error => {
                if (error.response.status === 302) {
                    SweetAlert.fieldsRequired("Quiz already exist")
                }
                if (error.response.status === 400) {
                    SweetAlert.fieldsRequired(error.response.data.message)
                }
            })
        }
    }
    const cancelAddQuiz = () => {
        navigate(`/Quiz/${quizData.categoryId}`);
    }

    return (
        <div className="login3">
            {verifyRole === 'Admin' ? (
                <div className="loginData">
                    <H1Component className="heading3">
                        {quizId ? "Update Quiz" : "Add Quiz"}</H1Component>
                    <form>
                        <div className="quiz-data">
                            <LabelComponent className="head3">Topic Name</LabelComponent><br /><br />
                            <Input
                                className="quiz-input"
                                type="text"
                                name="topicName"
                                value={quizData.topicName}
                                placeholder="Enter topic name"
                                onChange={changeData}
                            /><br /><br />
                            <LabelComponent className="head3">Topic Description</LabelComponent><br /><br />
                            <TextareaComponent
                                className="quiz-input"
                                type="text"
                                name="topicDescription"
                                value={quizData.topicDescription}
                                placeholder="Enter topic description"
                                onChange={changeData}
                            ></TextareaComponent>
                            <div>
                                <LabelComponent className="head3">Time</LabelComponent><br /><br />
                                <Input
                                    className="quiz-input"
                                    type="number"
                                    name="timer"
                                    value={quizData.timer}
                                    placeholder="Enter time in min"
                                    onChange={changeData}
                                /><br /><br />
                            </div>
                            <ButtonComponent className="btn-quiz" type="button" onClick={addQuizData}>
                                {quizId ? "update Quiz" : "Add Quiz"}
                            </ButtonComponent>
                            <ButtonComponent className="btn-quiz-cancel" type="button" onClick={cancelAddQuiz}>Cancel</ButtonComponent>
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