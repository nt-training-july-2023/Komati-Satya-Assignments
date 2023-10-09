import React from "react";
import { useNavigate, useLocation } from "react-router-dom";
import ErrorPage from "../ErrorPage";
import DisableBackButton from "../disableBackButton";
import { FaChartBar, FaList, FaSignOutAlt, FaUser, FaUsers } from "react-icons/fa";
import SweetAlert from "../SweetAlertComponents/SweetAlert";

const Navbar = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const verifyUserId = localStorage.getItem('userId')
    const verifyRole = localStorage.getItem('userRole');
    const logoutPage = () => {
        SweetAlert.logout(navigate)
    }
    return (
        <div className="admin">
            <DisableBackButton />
            {(verifyRole === 'Admin' || verifyRole === 'student') ?
                <>
                    <ul className="nav-bar">
                        <li className="aa"><a className={location.pathname === '/UserDashBoard' ? 'active' : ''} href="/UserDashBoard">Profile <FaUser className="profile-icon" /></a></li>
                        <li className="aa"><a className={location.pathname === '/Category' ? 'active' : ''} href="/Category">Categories <FaList className="category-icon" /></a></li>
                        {verifyRole === 'Admin' && <>
                            <li className="aa"><a className={location.pathname === '/Students' ? 'active' : ''} href="/Students">Students <FaUsers className="users-icon" /></a></li>
                        </>}
                        {verifyRole === 'Admin' && <>
                            <li className="aa"><a className={location.pathname === '/Result' ? 'active' : ''} href="/Result">Result <FaChartBar className="users-icon" /></a></li>
                        </>}
                        {verifyRole === 'student' && <>
                            <li className="aa"><a className={location.pathname === `/Result/${verifyUserId}` ? 'active' : ''} href={`/Result/${verifyUserId}`}>Result <FaChartBar className="users-icon" /></a></li>
                        </>}
                        <li><a className="logout" href="#" onClick={logoutPage}>Logout <FaSignOutAlt className="logout-icon" /></a></li>
                    </ul>
                </> : <ErrorPage />}
        </div>
    )
}
export default Navbar;