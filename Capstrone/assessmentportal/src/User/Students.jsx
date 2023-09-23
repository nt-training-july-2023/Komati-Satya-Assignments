
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import UserApi from "../APIs/UserApi";
import Navbar from "../Navbar/Navbar";
import Input from "../Inputs/Input";
import ButtonComponent from "../Inputs/ButtonComponent";
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
    const filteredCategory = student.filter(item =>
      item.userName.toLowerCase().includes(searchText.toLowerCase())
    );
    setStudent(filteredCategory);
  };
  const clearSearch = () => {
    setStudent(originalStudent);
    setSearchText("");
  }
  
  return (
    <div className="categoryData">
      <Navbar />
      {verifyRole === 'Admin' &&
        <>
          <h1 className="addHead">Students Details</h1>
          <div className="searchContainer">
            <Input
              className="search"
              type="text"
              placeholder="Search by Student Name"
              value={searchText}
              onChange={(e) => setSearchText(e.target.value)}
            />
            <ButtonComponent className="searchButton" onClick={handleSearch}>Search</ButtonComponent>
            <ButtonComponent className="searchButton" onClick={clearSearch}>Clear Search</ButtonComponent>

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

