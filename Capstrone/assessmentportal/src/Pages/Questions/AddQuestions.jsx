import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import './Questions.css';
import QuestionsApi from "../../Service/QuestionsApi";
import SweetAlert from "../../Components/SweetAlertComponents/SweetAlert";
import Input from "../../Components/Inputs/Input";
import ButtonComponent from "../../Components/Inputs/ButtonComponent";
import ErrorPage from "../../ErrorPage";
import LabelComponent from "../../Components/LabelComponent/LabelComponent";
import H1Component from "../../Components/HeadingComponent/H1component";

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
        quizId: ""

    });
    const [questionData2, setQuestionData2] = useState({
        question: "",
        option1: "",
        option2: "",
        option3: "",
        option4: "",
        correctOption: "",
        quizId: quizId

    });
   
    const navigate = useNavigate();

    const changeData = (e) => {
        setQuestionData2({ ...questionData2, [e.target.name]: e.target.value });

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
                    setQuestionData2({
                        question,
                        option1,
                        option2,
                        option3,
                        option4,
                        correctOption
                    });
                })
               
        }
    }, [quizId]);
    const showErrors=(e)=>{
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
                QuestionsApi.updateQuestion(questionData.questionId, questionData2)
                    .then((response) => {
                      
                        if (response.data.message=== "question updated successfully") {
                           SweetAlert.success("Question updated successfully")
                            navigate(`/Questions/${questionData.quizId}`);
                        }
                    }).catch((error)=>{
                        if (error.response.data.message=="Question already exists") {
                           SweetAlert.fieldsRequired("Question already present")
                        }
                        if(error.response.data.message === "Options must be unique"){
                            SweetAlert.fieldsRequired("Options must be unique");
                        }
                    })

            }
        
        else {
               showErrors(e)
                setErrors({});
                QuestionsApi.addQuestion(questionData2).then(response => {
                    if (response.data.message === "question added successfully") {
                        SweetAlert.success("Question added successfully");
                        navigate(`/Questions/${quizId}`);

                    } 
                }).catch(error => {
                    console.log(error)
                    if (error.response.message === "Question already exists") {
                        SweetAlert.fieldsRequired("Question already exist");
                    }
                    if(error.response.data.message === "Options must be unique"){
                        SweetAlert.fieldsRequired("Options must be unique");
                    }
                })
            
        }
    }

    const cancelAddQuestion = () => {
        if(question)
        SweetAlert.cancel("Question?",navigate,`/Questions/${questionData.quizId}`)
        else
        SweetAlert.cancel("Question?",navigate,`/Questions/${quizId}`)
    }

    return (
        <div className="login3">

            {verifyRole === 'Admin' ? (
                <div className="loginData3">
                    <H1Component className="heading3">
                        {quizId ? "Add Question" : "Update Question"}</H1Component>
                    <form>
                        <div className="signin4">
                            <LabelComponent className="head3">Question</LabelComponent><br /><br />
                            <Input
                                className="data3"
                                type="text"
                                name="question"
                                value={questionData2.question}
                                placeholder="Enter question"
                                onChange={changeData}
                            /><br /><br />
                            <LabelComponent className="head3">Option1</LabelComponent><br /><br />
                            <Input
                                className="data3"
                                type="text"
                                name="option1"
                                value={questionData2.option1}
                                placeholder="Enter option1"
                                onChange={changeData}
                            /><br /><br />
                            <LabelComponent className="head3">Option2</LabelComponent><br /><br />
                            <Input
                                className="data3"
                                type="text"
                                name="option2"
                                value={questionData2.option2}
                                placeholder="Enter option2"
                                onChange={changeData}
                            /><br /><br />
                            <LabelComponent className="head3">Option3</LabelComponent><br /><br />
                            <Input
                                className="data3"
                                type="text"
                                name="option3"
                                value={questionData2.option3}
                                placeholder="Enter option3"
                                onChange={changeData}
                            /><br /><br />
                            <LabelComponent className="head3">Option4</LabelComponent><br /><br />
                            <Input
                                className="data3"
                                type="text"
                                name="option4"
                                value={questionData2.option4}
                                placeholder="Enter option4"
                                onChange={changeData}
                            /><br /><br />
                            <LabelComponent className="head3">Correct Answer</LabelComponent><br /><br />
                            <select
                                className="data3"
                                name="correctOption"
                                value={questionData2.correctOption}
                                onChange={changeData}
                            >
                                <option value="">Select Option</option>
                                <option value={questionData2.option1}>{questionData2.option1}</option>
                                <option value={questionData2.option2}>{questionData2.option2}</option>
                                <option value={questionData2.option3}>{questionData2.option3}</option>
                                <option value={questionData2.option4}>{questionData2.option4}</option>
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