
import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Swal from "sweetalert2";
import './QuizStyles.css'
import ErrorPage from "../ErrorPage";
import QuizApi from "../APIs/QuizApi";

const AddQuiz = () => {
    const verifyRole = localStorage.getItem('userRole');
    const [errors, setErrors] = useState({});
    const { categoryId } = useParams();
    console.log(categoryId);
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
                    console.log(response);
                    const quizInformation = response.data.Quiz_topic_Information;
                    console.log(quizInformation)
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
    const addQuizData = async (e) => {
        if (quizId) {
            e.preventDefault();


            const validationErrors = {};

            if (!quizData.topicName) {
                validationErrors.topicName = 'quiz name Required';
            }
            if (!quizData.topicDescription) {
                validationErrors.topicDescription = 'quiz description Required';
            }

            if (Object.keys(validationErrors).length > 0) {
                setErrors(validationErrors);
                if (validationErrors) {
                    Swal.fire({
                        title: 'Error!',
                        text: 'All quiz data required',
                        icon: 'error',
                        confirmButtonText: 'Ok'
                    });
                }

            } else {
                setErrors({});
                QuizApi.updateQuiz(quizId, quizData)
                    .then((response) => {
                        if (response.data.status == 207) {
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
                            navigate(`/Quiz/${quizData.categoryId}`)
                        }
                    })
            }
        }
        else {
            e.preventDefault();
            const validationErrors = {};

            if (!quizData.topicName) {
                validationErrors.topicName = 'quiz name Required';
            }
            if (!quizData.topicDescription) {
                validationErrors.topicDescription = 'quiz description Required';
            }


            if (Object.keys(validationErrors).length > 0) {
                setErrors(validationErrors);
                if (validationErrors) {
                    Swal.fire({
                        title: 'Error!',
                        text: 'All quiz data required',
                        icon: 'error',
                        confirmButtonText: 'Ok'
                    });
                }

            } else {
                setErrors({});
                QuizApi.addQuiz(requestData).then(response => {
                    if (response.data.message === "succcessfully add the data") {
                        Swal.fire({
                            title: 'Add Quiz',
                            text: 'Added Quiz',
                            icon: 'success',
                            confirmButtonText: 'Ok'
                        });
                        navigate(`/Quiz/${quizData.categoryId}`);

                    } else if (response.data.message === "Topic is already exist,enter a new topic") {
                        Swal.fire({
                            title: 'Error!',
                            text: 'Quiz already present',
                            icon: 'error',
                            confirmButtonText: 'Ok'
                        });
                    }
                }).catch(error => {
                    console.log(error)
                })
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
                        {quizId ? "Update Quiz" : "Add Quiz"}</h1>
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
                            <div>
                                <label className="head3">Time</label><br /><br />
                                <input
                                    className="data3"
                                    type="number"
                                    name="timer"
                                    value={quizData.timer}
                                    placeholder="Enter time in min"
                                    onChange={changeData}
                                /><br /><br />
                            </div>
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
