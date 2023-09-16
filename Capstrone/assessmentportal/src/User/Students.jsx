
import axios from "axios";
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
// import '../CategoryStyles.css'
import ErrorPage from "../ErrorPage";
import UserApi from "../APIs/UserApi";
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

    // const response = await axios.get('http://localhost:6002/students');
    UserApi.getAllStudents().then(response => {
      setStudent(response.data.User_Information || []);
      setOriginalStudent(response.data.User_Information || []);

    }).catch(error => {
      console.error('An error occurred:', error);
    }).finally(() => {
      setIsLoading(false);
    })
  };

  const navigate = useNavigate();

  const handleSearch = async () => {
    console.log(searchText)
    const filteredCategory = student.filter(item =>
      item.userName.toLowerCase().includes(searchText.toLowerCase())
    );
    setStudent(filteredCategory);
  };
  const clearSearch = () => {
    setStudent(originalStudent);
    setSearchText("");
  }
  const backTo = () => {
    {
      verifyRole === 'Admin' &&
        navigate('/UserDashBoard');
    }

  }
  return (
    <div className="categoryData">
      {verifyRole === 'Admin' &&
        <>
          <h1 className="addHead">Students Details</h1>
          <button className="addButton" onClick={() => backTo()}>BackToDashBoard</button>

          <div className="searchContainer">
            <input
              className="search"
              type="text"
              placeholder="Search by Student Name"
              value={searchText}
              onChange={(e) => setSearchText(e.target.value)}
            />
            <button className="searchButton" onClick={handleSearch}>Search</button>
            <button className="searchButton" onClick={clearSearch}>Clear Search</button>

          </div>
          {isLoading ? (
            <p>Loading...</p>
          ) : (
            <div className="tableContainer">
               {student.length !== 0 ? (
              <table className="tableData">
                <thead className="headData">
                  <tr className="rowData">
                    <th>Student Name</th>
                    <th>User Id</th>
                    <th>Email</th>
                    <th>Gender</th>
                    <th>Phone Number</th>
                    <th>Date of Birth</th>
                    {/* <th>Delete</th>
              <th>Update</th> */}
                  </tr>
                </thead>
                <tbody className="bodyData">
                  {student
                    .filter(item => item.role === 'student')
                    .map(item => (
                      <tr key={item.userId}>
                        <td>{item.userName}</td>
                        <td>{item.userId}</td>
                        <td>{item.email}</td>
                        <td>{item.gender}</td>
                        <td>{item.phoneNumber}</td>
                        <td>{item.dateOfBirth}</td>
                      </tr>
                    ))}
                </tbody>
              </table>
               ) : (
                <h1>No Students</h1>
              )}
            </div>
          )}
        </>}
    </div>
  );
}

export default Student;

