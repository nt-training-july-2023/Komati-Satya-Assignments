import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import './Questions.css';
import QuestionsApi from "../../Service/QuestionsApi";
import SweetAlert from "../../Components/SweetAlertComponents/SweetAlert";
import Input from "../../Components/Inputs/Input";
import ButtonComponent from "../../Components/Inputs/ButtonComponent";
import ErrorPage from "../../Components/ErrorPage";
import LabelComponent from "../../Components/LabelComponent/LabelComponent";
import H1Component from "../../Components/HeadingComponent/H1component";
import TextareaComponent from "../../Components/Inputs/TextareaComponent";

const AddQuestions = () => {
    const verifyRole = localStorage.getItem('userRole');
    const [errors, setErrors] = useState({});
    const { question } = useParams();
    const { quizId } = useParams();
    const [questionData, setQuestionData] = useState({
        question: "",
        option1: "",
        option2: "",
        option3: "",
        option4: "",
        correctOption: "",
        questionId: "",
        quizId: quizId
    });
    const navigate = useNavigate();
    const changeData = (e) => {
        setQuestionData({ ...questionData, [e.target.name]: e.target.value });
    }
    useEffect(() => {
        if (question) {
            QuestionsApi.getByQuestion(question)
                .then((response) => {
                    const questionInformation = response.data.data;
                    const { question, option1, option2, option3, option4, correctOption, questionId, quizId } = questionInformation;
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
                })
        }
    }, [quizId]);
    const showErrors = (e) => {
        e.preventDefault();
        const validationErrors = {};
        if (!questionData.question) {
            validationErrors.question = 'queation name Required';
        }
        if (!questionData.option1) {
            validationErrors.option1 = 'option1 Required';
        }
        if (!questionData.option2) {
            validationErrors.option2 = 'option2 Required';
        }
        if (!questionData.option3) {
            validationErrors.option3 = 'option3 Required';
        }
        if (!questionData.option4) {
            validationErrors.option4 = 'option4 Required';
        }
        if (!questionData.correctOption) {
            validationErrors.correctOption = 'correctOption Required';
        }
        if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors)
            if (validationErrors) {
                SweetAlert.fieldsRequired("all data required")
            }
        }
    }
    const addQuestionData = async (e) => {
        if (question) {
            showErrors(e)
            setErrors({});
            QuestionsApi.updateQuestion(questionData.questionId, questionData)
                .then((response) => {
                    if (response.data.message === "question updated successfully") {
                        SweetAlert.success("Question updated successfully")
                        navigate(`/Questions/${questionData.quizId}`);
                    }
                }).catch((error) => {
                    if (error.response.data.message == "Question already exists") {
                        SweetAlert.fieldsRequired("Question already present")
                    }
                   else if (error.response.data.message === "Options must be unique") {
                        SweetAlert.fieldsRequired("Options must be unique");
                    }
                    else if(error.response.data.StatusCode === "400"){
                        SweetAlert.fieldsRequired(error.response.data.message);
                    }
                    else if(error.response.data.message === "coorect option must same with one of four options"){
                        SweetAlert.fieldsRequired("coorect option must same with one of four options")
                    }
                })
        }
        else {
            showErrors(e)
            setErrors({});
            QuestionsApi.addQuestion(questionData).then(response => {
                if (response.data.message === "question added successfully") {
                    SweetAlert.success("Question added successfully");
                    navigate(`/Questions/${quizId}`);
                }
            }).catch(error => {
                if (error.response.data.message === "Question already exists") {
                    SweetAlert.fieldsRequired("Question already exist");
                }
                else if (error.response.data.message === "Options must be unique") {
                    SweetAlert.fieldsRequired("Options must be unique");
                }
               else if(error.response.data.StatusCode === "400"){
                    SweetAlert.fieldsRequired(error.response.data.message);
                }
                else if(error.response.data.message === "coorect option must same with one of four options"){
                    SweetAlert.fieldsRequired("coorect option must same with one of four options")
                }
            })
        }
    }
    const cancelAddQuestion = () => {
        if (question)
            navigate(`/Questions/${questionData.quizId}`)
        else
            navigate(`/Questions/${quizId}`)
    }
    return (
        <div className="login3">
            {verifyRole === 'Admin' ? (
                <div className="loginData3">
                    <H1Component className="heading3">
                        {quizId ? "Add Question" : "Update Question"}</H1Component>
                    <form>
                        <div className="signin4">
                            <LabelComponent className="label-data">Question</LabelComponent><br /><br />
                            <TextareaComponent
                                className="data3"
                                type="text"
                                name="question"
                                value={questionData.question}
                                placeholder="Enter question"
                                onChange={changeData}
                            /><br /><br />
                            <LabelComponent className="label-data">Option1</LabelComponent><br /><br />
                            <Input
                                className="data3"
                                type="text"
                                name="option1"
                                value={questionData.option1}
                                placeholder="Enter option1"
                                onChange={changeData}
                            /><br /><br />
                            <LabelComponent className="label-data">Option2</LabelComponent><br /><br />
                            <Input
                                className="data3"
                                type="text"
                                name="option2"
                                value={questionData.option2}
                                placeholder="Enter option2"
                                onChange={changeData}
                            /><br /><br />
                            <LabelComponent className="label-data">Option3</LabelComponent><br /><br />
                            <Input
                                className="data3"
                                type="text"
                                name="option3"
                                value={questionData.option3}
                                placeholder="Enter option3"
                                onChange={changeData}
                            /><br /><br />
                            <LabelComponent className="label-data">Option4</LabelComponent><br /><br />
                            <Input
                                className="data3"
                                type="text"
                                name="option4"
                                value={questionData.option4}
                                placeholder="Enter option4"
                                onChange={changeData}
                            /><br /><br />
                            <LabelComponent className="label-data">Correct Answer</LabelComponent><br /><br />
                            <select
                                className="data3"
                                name="correctOption"
                                value={questionData.correctOption}
                                onChange={changeData}
                            >
                                <option value="">Select Option</option>
                                <option value={questionData.option1}>{questionData.option1}</option>
                                <option value={questionData.option2}>{questionData.option2}</option>
                                <option value={questionData.option3}>{questionData.option3}</option>
                                <option value={questionData.option4}>{questionData.option4}</option>
                            </select>

                            <br /><br />
                            <ButtonComponent className="btn4" type="button" onClick={addQuestionData}>
                                {quizId ? "Add Question" : "update Question"}
                            </ButtonComponent>
                            <ButtonComponent className="btn5" type="button" onClick={cancelAddQuestion}>Cancel</ButtonComponent>
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