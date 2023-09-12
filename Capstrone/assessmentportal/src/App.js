
import './App.css';
import { BrowserRouter,Routes,Route } from 'react-router-dom';
import Login from './UserRegistration/Login';
import UserDashBoard from './User/UserDashBoard';

import Registration from './UserRegistration/Registration';
import Category from './Categories/Category';
import AddCategory from './Categories/AddCategory';

import UserUpdate from './User/UserUpdate';
import ErrorPage from './ErrorPage';
import Quiz from './Quiz/Quiz';
import AddQuiz from './Quiz/AddQuiz';
import Student from './User/Students';
import Result from './Result/Result';
import Questions from './Questions/Questions';
import AddQuestions from './Questions/AddQuestions';


function App() {
 
  return (
  
    <div className="App">
     <BrowserRouter>
       <Routes>
        {/* <Route path="/Home" element={<HomePage/>}></Route> */}
        <Route path="/" element={<Login/>}></Route>
        <Route path="/UserDashBoard" element={<UserDashBoard/>}></Route>
        {/* <Route path="/AdminDashBoard" element={<AdminDashBoard/>}></Route> */}
        <Route path="/Registration" element={<Registration/>}></Route>
        <Route path="/Category" element={<Category/>}></Route>
        <Route path="/AddCategory" element={<AddCategory/>}></Route>
        <Route path="/UpdateCategory/:categoryId" element={<AddCategory/>}></Route>
        <Route path="/UserUpdate/:userId" element={<UserUpdate/>}></Route>
        <Route path="/NotFoundPage" element={<ErrorPage/>}></Route>
        <Route path="/Quiz/:categoryId" element={<Quiz/>}></Route>
        <Route path="/Quiz" element={<Quiz/>}></Route>
        <Route path="/AddQuiz/:categoryId" element={<AddQuiz/>}></Route>
        <Route path="/Students" element={<Student/>}></Route>
        <Route path="/Result" element={<Result/>}></Route>
        <Route path="/Result/:userId" element={<Result/>}></Route>
        <Route path="/UpdateQuiz/:quizId" element={<AddQuiz/>}></Route>
        <Route path="/Questions/:quizId" element={<Questions/>}></Route>
        <Route path="/AddQuestion/:quizId" element={<AddQuestions/>}></Route>
        <Route path="/UpdateQuestion/:question" element={<AddQuestions/>}></Route>
       </Routes>
     </BrowserRouter>
    </div>
  
  );
}
export default App;
