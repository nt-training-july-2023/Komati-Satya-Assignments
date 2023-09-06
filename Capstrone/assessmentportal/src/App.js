
import './App.css';
import { BrowserRouter,Routes,Route } from 'react-router-dom';
import Login from './UserRegistration/Login';
import UserDashBoard from './User/UserDashBoard';
import AdminDashBoard from './Admin/AdminDashBoard';
import Registration from './UserRegistration/Registration';
import Category from './Categories/Category';
import AddCategory from './Categories/AddCategory';
import UpdateCategory from './Categories/UpdateCategory';

function App() {
 
  return (
  
    <div className="App">
     <BrowserRouter>
       <Routes>
        {/* <Route path="/" element={<HomePage/>}></Route> */}
        <Route path="/" element={<Login/>}></Route>
        <Route path="/UserDashBoard" element={<UserDashBoard/>}></Route>
        <Route path="/AdminDashBoard" element={<AdminDashBoard/>}></Route>
        <Route path="/Registration" element={<Registration/>}></Route>
        <Route path="/Category" element={<Category/>}></Route>
        <Route path="/AddCategory" element={<AddCategory/>}></Route>
        <Route path="/UpdateCategory/:categoryId" element={<UpdateCategory/>}></Route>
       </Routes>
     </BrowserRouter>
    </div>
  
  );
}

export default App;
