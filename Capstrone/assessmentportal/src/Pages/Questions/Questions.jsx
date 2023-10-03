import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import ErrorPage from "../../ErrorPage";
import QuestionsApi from "../../Service/QuestionsApi";
import Navbar from "../../Components/Navbar/Navbar";
import ButtonComponent from "../../Components/Inputs/ButtonComponent";
import Input from "../../Components/Inputs/Input";
import SweetAlert from "../../Components/SweetAlertComponents/SweetAlert";
import H1Component from "../../Components/HeadingComponent/H1component";
import { FaPlusCircle } from "react-icons/fa";
import Table from "../../Components/TableComponent/Table";
const Questions = () => {
  const { quizId } = useParams();
  const verifyRole = localStorage.getItem('userRole');
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
      setQuestions(response.data.data|| []);
      setOriginalQuestions(response.data.data || []);
    }).finally(() => {
      setIsLoading(false);
    })
  };

  const deleteData = async (id) => {
    SweetAlert.deleteData("Question",deleteQuestion,id)
  };
  const deleteQuestion=(id)=>{
    QuestionsApi.deleteQuestion(id).then(response => {
      if (response.data.message === "question deleted successfully") {
        SweetAlert.success("Question deleted successfully")
      }
      getQuestions();
    })
      .catch(error => {
       
        getQuestions();
      })
  }
  const navigate = useNavigate();
  const addData = () => {
    navigate(`/AddQuestion/${quizId}`);
  }
  const handleSearch = async () => {
    const filteredQuestions = questions.filter(item =>
      (item.question || '').toLowerCase().includes((text || '').toLowerCase())
    );
    setQuestions(filteredQuestions);
  };
  const clearSearch = () => {
    setQuestions(originalQuestions);
    setText("");
  }
  const rows =[
    'question',
    'option1',
    'option2',
    'option3',
    'option4',
    'correctOption'
  ]
  const columns =[
    'Question',
    'Option1',
    'Option2',
    'Option3',
    'Option4',
    'Correct Option',
    
  ]
  return (
    <div className="categoryData">
      <Navbar />
      {(verifyRole === 'Admin' || verifyRole === 'student') ?
        <>
          <H1Component className="addHead">Questions Details</H1Component>
          {verifyRole === 'Admin' && <ButtonComponent className="addButton" onClick={() => addData()}><FaPlusCircle className="add-icon"/> Add Question</ButtonComponent>}
          <div className="searchContainer">
            <Input
              className="search"
              type="text"
              placeholder="Search by Question"
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
              {questions.length !== 0 ? (
              <Table columns={columns} data={questions} rows={rows} question={true}
                deleteData ={deleteData}
                role={verifyRole}
                isTrue={true}/>
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