
import axios from "axios";
import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

const UserUpdate = () => {
  const { userId } = useParams();
  const navigate = useNavigate();
  
  const [userData, setUserData] = useState({
    userName: "",
    phoneNumber: "",
    dateOfBirth: "",
    gender: "",
    email:"",
    userId:"",
    role:""
  });

  useEffect(() => {
    
    axios
      .get(`http://localhost:6002/student/${userId}`) 
      .then((response) => {
        console.log(response)
        const userInformation = response.data.User_Information;
        console.log("User Information:", userInformation);
        const { userName, phoneNumber, dateOfBirth, gender,email,userId,role } = userInformation;
        setUserData((previousData)=>({
            ...previousData,
            userName: userInformation.userName,
            phoneNumber: userInformation.phoneNumber,
            dateOfBirth: userInformation.dateOfBirth,
            gender: userInformation.gender,
            email: userInformation.email,
            userId: userInformation.userId
        }));
        console.log(userData);
      })
      .catch((error) => {
        console.error("An error occurred:", error);
      });
  }, [userId]);
  const handleUpdateCategory = () => {
    axios
      .put(`http://localhost:6002/student/${userId}`, userData)
      .then((response) => {
        if (response.data.message === "successfully update the data") {
          Swal.fire({
            title: "Updating data",
            text: "Successfully updated data",
            icon: "success",
            confirmButtonText: "Ok",
          });
          navigate(`/UserDashBoard?data=${JSON.stringify(response.data)}`);
        }
      });
  };

  const cancelUpdate = () => {
    Swal.fire({
      title: "Do you want to cancel the changes?",
      showDenyButton: true,
      confirmButtonText: "Yes",
      denyButtonText: "No",
      customClass: {
        actions: "my-actions",
        cancelButton: "order-1 right-gap",
        confirmButton: "order-2",
        denyButton: "order-3",
      },
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire("Changes are not saved", "", "info");
        navigate(`/UserDashBoard?data=${JSON.stringify(userData)}`);
      }
    });
  };

  return (
    <div className="login2">
      <div className="loginData2">
        <h1 className="heading2">Update Student Details</h1>
        <form>
          <div className="signin2">
          <div>
              <label className="head2">User Id:</label>
              <input
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
              <input
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
              <input
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
              <input
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
              <input
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
              <input
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
            
            <button className="btn2" type="button" onClick={handleUpdateCategory}>
              Update Student Details
            </button>
            <button className="btn3" type="button" onClick={cancelUpdate}>
              Cancel
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default UserUpdate;
