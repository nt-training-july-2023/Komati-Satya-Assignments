import './UserUpdate.css';
import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import ErrorPage from "../ErrorPage";
import UserApi from '../APIs/UserApi';
import Input from '../Inputs/Input';
import ButtonComponent from '../Inputs/ButtonComponent';
import SweetAlert from '../SweetAlertComponents/SweetAlert';

const UserUpdate = () => {
  const verifyRole = localStorage.getItem('userRole');
  const { userId } = useParams();
  const navigate = useNavigate();
  const [sendData, setSendData] = useState(null);
  const [userData, setUserData] = useState({
    userName: "",
    phoneNumber: "",
    dateOfBirth: "",
    gender: "",
    email: "",
    userId: "",
    role: ""
  });

  useEffect(() => {
    UserApi.getUserById(userId)
      .then((response) => {
        setSendData(response);
        const userInformation = response.data;
        setUserData((previousData) => ({
          ...previousData,
          userName: userInformation.userName,
          phoneNumber: userInformation.phoneNumber,
          dateOfBirth: userInformation.dateOfBirth,
          gender: userInformation.gender,
          email: userInformation.email,
          userId: userInformation.userId
        }));
      })
      .catch((error) => {
        console.error("An error occurred:", error);
      });
  }, [userId]);
  const handleUpdateCategory = () => {
    UserApi.updateUser(userId, userData)
      .then((response) => {
        if (response.data === "user updated successfully") {
          SweetAlert.success("Successfully updated data")
            navigate("/UserDashBoard")
        }
      });
  };
  const cancelUpdate = () => {
    SweetAlert.cancel('Update', navigate, '/UserDashBoard')
  }
  
  return (
    <div className="login2">
      {(verifyRole === 'Admin' || verifyRole === 'student') ?
        <>
          <div className="loginData2">
            <h1 className="heading2">Update Details</h1>
            <form>
              <div className="signin2">
                <div>
                  <label className="head2">User Id:</label>
                  <Input
                    className="data2"
                    type="text"
                    value={userData.userId}
                    onChange={(e) =>
                      setUserData({
                        ...userData,
                        userId: e.target.value,
                      })
                    }
                    disabled
                  />
                </div>
                <div>
                  <label className="head2">Email:</label>
                  <Input
                    className="data2"
                    type="text"
                    value={userData.email}
                    onChange={(e) =>
                      setUserData({
                        ...userData,
                        email: e.target.value,
                      })
                    }
                    disabled
                  />
                </div>

                <div>
                  <label className="head2">User Name:</label>
                  <Input
                    className="data2"
                    type="text"
                    value={userData.userName}
                    onChange={(e) =>
                      setUserData({
                        ...userData,
                        userName: e.target.value,
                      })
                    }
                  />
                </div>
                <div>
                  <label className="head2">Phone Number:</label>
                  <Input
                    className="data2"
                    type="text"
                    value={userData.phoneNumber}
                    onChange={(e) =>
                      setUserData({
                        ...userData,
                        phoneNumber: e.target.value,
                      })
                    }
                  />
                </div>
                <div>
                  <label className="head2">Date of Birth:</label>
                  <Input
                    className="data2"
                    type="text"
                    value={userData.dateOfBirth}
                    onChange={(e) =>
                      setUserData({
                        ...userData,
                        dateOfBirth: e.target.value,
                      })
                    }
                  />
                </div>
                <div>
                  <label className="head2">Gender:</label>
                  <Input
                    className="data2"
                    type="text"
                    value={userData.gender}
                    onChange={(e) =>
                      setUserData({
                        ...userData,
                        gender: e.target.value,
                      })
                    }
                  />
                </div>

                <ButtonComponent className="btn2" type="button" onClick={handleUpdateCategory}>
                  Update Student Details
                </ButtonComponent>
                <ButtonComponent className="btn3" type="button" onClick={cancelUpdate}>
                  Cancel
                </ButtonComponent>
              </div>
            </form>
          </div>
        </> : <ErrorPage />}
    </div>
  );
};
export default UserUpdate;
