import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import ErrorPage from "../ErrorPage";
import FinalResultApi from "../APIs/FinalResultApi";
import DisableBackButton from "../APIs/disableBackButton";
import Navbar from "../Navbar/Navbar";
import Input from "../Inputs/Input";
import ButtonComponent from "../Inputs/ButtonComponent";
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
    FinalResultApi.getAllResult().then(response => {
      setResult(response.data || []);
      setOriginalResult(response.data || []);
    }).catch(error => {
      console.error('An error occurred:', error);
    }).finally(() => {
      setIsLoading(false);
    })
  };
  const getResultt = () => {
    FinalResultApi.getResultByStudentId(userId).then(response => {
      setResult(response.data || []);
      setOriginalResult(response.data || []);
    }).catch(error => {
     
    }).finally(() => {
      setIsLoading(false);
    })
  };
  const handleSearch = async () => {
    const filteredResult = result.filter(item =>
      (item.userName || '').toLowerCase().includes((searchName || '').toLowerCase())
    );
    setResult(filteredResult);
  };
  const clearSearch = () => {
    setResult(originalResult);
    setSearchName("");
  }
  const sortedResults = [...result].sort((a, b) => new Date(b.dateAndTime) - new Date(a.dateAndTime));
  return (
    <div className="categoryData">
      <DisableBackButton />
      <Navbar />
      {(verifyRole === 'Admin' || verifyRole === 'student') ?
        <>
        
          <h1 className="addHead">Student Result Details</h1>
          {(verifyRole === 'Admin' &&<>
          <div className="searchContainer">
            <Input
              className="search"
              type="text"
              placeholder="Search by user Name"
              value={searchName}
              onChange={(e) => setSearchName(e.target.value)}
            />
            <ButtonComponent className="searchButton" onClick={handleSearch}>Search</ButtonComponent>
            <ButtonComponent className="searchButton" onClick={clearSearch}>Clear Search</ButtonComponent>
        
          </div>
         
           </>)}
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