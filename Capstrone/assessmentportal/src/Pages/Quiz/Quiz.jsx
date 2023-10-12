import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import ErrorPage from "../../Components/ErrorPage";
import QuizApi from "../../Service/QuizApi";
import Navbar from "../../Components/Navbar/Navbar";
import DisableBackButton from "../../Components/disableBackButton";
import ButtonComponent from "../../Components/Inputs/ButtonComponent";
import Swal from "sweetalert2";
import SweetAlert from "../../Components/SweetAlertComponents/SweetAlert";
import H1Component from "../../Components/HeadingComponent/H1component";
import { FaBackward, FaPlusCircle} from "react-icons/fa";
import Table from "../../Components/TableComponent/Table";
import SearchButton from "../../Components/SearchButton/SearchButton";

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
    }).catch((error)=>{
      console.error(error)
    })
    .finally(() => {
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
    }).catch(error => {
       console.error(error);
      })
  }
  const navigate = useNavigate();
  const addData = () => {
    navigate(`/AddQuiz/${categoryId}`);
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
      confirmButtonColor: '#5dcc5d',
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
  const rows=[
    'topicName',
     'topicDescription',
    'timer'
  ]
  const columns=[
    'Quiz Name',
     'Quiz Description',
    'Time(Min)'
  ]
  const handleBackButton=()=>{
    navigate('/Category')
  }
  return (
    <div className="categoryData">
      <Navbar />
      <DisableBackButton />
      {(verifyRole === 'Admin' || verifyRole === 'student') ?
        <>
          <H1Component className="addHead">Quiz Details</H1Component>
          <ButtonComponent className="back-Button" onClick={handleBackButton}><FaBackward className="back-icon" /> Back</ButtonComponent>
          {verifyRole === 'Admin' && <ButtonComponent className="addButton" onClick={() => addData()}><FaPlusCircle className="delete-icon"/> Add Quiz</ButtonComponent>}
          
          <div className="searchContainer">
            <SearchButton
                originalData={originalQuiz}
                setItem={setQuiz}
                setText={setText}
                placeholder="serach by quiz name"
                quiz={true}
              />
          </div>
          {isLoading ? (
            <p>Loading...</p>
          ) : (
            <div className="tableContainer">
              {quiz.length !== 0 ? (
                <Table columns={columns} data={quiz} rows={rows} quiz={true}
                deleteData ={deleteData}
                viewQuizes={handleTakeTest}
                role={verifyRole}
                isTrue={true}/>
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