
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import { useParams } from "react-router-dom";
import ErrorPage from "../ErrorPage";
import QuizApi from "../APIs/QuizApi";
import DisableBackButton from "../APIs/disableBackButton";
import Navbar from "../Navbar/Navbar";
function Quiz({ setTrue }) {
  const { categoryId } = useParams();
  const verifyRole = localStorage.getItem('userRole');
  const [quiz, setQuiz] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [text, setText] = useState("");
  const [originalQuiz, setOriginalQuiz] = useState([]);
  const [disabled, setDisabled] = useState("false");
  const [quizData, setQuizData] = useState([]);
  const verifyUserId = localStorage.getItem('userId')
  useEffect(() => {
    {
      getQuiz();
    }
  }, [categoryId]);
  const getQuiz = async () => {
    QuizApi.getQuizByCategoryId(categoryId).then(response => {
      setQuiz(response.data.Quiz_Information || []);
      setOriginalQuiz(response.data.Quiz_Information || []);
    }).catch(error => {
      console.error('An error occurred:', error);
    }).finally(() => {
      setIsLoading(false);
    })
  };

  console.log(quizData)
  const deleteData = async (id) => {
    Swal.fire({
      title: 'Do you want to delete quiz?',
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
        QuizApi.deleteQuiz(id).then(response => {
          if (response.data.message === "succcessfully delete the data") {
            Swal.fire({
              title: 'Deleting data',
              text: 'Successfully deleted data',
              icon: 'success',
              confirmButtonText: 'Ok'
            });
          }
          getQuiz();
        }).
          catch(error => {
            console.log(error);
          })
      } else if (result.isDenied) {

      }
    })
  };

  const navigate = useNavigate();
  const addData = () => {
    navigate(`/AddQuiz/${categoryId}`);
  }
  const handleSearch = async () => {
    const filteredQuiz = quiz.filter(item =>
      (item.topicName || '').toLowerCase().includes((text || '').toLowerCase())
    );
    setQuiz(filteredQuiz);
    console.log(quiz)
  };
  const clearSearch = () => {
    setQuiz(originalQuiz);
    setText("");
  }
  const handleTakeTest = async (topicName, quizId, timer, categoryId) => {
    { localStorage.setItem('quizName', topicName) }
    { localStorage.setItem('timer', timer) }
    { localStorage.setItem('categoryId', categoryId) }
    Swal.fire({
      title: 'Instructions',
      background: '#f5f2f2',
      showDenyButton: true,
      confirmButtonText: 'Yes',
      denyButtonText: 'No',
      heightAuto: false,
      customClass: {
        actions: 'my-actions',
        cancelButton: 'order-1 right-gap',
        confirmButton: 'order-2',
        denyButton: 'order-3',
      },
      onOpen: (modalElement) => {
        modalElement.querySelector('.swal2-content').style.overflowY = 'auto';
      },
      html: `
      <div style="background-color: #f5f2f2;">
        <p>Here are the quiz instructions</p>
        <ul style="background-color:#f5f2f2">
          <li>1. Once you start the quiz you can't go to back.</li>
          <li>2. Do not refresh the page</li>
          <li>3. The quiz stopps once the timer runs out.</li>
          <li>4. No negative marking for wrong answers. </li>
          <li>5. Questions are of Multiple Choice</li>
          <li>6. Each Question carry one mark</li>
        </ul>
      </div>
    `
    }).then((result) => {
      if (result.isConfirmed) {
        setTrue();
        navigate(`/Test/${quizId}`);
      } else if (result.isDenied) {

      }
    });
  }
  return (
    <div className="categoryData">
      <Navbar />
      <DisableBackButton />
      {(verifyRole === 'Admin' || verifyRole === 'student') ?
        <>
          <h1 className="addHead">Quiz Details</h1>
          {verifyRole === 'Admin' && <button className="addButton" onClick={() => addData()}>Add Quiz</button>}
          <div className="searchContainer">
            <input
              className="search"
              type="text"
              placeholder="Search by Quiz Name"
              value={text}
              onChange={(e) => setText(e.target.value)}
            />
            <button className="searchButton" onClick={handleSearch}>Search</button>
            <button className="searchButton" onClick={clearSearch}>Clear Search</button>

          </div>
          {isLoading ? (
            <p>Loading...</p>
          ) : (
            <div className="tableContainer">
              {quiz.length !== 0 ? (
                <table className="tableData">
                  <thead className="headData">
                    <tr className="rowData">
                      <th>Quiz Name</th>
                      <th>Quiz Description</th>
                      <th>Time(in Min)</th>
                    </tr>
                  </thead>
                  <tbody className="bodyData">
                    {quiz.map(item => (

                      <tr key={item.quizId}>
                        <td>{item.topicName}</td>
                        <td>{item.topicDescription}</td>
                        <td>{item.timer}</td>
                        {verifyRole === 'Admin' && <>
                          <td><button className="deleteData" type="button" onClick={() => deleteData(item.quizId)}>Delete</button></td>
                          <td><Link to={`/UpdateQuiz/${item.quizId}`} className="updateData">Update</Link></td>
                          <td><Link to={`/Questions/${item.quizId}`} className="updateData">view Questions</Link></td>
                        </>}

                        {verifyRole === 'student' && <>
                          <td><Link to="#" className="updateData" onClick={() => handleTakeTest(item.topicName, item.quizId, item.timer, item.categoryId)}>Take Test</Link></td></>}

                      </tr>
                    ))}
                  </tbody>
                </table>
              ) : (
                <h1>No Quiz</h1>
              )}
            </div>
          )}
        </> : <ErrorPage />}
    </div>
  );
}
export default Quiz