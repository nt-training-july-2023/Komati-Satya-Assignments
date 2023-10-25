import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import SweetAlert from "../../Components/SweetAlertComponents/SweetAlert";
import './Test.css';
import QuizApi from "../../Service/QuizApi";
import QuestionsApi from "../../Service/QuestionsApi";
import ResultApi from "../../Service/ResultApi";
import DisableBackButton from "../../Components/disableBackButton";
import Input from "../../Components/Inputs/Input";
import ButtonComponent from "../../Components/Inputs/ButtonComponent";
import ErrorPage from "../../Components/ErrorPage";
import H1Component from "../../Components/HeadingComponent/H1component";
import LabelComponent from "../../Components/LabelComponent/LabelComponent";

function Test({ isRefresh, setTrue }) {
  const navigate = useNavigate();
  const verifyRole = localStorage.getItem("userRole");
  const categoryId = localStorage.getItem("categoryId")
  const [questionCounter, setQuestionCounter] = useState(1);
  const [isLoading, setIsLoading] = useState(false);
  const [quiz, setQuiz] = useState([]);
  const [questions, setQuestions] = useState([]);
  const [userScore, setUserScore] = useState(0);
  const { quizId } = useParams();
  const [selectedOptions, setSelectedOptions] = useState({})
  const [quizSubmitted, setQuizSubmitted] = useState(false);
  const verifyEmail = localStorage.getItem('userEmail');
  const verifyFirstName = localStorage.getItem('FirstName');
  const verifyLastName = localStorage.getItem('LastName');
  const verifyCategory = localStorage.getItem('categoryName')
  const verifyQuizName = localStorage.getItem('quizName')
  const verifyUserId = localStorage.getItem('userId')
  const verifyTimer = localStorage.getItem('timer')
  const [numberOfQuestions, setNumberOfQuestions] = useState();
  const [attemptedQuestionss, setAttemptedQuestions] = useState(0);
  const [date, setDate] = useState()
  const [timeLeft, setTimeLeft] = useState((verifyTimer) * 10);
  const [attempted, setAttempted] = useState(0);
  const [tab, setTab] = useState(false);

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
      setQuiz(response.data.data || []);
    }).finally(() => {
      setIsLoading(false);
    })
  }

  useEffect(() => {
    getQuestions();
  }, [quizId]);

  const getQuestions = async () => {
    QuestionsApi.getQuestionByQuizId(quizId).then(response => {
      setQuestions(response.data.data || []);
      localStorage.setItem('questions', JSON.stringify(response.data.data));
      if (response.data.data)
        setNumberOfQuestions(response.data.data.length)
        
    }).finally(() => {
      setIsLoading(false);
    })
  };

  useEffect(() => {
    setQuestionCounter(1);
  }, [questions]);
  useEffect(() => {
    const handleVisibilityChange = () => {
      if (document.hidden) {
        if (!quizSubmitted) {
          setTab(true)
          calculateScoreForTab()
          const s = localStorage.getItem("user");
          setUserScore(s);
          const a = countSelectedOptionsInLocalStorage()
          setAttemptedQuestions(a);
          setQuizSubmitted(true);
        }
      }
    };
    document.addEventListener('visibilitychange', handleVisibilityChange);
    return () => {
      document.removeEventListener('visibilitychange', handleVisibilityChange);
    };
  }, []);
  useEffect(() => {
    if (tab) {
      removeSelectedOptionsInLocalStorage()
      localStorage.removeItem('timerValue');
    }
  })
  const initializeSelectedOptions = () => {
    const initialSelectedOptions = {};
    for (let i = 0; i < questions.length; i++) {
      const questionId = questions[i].questionId;
      const storedOption = localStorage.getItem(`selectedOption_${questionId}`);
      if (storedOption) {
        initialSelectedOptions[questionId] = storedOption;
      }
    }
    setSelectedOptions(initialSelectedOptions);
  };
  const handleOptionChange = (questionId, selectedOption) => {
    setSelectedOptions((prevSelectedOptions) => {
      return {
        ...prevSelectedOptions,
        [questionId]: selectedOption,
      };
    });
    localStorage.setItem(`selectedOption_${questionId}`, selectedOption);
    setAttempted(countSelectedOptionsInLocalStorage());
  };
  useEffect(() => {
    initializeSelectedOptions();
  }, [questions]);

  useEffect(() => {
    const timer = setTimeout(() => {
      if (timeLeft > 0) {
        setTimeLeft(timeLeft - 1);
        localStorage.setItem('timerValue', timeLeft - 1);
      } else {
        clearTimeout(timer);
        SweetAlert.timeOut()
        timeOutSubmit()
      }
    }, 1000);
    return () => clearTimeout(timer);
  }, [timeLeft]);

  useEffect(() => {
    const storedTimeLeft = localStorage.getItem('timerValue');
    const initialTimeLeft = storedTimeLeft ? parseInt(storedTimeLeft) : verifyTimer * 60;
    setTimeLeft(initialTimeLeft);
  }, []);

  const formatTime = (seconds) => {
    const minutes = Math.floor(seconds / 60);
    const remainingSeconds = seconds % 60;
    return `${minutes}:${remainingSeconds < 10 ? `0${remainingSeconds}` : remainingSeconds}`;
  };

  const timeOutSubmit = () => {
    if (!quizSubmitted) {
      const score = calculateScore()
      setUserScore(score);
      const a = countSelectedOptionsInLocalStorage()
      setAttemptedQuestions(a);
      setQuizSubmitted(true);
      removeSelectedOptionsInLocalStorage()
      localStorage.removeItem('timerValue');
    }
  }

  const calculateScore = () => {
    let score = 0;
    for (let i = 0; i < questions.length; i++) {
      const questionId = questions[i].questionId;
      const selectedOption = localStorage.getItem(`selectedOption_${questionId}`);
      const correctOption = questions[i].correctOption;
      if (selectedOption === correctOption) {
        score++;
      }
    }
    localStorage.setItem('user', score);
    return score;
  };
  const calculateScoreForTab = () => {
    let score = 0;
    const questions = JSON.parse(localStorage.getItem('questions'));
    console.log(questions)
    for (let i = 0; i < questions.length; i++) {
      const questionId = questions[i].questionId;
       const selectedOption = localStorage.getItem(`selectedOption_${questionId}`);
      const correctOption = questions[i].correctOption;
      console.log(correctOption)
      console.log(selectedOption)
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
      confirmButtonText: 'Yes',
      denyButtonText: 'No',
      confirmButtonColor: '#5dcc5d',
      customClass: {
        actions: 'my-actions',
        cancelButton: 'order-1 right-gap',
        confirmButton: 'order-2 confirm',
        denyButton: 'order-3',
      }
    }).then((result) => {
      if (result.isConfirmed) {
        if (!quizSubmitted) {
          calculateScore()
          const s = localStorage.getItem("user");
          setUserScore(s);
          const a = countSelectedOptionsInLocalStorage()
          setAttemptedQuestions(a);
          removeSelectedOptionsInLocalStorage()
          setQuizSubmitted(true);
          localStorage.removeItem('timerValue');
        }
      }
    })
  };

  const countSelectedOptionsInLocalStorage = () => {
    let count = 0;
    for (let i = 0; i < localStorage.length; i++) {
      const key = localStorage.key(i);
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
      if (key.startsWith("selectedOption_")) {
        keysToRemove.push(key);
      }
    }
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
    if (verifyRole === "student") {
      if (!isRefresh) {
        const a = countSelectedOptionsInLocalStorage()
        setAttempted(a)
        Swal.fire({
          title: 'If you refresh the page the test will be submitted',
          showDenyButton: true,
          confirmButtonText: 'Go ahead',
          denyButtonText: 'Cancel',
          confirmButtonColor: '#5dcc5d',
          customClass: {
            actions: 'my-actions',
            cancelButton: 'order-1 right-gap',
            confirmButton: 'order-2',
            denyButton: 'order-3',
          }
        }).then((result) => {
          if (result.isConfirmed) {
            localStorage.removeItem('timerValue');
            const a = countSelectedOptionsInLocalStorage()
            setAttemptedQuestions(a);
            const ss = localStorage.getItem('user')
            setUserScore(ss);
            setQuizSubmitted(true);
            removeSelectedOptionsInLocalStorage()
            localStorage.removeItem('user')
          } else if (result.isDenied) {
          }
        })
      }
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
      firstName: verifyFirstName,
      lastName:verifyLastName,
      email: verifyEmail,
      categoryName: verifyCategory,
      quizName: verifyQuizName,
      maxMarks: numberOfQuestions,
      attemptedQuestions: attemptedQuestionss,
      totalQuestions: numberOfQuestions,
      obtainMarks: userScore,
      dateAndTime: date,
      categoryId: categoryId
    };
    ResultApi.addResult(postDataa).then(response => {
      navigate(`/Result/${verifyUserId}`)
    })
  };

  const backTo = () => {
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
                <H1Component className="navbar-title">Test</H1Component>
                <p className="attempted">Attempted Questions:{attempted + "/" + numberOfQuestions}</p>
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
                              checked={selectedOptions[item.questionId] === item.option1}
                            />
                            <LabelComponent htmlFor={`option1_${item.questionId}`}>{item.option1}</LabelComponent>
                          </div>
                          <div className="option">
                            <Input
                              type="radio"
                              id={`option2_${item.questionId}`}
                              name={`question_${item.questionId}`}
                              value={item.option2}
                              onChange={() => handleOptionChange(item.questionId, item.option2)}
                              checked={selectedOptions[item.questionId] === item.option2}
                            />
                            <LabelComponent htmlFor={`option2_${item.questionId}`}>{item.option2}</LabelComponent>
                          </div>
                          {item.option3 && item.option4 ? (
                            <>
                              <div className="option">
                                <Input
                                  type="radio"
                                  id={`option3_${item.questionId}`}
                                  name={`question_${item.questionId}`}
                                  value={item.option3}
                                  onChange={() => handleOptionChange(item.questionId, item.option3)}
                                  checked={selectedOptions[item.questionId] === item.option3}
                                />
                                <LabelComponent htmlFor={`option3_${item.questionId}`}>{item.option3}</LabelComponent>
                              </div>
                              <div className="option">
                                <Input
                                  type="radio"
                                  id={`option4_${item.questionId}`}
                                  name={`question_${item.questionId}`}
                                  value={item.option4}
                                  onChange={() => handleOptionChange(item.questionId, item.option4)}
                                  checked={selectedOptions[item.questionId] === item.option4}
                                />
                                <LabelComponent htmlFor={`option4_${item.questionId}`}>{item.option4}</LabelComponent>
                              </div>
                            </>
                          ) : null}
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
              <H1Component>No questions</H1Component>
              <ButtonComponent className="back" onClick={() => backTo()}>Back</ButtonComponent>
            </div>
          )}
        </>
      ) : (<ErrorPage />)}
    </div>
  );
}
export default Test;
