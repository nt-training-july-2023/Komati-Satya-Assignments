import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import ErrorPage from "../../ErrorPage";
import QuestionsApi from "../../APIs/QuestionsApi";
import Navbar from "../../Components/Navbar/Navbar";
import ButtonComponent from "../../Components/Inputs/ButtonComponent";
import Input from "../../Components/Inputs/Input";
import SweetAlert from "../../Components/SweetAlertComponents/SweetAlert";

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
 
  return (
    <div className="categoryData">
      <Navbar />
      {(verifyRole === 'Admin' || verifyRole === 'student') ?
        <>
          <h1 className="addHead">Questions Details</h1>
          {verifyRole === 'Admin' && <ButtonComponent className="addButton" onClick={() => addData()}>Add Question</ButtonComponent>}
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
                <table className="tableData">
                  <thead className="headData">
                    <tr className="rowData">
                      <th>Question</th>
                      <th>Option1</th>
                      <th>Option2</th>
                      <th>Option3</th>
                      <th>Option4</th>
                      <th>Correct Answer</th>
                        <th>Actions</th>
                        <th></th>
                      
                    </tr>
                  </thead>
                  <tbody className="bodyData">
                    {questions.map(item => (

                      <tr key={item.questionId}>
                        <td>{item.question}</td>
                        <td>{item.option1}</td>
                        <td>{item.option2}</td>
                        <td>{item.option3}</td>
                        <td>{item.option4}</td>
                        <td>{item.correctOption}</td>

                        {verifyRole === 'Admin' && <>
                          <td><ButtonComponent className="deleteData" type="button" onClick={() => deleteData(item.questionId)}>Delete</ButtonComponent></td>
                          <td><Link to={`/UpdateQuestion/${item.question}`} className="updateData">Update</Link></td>

                        </>}

                      </tr>
                    ))}
                  </tbody>
                </table>
              ) : (
                <h1>No Questions</h1>
              )}
            </div>
          )}
        </> : <ErrorPage />}
    </div>
  );
}


export default Questions