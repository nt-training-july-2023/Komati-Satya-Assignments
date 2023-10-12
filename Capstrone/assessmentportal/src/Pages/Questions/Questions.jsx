import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import ErrorPage from "../../Components/ErrorPage";
import QuestionsApi from "../../Service/QuestionsApi";
import Navbar from "../../Components/Navbar/Navbar";
import ButtonComponent from "../../Components/Inputs/ButtonComponent";
import SweetAlert from "../../Components/SweetAlertComponents/SweetAlert";
import H1Component from "../../Components/HeadingComponent/H1component";
import { FaBackward, FaPlusCircle } from "react-icons/fa";
import Table from "../../Components/TableComponent/Table";
import SearchButton from "../../Components/SearchButton/SearchButton";

const Questions = () => {
  const { quizId } = useParams();
  const verifyRole = localStorage.getItem('userRole');
  const categoryId = localStorage.getItem('categoryId')
  const [questions, setQuestions] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [text, setText] = useState("");
  const [originalQuestions, setOriginalQuestions] = useState([]);
  useEffect(() => {
    {
      getQuestions();
    }
  }, [quizId]);
  const getQuestions = async () => {
    QuestionsApi.getQuestionByQuizId(quizId).then(response => {
      setQuestions(response.data.data || []);
      setOriginalQuestions(response.data.data || []);
    }).catch((error)=>{
       console.error(error);
    })
    .finally(() => {
      setIsLoading(false);
    })
  };
  const deleteData = async (id) => {
    SweetAlert.deleteData("Question", deleteQuestion, id)
  };
  const deleteQuestion = (id) => {
    QuestionsApi.deleteQuestion(id).then(response => {
      if (response.data.message === "question deleted successfully") {
        SweetAlert.success("Question deleted successfully")
      }
      getQuestions();
    })
  }
  const navigate = useNavigate();
  const addData = () => {
    navigate(`/AddQuestion/${quizId}`);
  }
  const rows = [
    'question',
    'option1',
    'option2',
    'option3',
    'option4',
    'correctOption'
  ]
  const columns = [
    'Question',
    'Option1',
    'Option2',
    'Option3',
    'Option4',
    'Correct Option',

  ]
  const handleBackButton=()=>{
    navigate(`/Quiz/${categoryId}`)
  }
  return (
    <div className="categoryData">
      <Navbar />
      {(verifyRole === 'Admin' || verifyRole === 'student') ?
        <>
          <H1Component className="addHead">Questions Details</H1Component>
          <ButtonComponent className="back-Button" onClick={handleBackButton}><FaBackward className="back-icon" /> Back</ButtonComponent>
          {verifyRole === 'Admin' && <ButtonComponent className="addButton" onClick={() => addData()}><FaPlusCircle className="add-icon" /> Add Question</ButtonComponent>}
          <div className="searchContainer">
             <SearchButton
                originalData={originalQuestions}
                setItem={setQuestions}
                setText={setText}
                placeholder="serach by question"
                question={true}
              />
          </div>
          {isLoading ? (
            <p>Loading...</p>
          ) : (
            <div className="tableContainer">
              {questions.length !== 0 ? (
                <Table columns={columns} data={questions} rows={rows} question={true}
                  deleteData={deleteData}
                  role={verifyRole}
                  isTrue={true} />
              ) : (
                <H1Component className="no-questions">No Questions</H1Component>
              )}
            </div>
          )}
        </> : <ErrorPage />}
    </div>
  );
}
export default Questions