import axios from "axios";
import React, { useEffect, useState } from "react";

const Demo = () => {
  const [data, setData] = useState([]);
  const verifyUserId = localStorage.getItem('userId');

  useEffect(() => {
    getStudent();
  }, []);

  const getStudent = async () => {
    try {
      const response = await axios.get(`http://localhost:6002/student/${verifyUserId}`);
      console.log(response.data);
      console.log(response.data.User_Information)
      setData(response.data.User_Information || []);
      console.log(data)
    } catch (error) {
      console.error('An error occurred:', error);
    }
  };

  return (
    <>
    <h1>{data.email}</h1>
      <h1>Student Data</h1>
      {Array.isArray(data) && data.length > 0 ? (
        data.map((student, index) => (
          <p key={index}>
            <strong>Name:</strong> {student.userName}, <strong>Email:</strong> {student.email}
          </p>
        ))
      ) : (
        <p>No data available</p>
      )}
    </>
  );
};

export default Demo;
