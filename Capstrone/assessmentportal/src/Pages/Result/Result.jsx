import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import ErrorPage from "../../Components/ErrorPage";
import FinalResultApi from "../../Service/FinalResultApi";
import Navbar from "../../Components/Navbar/Navbar";
import DisableBackButton from "../../Components/disableBackButton";
import H1Component from "../../Components/HeadingComponent/H1component";
import Table from "../../Components/TableComponent/Table";
import SearchButton from "../../Components/SearchButton/SearchButton";

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
      setResult(response.data.data || []);
      setOriginalResult(response.data.data || []);
    }).finally(() => {
      setIsLoading(false);
    })
  };
  const getResultt = () => {
    FinalResultApi.getResultByStudentId(userId).then(response => {
      setResult(response.data.data || []);
      setOriginalResult(response.data.data || []);
    }).finally(() => {
      setIsLoading(false);
    })
  };

  const sortedResults = [...result].sort((a, b) => new Date(b.dateAndTime) - new Date(a.dateAndTime));
  const rows = [
    'userName',
    'email',
    'dateAndTime',
    'categoryName',
    'quizName',
    'totalQuestions',
    'attemptedQuestions',
    'maxMarks',
    'obtainMarks'
  ];
  const columns = [
    'User Name',
    'Email',
    'DateAndTime',
    'Category Name',
    'Quiz Name',
    'Total Questions',
    'Attempted Questions',
    'Max Marks',
    'Obtain Marks'
  ];

  return (
    <div className="categoryData">
      <DisableBackButton />
      <Navbar />
      {(verifyRole === 'Admin' || verifyRole === 'student') ?
        <>
          <H1Component className="addHead">Student Result Details</H1Component>
          {(verifyRole === 'Admin' && <>
            <div className="searchContainer">
              <SearchButton
                originalData={originalResult}
                setItem={setResult}
                setText={setSearchName}
                placeholder="serach by user email"
                result={true}
              />
            </div>
          </>)}
          {isLoading ? (
            <p>Loading...</p>
          ) : (
            <div className="tableContainer">
              {sortedResults.length !== 0 ? (
                <Table columns={columns} data={sortedResults} rows={rows} />
              ) : (
                <H1Component className="no-questions">No Result</H1Component>
              )}
            </div>
          )}
        </> : <ErrorPage />}
    </div>
  );
}
export default Result