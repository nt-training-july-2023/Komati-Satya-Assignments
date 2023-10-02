import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import ErrorPage from "../../ErrorPage";
import QuizApi from "../../Service/QuizApi";
import Navbar from "../../Components/Navbar/Navbar";
import DisableBackButton from "../../Components/disableBackButton";
import Input from "../../Components/Inputs/Input";
import ButtonComponent from "../../Components/Inputs/ButtonComponent";
import Swal from "sweetalert2";
import SweetAlert from "../../Components/SweetAlertComponents/SweetAlert";
import H1Component from "../../Components/HeadingComponent/H1component";
import { FaTrash,FaPencilAlt ,FaList, FaPlusCircle} from "react-icons/fa";



function Quiz({ setTrue }) {
  const { categoryId } = useParams();
  const verifyRole = localStorage.getItem('userRole');
  const [quiz, setQuiz] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [text, setText] = useState("");
  const [originalQuiz, setOriginalQuiz] = useState([]);
  useEffect(() => {
    {
      getQuiz();
    }
  }, [categoryId]);
  const getQuiz = async () => {
    QuizApi.getQuizByCategoryId(categoryId).then(response => {
      setQuiz(response.data.data|| []);
      setOriginalQuiz(response.data.data || []);
    }).finally(() => {
      setIsLoading(false);
    })
  };
  const deleteData = async (id) => {
    SweetAlert.deleteData("quiz",deleteQuiz,id)
  };
  const deleteQuiz=(id)=>{
    QuizApi.deleteQuiz(id).then(response => {
      if (response.data.message === "quiz deleted successfully") {
        SweetAlert.success("Successfully deleted data")
      }
      getQuiz();
    }).
      catch(error => {
       
      })
  }
  const navigate = useNavigate();
  const addData = () => {
    navigate(`/AddQuiz/${categoryId}`);
  }
  const handleSearch = async () => {
    const filteredQuiz = quiz.filter(item =>
      (item.topicName || '').toLowerCase().includes((text || '').toLowerCase())
    );
    setQuiz(filteredQuiz);
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
          <H1Component className="addHead">Quiz Details</H1Component>
          {verifyRole === 'Admin' && <ButtonComponent className="addButton" onClick={() => addData()}><FaPlusCircle className="delete-icon"/> Add Quiz</ButtonComponent>}
          <div className="searchContainer">
            <Input
              className="search"
              type="text"
              placeholder="Search by Quiz Name"
              value={text}
              onChange={(e) => setText(e.target.value)}
            />
            <ButtonComponent className="searchButton" onClick={handleSearch}>Search</ButtonComponent>
            <ButtonComponent className="searchButton" onClick={clearSearch}>Clear Search</ButtonComponent>

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
                      {(verifyRole==="Admin" &&
                        <>
                        <th></th>
                        <th>Actions</th>
                        <th></th>
                        </>
                        )}
                        {(verifyRole==="student" &&
                        <>
                      
                        <th>Actions</th>
                        <th></th>
                        </>
                        )}
                    </tr>
                  </thead>
                  <tbody className="bodyData">
                    {quiz.map(item => (

                      <tr key={item.quizId}>
                        <td>{item.topicName}</td>
                        <td>{item.topicDescription}</td>
                        <td>{item.timer}</td>
                        {verifyRole === 'Admin' && <>
                          <td><ButtonComponent className="deleteData" type="button" onClick={() => deleteData(item.quizId)}><FaTrash className="delete-icon"/> Delete</ButtonComponent></td>
                          <td><Link to={`/UpdateQuiz/${item.quizId}`} className="updateData"><FaPencilAlt className="update-icon"/> Update</Link></td>
                          <td><Link to={`/Questions/${item.quizId}`} className="updateData"><FaList className="delete-icon"/> Questions</Link></td>
                        </>}

                        {verifyRole === 'student' && <>
                          <td><Link to="#" className="updateData" onClick={() => handleTakeTest(item.topicName, item.quizId, item.timer, item.categoryId)}>Take Test</Link></td></>}

                      </tr>
                    ))}
                  </tbody>
                </table>
              ) : (
                <H1Component className="no-questions">No Quiz</H1Component>
              )}
            </div>
          )}
        </> : <ErrorPage />}
    </div>
  );
}
export default Quiz