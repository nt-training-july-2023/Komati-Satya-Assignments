
import axios from "axios";
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import { useParams } from "react-router-dom";
import ErrorPage from "../ErrorPage";
function Quiz() {
  const { categoryId } = useParams();
  // console.log(categoryId)
  const verifyRole = localStorage.getItem('userRole');
  const [quiz, setQuiz] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [text, setText] = useState("");
  const [originalQuiz, setOriginalQuiz] = useState([]);
  useEffect(() => {
    {
      verifyRole === 'Admin' &&
      getQuizes();
    }
    {
      verifyRole === 'student' &&
      getQuiz();
    }
  }, [categoryId]);
  const getQuiz = async () => {
    try {
      const response = await axios.get(`http://localhost:6002/quizz/${categoryId}`);
      console.log(response)
      setQuiz(response.data.Quiz_Information || []);
      setOriginalQuiz(response.data.Quiz_Information || []);
    } catch (error) {
      console.error('An error occurred:', error);
    } finally {
      setIsLoading(false);
    }
  };
  const getQuizes = async () => {
    try {
      const response = await axios.get(`http://localhost:6002/quiz`);
      console.log(response)
      setQuiz(response.data.Quiz_Information || []);
      setOriginalQuiz(response.data.Quiz_Information || []);
    } catch (error) {
      console.error('An error occurred:', error);
    } finally {
      setIsLoading(false);
    }
  };
  // const deleteData = async (id) => {
  //   try {
  //     const response = await axios.delete(`http://localhost:6002/cat/${id}`);
  //     console.log(response);
  //     if (response.data.message === "succcessfully delete the data") {
  //       Swal.fire({
  //         title: 'Deleting data',
  //         text: 'Successfully deleted data',
  //         icon: 'success',
  //         confirmButtonText: 'Ok'
  //       });
  //     }
  //     getQuiz();
  //   }
  //   catch (error) {
  //     console.log(error);
  //   }
  // };
  const deleteData = () => {

  }
  const navigate = useNavigate();
  const addData = () => {
    navigate('/AddQuiz');
  }
  const handleSearch = async () => {
    console.log(text)
    const filteredQuiz = quiz.filter(item =>
      (item.topicName || '').toLowerCase().includes((text || '').toLowerCase())
    );
    setQuiz(filteredQuiz);
  };
  const clearSearch = () => {
    setQuiz(originalQuiz);
    setText("");
  }
  const backTo = () => {
    {
      verifyRole === 'Admin' &&
      navigate(-1);
    }
    {
      verifyRole === 'student' &&
      navigate(-1);
    }
  }
  return (
    <div className="categoryData">
      {(verifyRole === 'Admin' || verifyRole === 'student') ?
        <>
          <h1 className="addHead">Quiz Details</h1>
          <button className="addButton" onClick={() => backTo()}>Back</button>
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
              <table className="tableData">
                <thead className="headData">
                  <tr className="rowData">
                    <th>Quiz Name</th>
                    <th>Quiz Description</th>
                    <th>Maximum Marks</th>
                    <th>Pass Marks</th>
                    {/* <th>Delete</th>
              <th>Update</th> */}
                  </tr>
                </thead>
                <tbody className="bodyData">
                  {quiz.map(item => (

                    <tr key={item.quizId}>
                      <td>{item.topicName}</td>
                      <td>{item.topicDescription}</td>
                      <td>{item.maxMarks}</td>
                      <td>{item.passMarks}</td>
                      {verifyRole === 'Admin' && <>
                        <td><button className="deleteData" type="button" onClick={() => deleteData(item.quizId)}>Delete</button></td>
                        <td><Link to={`/UpdateQuiz/${item.categoryId}`} className="updateData">Update</Link></td></>}
                      {verifyRole === 'student' && <>
                        <td><Link to={`/Quiz/${item.categoryId}`} className="updateData">Take Test</Link></td></>}
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </> : <ErrorPage />}
    </div>
  );
}
export default Quiz