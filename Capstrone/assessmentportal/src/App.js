
import './App.css';
import { BrowserRouter,Routes,Route } from 'react-router-dom';
import Login from './UserRegistration/Login';
import UserDashBoard from './User/UserDashBoard';
import AdminDashBoard from './Admin/AdminDashBoard';
import Registration from './UserRegistration/Registration';
import HomePage from './HomePage/HomePage';
import Category from './Categories/Category';
function App() {
  return (
    <div className="App">
     <BrowserRouter>
       <Routes>
        <Route path="/" element={<HomePage/>}></Route>
        <Route path="/Login" element={<Login/>}></Route>
        <Route path="/UserDashBoard" element={<UserDashBoard/>}></Route>
        <Route path="/AdminDashBoard" element={<AdminDashBoard/>}></Route>
        <Route path="/Registration" element={<Registration/>}></Route>
        <Route path="/Category" element={<Category/>}></Route>
       </Routes>
     </BrowserRouter>
    </div>
  );
}

export default App;
