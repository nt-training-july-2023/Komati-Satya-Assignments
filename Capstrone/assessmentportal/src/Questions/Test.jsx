import React, { useState, useEffect } from "react";
import { useParams, Link, useNavigate } from "react-router-dom";
import axios from "axios";
import Swal from "sweetalert2";
import ErrorPage from "../ErrorPage";
import './Test.css';
import QuizApi from "../APIs/QuizApi";
import Questions from "./Questions";
import QuestionsApi from "../APIs/QuestionsApi";
import ResultApi from "../APIs/ResultApi";
import DisableBackButton from "../APIs/disableBackButton";
import Input from "../Inputs/Input";
import ButtonComponent from "../Inputs/ButtonComponent";

function Test({ isRefresh, setTrue }) {
  const navigate = useNavigate();
  const verifyRole = localStorage.getItem("userRole");
  const cat = localStorage.getItem("categoryId")
  const [questionCounter, setQuestionCounter] = useState(1);
  const [isLoading, setIsLoading] = useState(false);
  const [quiz, setQuiz] = useState([]);
  const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
  const [questions, setQuestions] = useState([]);
  const [userScore, setUserScore] = useState(0);
  const { quizId } = useParams();
  const [selectedOptions, setSelectedOptions] = useState([]);
  const [quizSubmitted, setQuizSubmitted] = useState(false);
  const [resultt, setResult] = useState("");
  const verifyEmail = localStorage.getItem('userEmail');
  const verifyName = localStorage.getItem('userName');
  const verifyCategory = localStorage.getItem('categoryName')
  const verifyQuizName = localStorage.getItem('quizName')
  const verifyUserId = localStorage.getItem('userId')
  const verifyTimer = localStorage.getItem('timer')
  const [numberOfQuestions, setNumberOfQuestions] = useState();
  const [attemptedQuestionss, setAttemptedQuestions] = useState(0);
  const [date, setDate] = useState()
  const [timeLeft, setTimeLeft] = useState((verifyTimer)*10);
  useEffect(() => {
    const currentDate = new Date();
    const options = {
      year: 'numeric',
      month: '2-digit',
      day: '2-digit',
      hour: '2-digit',
      minute: '2-digit',
      second: '2-digit',
      hour12: true
    };
    const formattedDate = currentDate.toLocaleDateString('en-US', options);
    setDate(formattedDate);
  }, [])
  useEffect(() => {
    getQuiz();
  }, [quizId])
  const getQuiz = async () => {
    QuizApi.getQuiz(quizId).then(response => {
      setQuiz(response.data.Quiz_topic_Information || []);
    }).catch(error => {
      console.error('An error occurred:', error);
    }).finally(() => {
      setIsLoading(false);
    })
  }
  useEffect(() => {
    getQuestions();

  }, [quizId]);

  const getQuestions = async () => {
    QuestionsApi.getQuestionByQuizId(quizId).then(response => {
      setQuestions(response.data.Questions_Information || []);
      setNumberOfQuestions(response.data.Questions_Information.length)

    }).catch(error => {
      console.error('An error occurred:', error);
    }).finally(() => {
      setIsLoading(false);
    })
  };
  useEffect(() => {
    setQuestionCounter(1);
  }, [questions]);

  const handleOptionChange = (questionId, selectedOption) => {

    if (currentQuestionIndex < numberOfQuestions) {
      const currentQuestion = questions[currentQuestionIndex];
      setCurrentQuestionIndex((currentQuestionIndex) => currentQuestionIndex + 1);

    }

    setSelectedOptions((prevSelectedOptions) => {
      const updatedOptions = [...prevSelectedOptions];
      updatedOptions[currentQuestionIndex] = selectedOption;
      localStorage.setItem(`selectedOption_${questionId}`, selectedOption);
      return updatedOptions;
    });

  };
  const getAtt = () => {
    let atte = 0;
    questions.forEach((item, index) => {
      if (selectedOptions[index] !== undefined) {
        atte++;
        index++
      }
    });
    setAttemptedQuestions(atte)
  };

  useEffect(() => {
    const timer = setTimeout(() => {
      if (timeLeft > 0) {
        setTimeLeft(timeLeft - 1);
        localStorage.setItem('timerValue', timeLeft - 1);
      } else {
        clearTimeout(timer);
        Swal.fire({
          title: 'Time Out',
          text: 'All answers are saved',
          icon: 'warning',
          confirmButtonText: 'Ok'
        })
        timeOutSubmit()
      }
    }, 1000);

    return () => clearTimeout(timer);
  }, [timeLeft]);
  useEffect(() => {
    const storedTimeLeft = localStorage.getItem('timerValue');
    const initialTimeLeft = storedTimeLeft ? parseInt(storedTimeLeft) :verifyTimer*60;
    setTimeLeft(initialTimeLeft);
  }, []);

  const formatTime = (seconds) => {
    const minutes = Math.floor(seconds / 60);
    const remainingSeconds = seconds % 60;
    return `${minutes}:${remainingSeconds < 10 ? "0" : ""}${remainingSeconds}`;
  };
  const timeOutSubmit = () => {
    if (!quizSubmitted) {
      // let score = 0;
      // questions.forEach((item, index) => {
      //   if (item.correctOption === selectedOptions[index]) {
      //     score = score + 1;
      //     index++;
      //   }
      // });
      const score=calculateScore()
      setUserScore(score);
      setQuizSubmitted(true);
      getAtt();
      removeSelectedOptionsInLocalStorage()
      localStorage.removeItem('timerValue');
      localStorage.removeItem('userScore')
    }
  }
  const calculateScore = () => {
    let score = 0;
    for (let i = 0; i < questions.length; i++) {
      const questionId = questions[i].questionId;
      const selectedOption = localStorage.getItem(`selectedOption_${questionId}`);
      const correctOption = questions[i].correctOption;
  
      // Check if the selected option matches the correct option
      if (selectedOption === correctOption) {       
        score++;       
      }
    }  
    localStorage.setItem('user', score);
    return score;
  };
  
  const handleSubmitQuiz = async () => {
    Swal.fire({
      title: 'Do you want to Submit the test??',
      showDenyButton: true,
      // showCancelButton: true,
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

        if (!quizSubmitted) {
          // let score = 0;

          // questions.forEach((item, index) => {
          //   if (item.correctOption === selectedOptions[index]) {
          //     score = score + 1;
          //     index++;
          //   }
          // });
          calculateScore()
          const s=localStorage.getItem("user");
          setUserScore(s);
          removeSelectedOptionsInLocalStorage()
          
          setQuizSubmitted(true);
          getAtt()
          localStorage.removeItem('timerValue');
          
        }
      }
    })

  };
  const countSelectedOptionsInLocalStorage = () => {
    let count = 0;
    for (let i = 0; i < localStorage.length; i++) {
      const key = localStorage.key(i);
      // Check if the key matches the naming convention
      if (key.startsWith("selectedOption_")) {
        count++;
        
      }
    }
    
    return count;
  };
  const removeSelectedOptionsInLocalStorage = () => {
    const keysToRemove = [];
    for (let i = 0; i < localStorage.length; i++) {
      const key = localStorage.key(i);
      // Check if the key matches the naming convention
      if (key.startsWith("selectedOption_")) {
        keysToRemove.push(key);
      }
    }
  
    // Remove all the selected options from localStorage
    keysToRemove.forEach((key) => {
      localStorage.removeItem(key);
    });
  
    return keysToRemove.length;
  };
  useEffect(() => {
    if (!isRefresh) {
      calculateScore()
    }
  })
  useEffect(() => {
    if (!isRefresh) {

      Swal.fire({
        title: 'If you refresh the page the test will be submitted',
        showDenyButton: true,
        // showCancelButton: true,
        confirmButtonText: 'Go ahead',
        denyButtonText: 'Cancel',
        customClass: {
          actions: 'my-actions',
          cancelButton: 'order-1 right-gap',
          confirmButton: 'order-2',
          denyButton: 'order-3',
        }
      }).then((result) => {
        if (result.isConfirmed) {
          //     const cat = localStorage.getItem("categoryId")
          // console.log(cat)
          // navigate(`/Quiz/${cat}`)
         
          localStorage.removeItem('timerValue');
          const a=countSelectedOptionsInLocalStorage()
          setAttemptedQuestions(a);
          const ss=localStorage.getItem('user')
          setUserScore(ss);
          setQuizSubmitted(true);
          removeSelectedOptionsInLocalStorage()
          localStorage.removeItem('user')
        } else if (result.isDenied) {

        }
      })
    }
  }, [isRefresh, setTrue])
  useEffect(() => {
    if (quizSubmitted) {
      postData();
    }
  }, [quizSubmitted, userScore]);
  const postData = async () => {
    const postDataa = {
      userId: verifyUserId,
      userName: verifyName,
      email: verifyEmail,
      categoryName: verifyCategory,
      quizName: verifyQuizName,
      maxMarks: numberOfQuestions,
      result: resultt,
      attemptedQuestions: attemptedQuestionss,
      totalQuestions: numberOfQuestions,
      obtainMarks: userScore,
      dateAndTime: date,
      categoryId: cat
    };
    ResultApi.addResult(postDataa).then(response => {
      navigate(`/Result/${verifyUserId}`)
    }).catch(error => {
      console.error('Error sending data to the server:', error);
    })
  };
  const backTo = () => {
    const categoryId = localStorage.getItem("categoryId")
    navigate(`/Quiz/${categoryId}`)
  }
  return (
    <div className="quiz-container">
      <DisableBackButton />
      {verifyRole === 'student' ? (
        <>
          {questions.length !== 0 ? (
            <>
              <div className="navbar">
                <h1 className="navbar-title">Test</h1>
                <div className="timer">
                  Time Left: {formatTime(timeLeft)}
                </div>
              </div>

              <div className="categoryData">

                <div>
                  <form className="testData">
                    {questions.map((item, index) => (
                      <div key={item.questionId} className="question-container">
                        <h4>{`${questionCounter + index}. ${item.question}`}</h4>
                        <div className="option-container">
                          <div className="option">
                            <Input
                              type="radio"
                              id={`option1_${item.questionId}`}
                              name={`question_${item.questionId}`}
                              value={item.option1}
                              onChange={() => handleOptionChange(item.questionId, item.option1)}
                              
                            />
                            <label htmlFor={`option1_${item.questionId}`}>{item.option1}</label>
                          </div>
                          <div className="option">
                            <Input
                              type="radio"
                              id={`option2_${item.questionId}`}
                              name={`question_${item.questionId}`}
                              value={item.option2}
                              onChange={() => handleOptionChange(item.questionId, item.option2)}
                            />
                            <label htmlFor={`option2_${item.questionId}`}>{item.option2}</label>
                          </div>
                          <div className="option">
                            <Input
                              type="radio"
                              id={`option3_${item.questionId}`}
                              name={`question_${item.questionId}`}
                              value={item.option3}
                              onChange={() => handleOptionChange(item.questionId, item.option3)}
                            />
                            <label htmlFor={`option3_${item.questionId}`}>{item.option3}</label>
                          </div>
                          <div className="option">
                            <Input
                              type="radio"
                              id={`option4_${item.questionId}`}
                              name={`question_${item.questionId}`}
                              value={item.option4}
                              onChange={() => handleOptionChange(item.questionId, item.option4)}
                            />
                            <label htmlFor={`option4_${item.questionId}`}>{item.option4}</label>
                          </div>
                        </div>
                      </div>
                    ))}
                  </form>
                  {!quizSubmitted && (
                    <ButtonComponent className="submitQuizButton" onClick={handleSubmitQuiz} type="button">
                      Submit Test
                    </ButtonComponent>
                  )}
                </div>
              </div>
            </>
          ) : (
            <div>
              <h1>No questions</h1>
              <ButtonComponent className="back" onClick={() => backTo()}>Back</ButtonComponent>
            </div>
          )}
        </>) :

        (<ErrorPage />)}
    </div>
  );
}


export default Test;