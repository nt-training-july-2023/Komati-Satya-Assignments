import React, { useState, useEffect } from "react";
import UserApi from "../../Service/UserApi";
import Navbar from "../../Components/Navbar/Navbar";
import H1Component from "../../Components/HeadingComponent/H1component";
import Table from "../../Components/TableComponent/Table";
import ErrorPage from "../../Components/ErrorPage";
import SearchButton from "../../Components/SearchButton/SearchButton";

function Student() {
  const verifyRole = localStorage.getItem('userRole');
  const [student, setStudent] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [searchText, setSearchText] = useState("");
  const [originalStudent, setOriginalStudent] = useState([]);
  useEffect(() => {
    getStudents();
  }, []);
  const getStudents = async () => {
    UserApi.getAllStudents().then(response => {
      setStudent(response.data.data || []);
      setOriginalStudent(response.data.data || []);
    }).catch(error => {
      console.error(error);
    }).finally(() => {
      setIsLoading(false);
    })
  };

  const rows = [
    'userName',
    'userId',
    'email',
    'gender',
    'phoneNumber',
    'dateOfBirth'
  ]
  const columns = [
    'User Name',
    'User Id',
    'Email',
    'Gender',
    'Phone Number',
    'Date Of Birth'
  ]

  return (
    <div className="categoryData">
      <Navbar />
      {verifyRole === 'Admin' ?
        (<>
          <H1Component className="addHead">Students Details</H1Component>
          <div className="searchContainer">
            <SearchButton
              originalData={originalStudent}
              setItem={setStudent}
              setText={setSearchText}
              placeholder="serach by user name"
              student={true}
            />
          </div>
          {isLoading ? (
            <p>Loading...</p>
          ) : (
            <div className="tableContainer">
              {student.length !== 0 ? (
                <Table columns={columns} data={student
                  .filter(item => item.role === 'student')} rows={rows} />
              ) : (
                <H1Component className="no-questions">No Students</H1Component>
              )}
            </div>
          )}
        </>) : (<ErrorPage />)}
    </div>
  );
}
export default Student;

