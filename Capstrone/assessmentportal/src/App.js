import './App.css';
import { BrowserRouter,Routes,Route } from 'react-router-dom';
import UserDashBoard from '../src/Pages/User/UserDashBoard';
import Registration from  '../src/Pages/UserRegistration/Registration';
import UserUpdate from './Pages/User/UserUpdate';
import Category from './Pages/Categories/Category';
import AddCategory from '../src/Pages/Categories/AddCategory';
import Quiz from './Pages/Quiz/Quiz';
import { useState } from 'react';
import Result from './Pages/Result/Result';
import AddQuiz from './Pages/Quiz/AddQuiz';
import Questions from './Pages/Questions/Questions';
import AddQuestions from './Pages/Questions/AddQuestions';
import Login from './Pages/UserRegistration/Login';
import Student from './Pages/User/Students';
import Test from './Pages/Questions/Test';
import ErrorPage from './Components/ErrorPage';
import AddAssertionQuestion from './Pages/Questions/AddAssertionQuestion';

function App() {
 const[isRefresh,setIsRefresh]=useState(false)
 function setTrue() {
  setIsRefresh(true);
 }
  return (
  
    <div className="App">
     <BrowserRouter>
       <Routes>
        <Route path="/" element={<Login/>}></Route>
        <Route path="/UserDashBoard" element={<UserDashBoard/>}></Route>
        <Route path="/Registration" element={<Registration/>}></Route>
        <Route path="/Category" element={<Category/>}></Route>
        <Route path="/AddCategory" element={<AddCategory/>}></Route>
        <Route path="/UpdateCategory/:categoryId" element={<AddCategory/>}></Route>
        <Route path="/UserUpdate/:userId" element={<UserUpdate/>}></Route>
        <Route path="/NotFoundPage" element={<ErrorPage/>}></Route>
        <Route path="/Quiz/:categoryId" element={<Quiz setTrue={setTrue}/>}></Route>
        <Route path="/Quiz" element={<Quiz />}></Route>
        <Route path="/AddQuiz/:categoryId" element={<AddQuiz/>}></Route>
        <Route path="/Students" element={<Student/>}></Route>
        <Route path="/Result" element={<Result/>}></Route>
        <Route path="/Result/:userId" element={<Result/>}></Route>
        <Route path="/UpdateQuiz/:quizId" element={<AddQuiz/>}></Route>
        <Route path="/Questions/:quizId" element={<Questions/>}></Route>
        <Route path="/AddQuestion/:quizId" element={<AddQuestions/>}></Route>
        <Route path="/UpdateQuestion/:question" element={<AddQuestions/>}></Route>
        <Route path="/Test/:quizId" element={<Test isRefresh={isRefresh} setTrue={setTrue}/>}></Route>
        <Route path="/AddAssertionQuestion/:quizId" element = {<AddAssertionQuestion/>}></Route>
        <Route path="/UpdateAssertionQuestion/:question" element={<AddAssertionQuestion/>}></Route>
       </Routes>
     </BrowserRouter>
    </div>
  
  );
}
export default App;
