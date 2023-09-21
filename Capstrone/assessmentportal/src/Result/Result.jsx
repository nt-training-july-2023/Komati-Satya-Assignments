
import axios from "axios";
import React, { useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

import ErrorPage from "../ErrorPage";
import FinalResultApi from "../APIs/FinalResultApi";
import DisableBackButton from "../APIs/disableBackButton";
import Navbar from "../Navbar/Navbar";
const Result = () => {
  const verifyRole = localStorage.getItem('userRole');
  const [result, setResult] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [searchName, setSearchName] = useState("");
  const [originalResult, setOriginalResult] = useState([]);
  const { userId } = useParams();
  useEffect(() => {

    { verifyRole === 'Admin' ? getAllResultss() : getResultt() }

  }, []);
  const getAllResultss = () => {

    //const response = await axios.get('http://localhost:6002/finalResult');
    FinalResultApi.getAllResult().then(response => {
      console.log(response)
      if (response.data.message === "No results are there") {
        <h1>No results</h1>
      }
      setResult(response.data.Result_Information || []);
      setOriginalResult(response.data.Result_Information || []);
    }).catch(error => {
      console.error('An error occurred:', error);
    }).finally(() => {
      setIsLoading(false);
    })
  };
  const getResultt = () => {
    //const response = await axios.get(`http://localhost:6002/finalResult/${userId}`);
    FinalResultApi.getResultByStudentId(userId).then(response => {
      console.log(response)
      if (response.data.message == "No user is there") {
        <h1>No results</h1>
      }
      setResult(response.data.User_Information || []);
      setOriginalResult(response.data.User_Information || []);
    }).catch(error => {
      console.error('An error occurred:', error);
    }).finally(() => {
      setIsLoading(false);
    })
  };

  const navigate = useNavigate();

  const handleSearch = async () => {
    console.log(searchName)

    const filteredResult = result.filter(item =>
      (item.userName || '').toLowerCase().includes((searchName || '').toLowerCase())
    );
    console.log(filteredResult)
    setResult(filteredResult);
    console.log(result)
  };
  const clearSearch = () => {
    setResult(originalResult);
    setSearchName("");
  }
  const backTo = () => {
    {
      verifyRole === 'Admin' &&
        navigate('/UserDashBoard');
    }
    {
      verifyRole === 'student' &&
        navigate('/UserDashBoard');
    }
  }
  const sortedResults = [...result].sort((a, b) => new Date(b.dateAndTime) - new Date(a.dateAndTime));
  return (
    <div className="categoryData">
      <DisableBackButton />
      <Navbar />
      {(verifyRole === 'Admin' || verifyRole === 'student') ?
        <>
          <h1 className="addHead">Student Result Details</h1>
          {/* <button className="addButton" onClick={() => backTo()}>BackToDashBoard</button> */}

          <div className="searchContainer">
            <input
              className="search"
              type="text"
              placeholder="Search by user Name"
              value={searchName}
              onChange={(e) => setSearchName(e.target.value)}
            />
            <button className="searchButton" onClick={handleSearch}>Search</button>
            <button className="searchButton" onClick={clearSearch}>Clear Search</button>

          </div>
          {isLoading ? (
            <p>Loading...</p>
          ) : (
            <div className="tableContainer">
              {sortedResults.length !== 0 ? (
                <table className="tableData">
                  <thead className="headData">
                    <tr className="rowData">
                      <th>User Name</th>
                      <th>User Email</th>
                      <th>Date and Time</th>
                      <th>Category Name</th>
                      <th>Quiz Name</th>
                      <th>Total No Of Questions</th>
                      <th>AttemptedQuestions</th>
                      <th>Maximum Marks</th>
                      <th>Obtained Marks</th>
                    </tr>
                  </thead>
                  <tbody className="bodyData">
                    {sortedResults.map(item => (

                      <tr key={item.resultId}>
                        <td>{item.userName}</td>
                        <td>{item.email}</td>
                        <td>{item.dateAndTime}</td>
                        <td>{item.categoryName}</td>
                        <td>{item.quizName}</td>
                        <td>{item.totalQuestions}</td>
                        <td>{item.attemptedQuestions}</td>
                        <td>{item.maxMarks}</td>
                        <td>{item.obtainMarks}</td>


                      </tr>
                    ))}
                  </tbody>
                </table>
              ) : (
                <h1>No Result</h1>
              )}
            </div>
          )}
        </> : <ErrorPage />}
    </div>
  );
}




export default Result