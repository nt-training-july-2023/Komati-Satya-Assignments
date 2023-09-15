
import React, { useState, useEffect } from "react";
import { useParams, Link, useNavigate } from "react-router-dom";
import axios from "axios";
import Swal from "sweetalert2";
import ErrorPage from "../ErrorPage";
import './Test.css';

function Test() {
  const navigate=useNavigate();
  const verifyRole = localStorage.getItem("userRole");
  const [questionCounter, setQuestionCounter] = useState(1);
  const [isLoading, setIsLoading] = useState(false);
  const [quiz,setQuiz]=useState([]);
  const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
  const [timeLeft, setTimeLeft] = useState(60*60);
  const [timerId, setTimerId] = useState(null);
  const [questions, setQuestions] = useState([]);
  const [userScore, setUserScore] = useState(0); // Initialize the user's score
  const { quizId } = useParams();
  const [selectedOptions,setSelectedOptions]=useState([]);
  const [quizSubmitted, setQuizSubmitted] = useState(false); 
  const [resultt,setResult]=useState("");
  const verifyEmail = localStorage.getItem('userEmail');
  // console.log(verifyEmail);
  const verifyName = localStorage.getItem('userName');
  // console.log(verifyName);
  const verifyCategory=localStorage.getItem('categoryName')
  // console.log(verifyCategory);
  const verifyQuizName=localStorage.getItem('quizName')
  // console.log(verifyQuizName)
  const verifyUserId=localStorage.getItem('userId')
  console.log(verifyUserId)
  const [numberOfQuestions,setNumberOfQuestions]=useState();
  const [attemptedQuestionss,setAttemptedQuestions]=useState(0);
  const [date,setDate]=useState()
 
  useEffect(() => {
    const currentDate = new Date();
    setDate(currentDate)
    
  },[])
   
//  console.log(date)
useEffect(()=>{
  getQuiz();
},[quizId])
const getQuiz=async()=>{
try {
  const response = await axios.get(`http://localhost:6002/quiz/${quizId}`);
  console.log(response)
  setQuiz(response.data.Quiz_topic_Information || []);
  console.log(quiz)
} catch (error) {
  console.error('An error occurred:', error);
} finally {
  setIsLoading(false);
}}
// console.log(quiz)

  useEffect(() => {
    getQuestions();
  
  }, [quizId]);
  
  const getQuestions = async () => {
    try {
      const response = await axios.get(`http://localhost:6002/questions/${quizId}`);
      setQuestions(response.data.Questions_Information || []);
      setNumberOfQuestions(response.data.Questions_Information.length)
     
    } catch (error) {
      console.error('An error occurred:', error);
    } finally {
      setIsLoading(false);
    }
  };
  useEffect(() => {
    setQuestionCounter(1);
  }, [questions]);
  
  const handleOptionChange = (questionId, selectedOption) => {
    
    if(currentQuestionIndex<numberOfQuestions){
    const currentQuestion = questions[currentQuestionIndex];
    setCurrentQuestionIndex((currentQuestionIndex)=>currentQuestionIndex+1);
   
  }
  
    setSelectedOptions((prevSelectedOptions) => {
      const updatedOptions = [...prevSelectedOptions];
      updatedOptions[currentQuestionIndex] = selectedOption;
      return updatedOptions;
    });
    
  };
  const getAtt = () => {
    let atte = 0;
    questions.forEach((item,index) => {
      if (selectedOptions[index] !== undefined) {
        atte++;
        index++
      }
    });
    setAttemptedQuestions(atte)
    console.log("Attempted Questions:", atte);
  };
  
console.log(attemptedQuestionss);

  
 console.log(userScore)
 
  useEffect(() => {
    const timer = setTimeout(() => {
      if (timeLeft > 0) {
        setTimeLeft(timeLeft - 1);
      } else {
        clearTimeout(timer);
         Swal.fire({
          title: 'Time Out',
          text: 'All answers are saved',
          icon: 'warning',
          confirmButtonText: 'Ok'
      })
      handleSubmitQuiz()
    }
    }, 1000);

    return () => clearTimeout(timer); 
  }, [timeLeft]);
 

  const formatTime = (seconds) => {
    const minutes = Math.floor(seconds / 60);
    const remainingSeconds = seconds % 60;
    return `${minutes}:${remainingSeconds < 10 ? "0" : ""}${remainingSeconds}`;
  };

  const handleSubmitQuiz = async () => {
    if (!quizSubmitted) {
          let score = 0;
          
       questions.forEach((item, index) => {
       console.log(item.correctOption)
        console.log(selectedOptions[index])
         if (item.correctOption === selectedOptions[index]) {
           score=score+1;
            index++;
           }
          });
  
      setUserScore(score);
      setQuizSubmitted(true);
      
      getAtt();
    }
  };
  
  useEffect(() => {
    if (quizSubmitted) {
      
      postData();
    }
  }, [quizSubmitted, userScore]);
  
  // const getResult = (score) => {
  //   const passMarks=numberOfQuestions-3;
  //   if (quiz.passMarks > score) {
  //     setResult("fail");
  //   } else {
  //     setResult("pass");
  //   }
  // };
  
  
  const postData = async () => {
    const postDataa = {
      userId: verifyUserId,
      userName: verifyName,
      email: verifyEmail,
      categoryName: verifyCategory,
      quizName: verifyQuizName,
      maxMarks: numberOfQuestions,
      // result: resultt,
      attemptedQuestions: attemptedQuestionss,
      totalQuestions: numberOfQuestions,
      obtainMarks: userScore,
      dateAndTime: date,
    };
  
    try {
      const response = await axios.post('http://localhost:6002/res', postDataa);
      console.log('POST response:', response.data);
      navigate(`/Result/${verifyUserId}`)
      // if(response.data.message==="succcessfully add the data"){

      //   Swal.fire({
      //     title: 'Result',
      //     text: 'Quiz submitted successfully and your score '`${postData.obtainMarks}`,
      //     icon: 'success',
      //     confirmButtonText: 'Ok'
      //   });
      //   navigate(`/Result/${verifyUserId}`)
      // }
    
      

    } catch (error) {
      console.error('Error sending data to the server:', error);
    }
  };
  
  console.log("a" + resultt);
  console.log("a" + attemptedQuestionss);
  console.log("a" + userScore);
 

  console.log(quiz.maxMarks)
  // console.log(userScore)

  console.log(resultt)

 

  return (
    <div className="quiz-container">
      <div className="navbar">
        <h1 className="navbar-title">Test</h1>
        <div className="timer">
          Time Left: {formatTime(timeLeft)}
        </div>
      </div>

      <div className="categoryData">
        {questions.length !== 0 ? (
          <>
            {verifyRole === 'student' && (
              <div>
                <form className="testData">
                  {questions.map((item, index) => (
                    <div key={item.questionId} className="question-container">
                      <h4>{`${questionCounter + index}. ${item.question}`}</h4>
                      <div className="option-container">
                        <div className="option">
                          <input
                            type="radio"
                            id={`option1_${item.questionId}`}
                            name={`question_${item.questionId}`}
                            value={item.option1}
                            onChange={() => handleOptionChange(item.questionId, item.option1)}
                          />
                          <label htmlFor={`option1_${item.questionId}`}>{item.option1}</label>
                        </div>
                        <div className="option">
                          <input
                            type="radio"
                            id={`option2_${item.questionId}`}
                            name={`question_${item.questionId}`}
                            value={item.option2}
                            onChange={() => handleOptionChange(item.questionId, item.option2)}
                          />
                          <label htmlFor={`option2_${item.questionId}`}>{item.option2}</label>
                        </div>
                        <div className="option">
                          <input
                            type="radio"
                            id={`option3_${item.questionId}`}
                            name={`question_${item.questionId}`}
                            value={item.option3}
                            onChange={() => handleOptionChange(item.questionId, item.option3)}
                          />
                          <label htmlFor={`option3_${item.questionId}`}>{item.option3}</label>
                        </div>
                        <div className="option">
                          <input
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
                  <button className="submitQuizButton" onClick={handleSubmitQuiz} type="button">
                    Submit Quiz
                  </button>
                )}
              </div>
            )}
          </>
        ) : (
          <h1>No questions</h1>
        )}
      </div>
    </div>
  );
          }

    
    export default Test;