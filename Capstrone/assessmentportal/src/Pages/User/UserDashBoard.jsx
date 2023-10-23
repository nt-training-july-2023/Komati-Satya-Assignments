import React from "react";
import './AdminStyles.css';
import { useNavigate} from "react-router-dom";
import { useEffect, useState } from "react";
import Navbar from "../../Components/Navbar/Navbar";
import ButtonComponent from "../../Components/Inputs/ButtonComponent";
import ErrorPage from "../../Components/ErrorPage";
import UserApi from "../../Service/UserApi";
import DisableBackButton from "../../Components/disableBackButton";
import H2Component from "../../Components/HeadingComponent/H2component";
import LabelComponent from "../../Components/LabelComponent/LabelComponent";
import userImage from "../../assets/images/Profile/userImage.png";

const UserDashBoard = () => {
  const navigate = useNavigate();
  const [student, setStudent] = useState([]);
  const verifyUserId = localStorage.getItem('userId');
  
  useEffect(() => {
    getStudent();
  }, []);

  const getStudent = async () => {
   if(verifyUserId){
    UserApi.getUserById(verifyUserId).then(response => {
      setStudent(response.data.data || []);
    })
  }
  };
  const verifyRole = localStorage.getItem('userRole');
  const UpdateData = () => {
    navigate(`/UserUpdate/${verifyUserId}`)
  }
  return (
    <div>
      {(verifyRole === 'Admin' || verifyRole === 'student') ?
        <>
      <Navbar />
      <DisableBackButton/>
      <div className="adminBackground">
        {verifyRole === 'student' && <>
          <H2Component>Welcome to User Dashboard</H2Component>
        </>}
        {verifyRole === 'Admin' && <>
          <H2Component>Welcome to Admin Dashboard</H2Component>
        </>}
        <div>
          <img className="image" src= {userImage} />
          {verifyRole === 'student' && <>
            <H2Component>User information</H2Component>
          </>}
          {verifyRole === 'Admin' && <>
            <H2Component>Admin information</H2Component>
          </>}
        </div>
        <div className="information">
          <div className="details">
            <div className="details-item">
              <div>
              <LabelComponent><strong>First Name:</strong></LabelComponent>  {student.firstName} 
              </div>
              </div>
              <div className="details-item">
              <div>
              <LabelComponent><strong>Last Name:</strong></LabelComponent>  {student.lastName} 
              </div>
              </div>
          
            <div className="details-item">
              <div>
              <LabelComponent><strong>UserId:</strong></LabelComponent>  {student.userId}  
              </div>
            </div>
            <div className="details-item">
              <div>
              <LabelComponent><strong>Email:</strong></LabelComponent>  {student.email}
              </div>
            </div>
            <div className="details-item">
              <div>
              <LabelComponent><strong>Gender:</strong></LabelComponent> {student.gender}
              </div>
            </div>
            <div className="details-item">
              <div>
              <LabelComponent><strong>PhoneNumber:</strong> </LabelComponent> {student.phoneNumber}
              </div>
            </div>
            <div className="details-item">
              <div>
              <LabelComponent><strong>Date Of Birth:</strong></LabelComponent>  {student.dateOfBirth}
              </div>
            </div>
          </div>
        </div>
        <ButtonComponent className="btn2" type="button" onClick={UpdateData}>Update Details</ButtonComponent>
      </div>
      </> : <ErrorPage/>}
    </div>
  )
}
export default UserDashBoard