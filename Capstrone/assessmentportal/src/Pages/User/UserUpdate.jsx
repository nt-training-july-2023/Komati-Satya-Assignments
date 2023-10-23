import './UserUpdate.css';
import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import UserApi from '../../Service/UserApi';
import SweetAlert from "../../Components/SweetAlertComponents/SweetAlert";
import Input from '../../Components/Inputs/Input';
import ButtonComponent from '../../Components/Inputs/ButtonComponent';
import ErrorPage from "../../Components/ErrorPage";
import LabelComponent from '../../Components/LabelComponent/LabelComponent';
import H1Component from '../../Components/HeadingComponent/H1component';

const UserUpdate = () => {
  const verifyRole = localStorage.getItem('userRole');
  const { userId } = useParams();
  const navigate = useNavigate();
  const [userData, setUserData] = useState({
    firstName: "",
    lastName: "",
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
        const userInformation = response.data.data;
        setUserData({
          firstName: userInformation.firstName,
          lastName: userInformation.lastName,
          phoneNumber: userInformation.phoneNumber,
          dateOfBirth: userInformation.dateOfBirth,
          gender: userInformation.gender,
          email: userInformation.email,
          userId: userInformation.userId
        });
      })

  }, [userId]);
  const handleUpdateCategory = () => {
    UserApi.updateUser(userId, userData)
      .then((response) => {
        if (response.data.message === "user updated successfully") {
          SweetAlert.success("Successfully updated data")
          navigate("/UserDashBoard")
        }
      }).catch((error) => {
        if (error.response.status === 400) {
          SweetAlert.fieldsRequired(error.response.data.message)
        }
      })
  };
  const cancelUpdate = () => {
    navigate('/UserDashBoard')
  }
  const genderOptions = ["male", "female", "other"];

  return (
    <div className="login2">
      {(verifyRole === 'Admin' || verifyRole === 'student') ?
        <>
          <div className="loginData2">
            <H1Component className="heading2">Update Details</H1Component>
            <form>
              <div className="signin2">
                <div>
                  <LabelComponent className="head2">User Id:</LabelComponent>
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
                  <LabelComponent className="head2">Email:</LabelComponent>
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
                  <LabelComponent className="head2">First Name:</LabelComponent>
                  <Input
                    className="data2"
                    type="text"
                    value={userData.firstName}
                    onChange={(e) =>
                      setUserData({
                        ...userData,
                        firstName: e.target.value,
                      })
                    }
                  />
                </div>
                <div>
                  <LabelComponent className="head2">Last Name:</LabelComponent>
                  <Input
                    className="data2"
                    type="text"
                    value={userData.lastName}
                    onChange={(e) =>
                      setUserData({
                        ...userData,
                        lastName: e.target.value,
                      })
                    }
                  />
                </div>
                <div>
                  <LabelComponent className="head2">Phone Number:</LabelComponent>
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
                  <LabelComponent className="head2">Date of Birth:</LabelComponent>
                  <Input
                    className="data2"
                    type="date"
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
                  <LabelComponent className="head2">Gender:</LabelComponent>
                  <select
                    className="data2"
                    value={userData.gender}
                    onChange={(e) =>
                      setUserData({
                        ...userData,
                        gender: e.target.value,
                      })
                    }
                  >
                    {genderOptions.map((option) => (
                      <option key={option} value={option}>
                        {option}
                      </option>
                    ))}
                  </select>

                </div>
                <ButtonComponent className="btn2" type="button" onClick={handleUpdateCategory}>
                  Update
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
