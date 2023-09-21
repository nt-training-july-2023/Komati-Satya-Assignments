    
import axios from "axios";
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import { useParams } from "react-router-dom";
import ErrorPage from "../ErrorPage";
import QuestionsApi from "../APIs/QuestionsApi";
import Navbar from "../Navbar/Navbar";
const Questions=()=>{
  const { quizId } = useParams();
  // console.log(categoryId)
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
      //const response = await axios.get(`http://localhost:6002/questions/${quizId}`);
      QuestionsApi.getQuestionByQuizId(quizId).then(response=>{
      console.log(response)
      setQuestions(response.data.Questions_Information || []);
      setOriginalQuestions(response.data.Questions_Information || []);
    }).catch (error=>{
      console.error('An error occurred:', error);
    }).finally(()=> {
      setIsLoading(false);
    })
  };

  const deleteData = async (id) => {
    Swal.fire({
      title: 'Do you want to delete question?',
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
           // const response = await axios.delete(`http://localhost:6002/que/${id}`);
   QuestionsApi.deleteQuestion(id).then(response=>{
    console.log(response);
    if (response.data.message === "succcessfully delete the data") {
      Swal.fire({
        title: 'Deleting data',
        text: 'Successfully deleted data',
        icon: 'success',
        confirmButtonText: 'Ok'
      });
    }
    getQuestions();
  })
  .catch (error=>{
    console.log(error);
  })
      } else if (result.isDenied) {

      }
  })
  //  // const response = await axios.delete(`http://localhost:6002/que/${id}`);
  //  QuestionsApi.deleteQuestion(id).then(response=>{
  //     console.log(response);
  //     if (response.data.message === "succcessfully delete the data") {
  //       Swal.fire({
  //         title: 'Deleting data',
  //         text: 'Successfully deleted data',
  //         icon: 'success',
  //         confirmButtonText: 'Ok'
  //       });
  //     }
  //     getQuestions();
  //   })
  //   .catch (error=>{
  //     console.log(error);
  //   })
  };
 
  const navigate = useNavigate();
  const addData = () => {
     navigate(`/AddQuestion/${quizId}`);
  }
  const handleSearch = async () => {
    console.log(text)
    const filteredQuestions = questions.filter(item =>
      (item.question || '').toLowerCase().includes((text || '').toLowerCase())
    );
    setQuestions(filteredQuestions);
    // console.log(quiz)
  };
  const clearSearch = () => {
    setQuestions(originalQuestions);
    setText("");
  }
  const backTo = () => {
    {
      verifyRole === 'Admin' &&
      navigate('/UserDashBoard');
    }
   
  }
  return (
    <div className="categoryData">
      <Navbar/>
      {(verifyRole === 'Admin' || verifyRole === 'student') ?
        <>
          <h1 className="addHead">Questions Details</h1>
          {/* <button className="addButton" onClick={() => backTo()}>BackToDashBoard</button> */}
          {verifyRole === 'Admin' && <button className="addButton" onClick={() => addData()}>Add Question</button>}
          <div className="searchContainer">
            <input
              className="search"
              type="text"
              placeholder="Search by Question"
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
                    
                    {/* <th>Delete</th>
              <th>Update</th> */}
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
                        <td><button className="deleteData" type="button" onClick={() => deleteData(item.questionId)}>Delete</button></td>
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