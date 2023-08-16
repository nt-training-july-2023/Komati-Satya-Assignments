import React from 'react';
import './App.css';
//import { BrowserRouter,Routes,Route} from 'react-router-dom';
// import Home from "./Home.jsx";
// import Login from "./Login.jsx";
// import About from "./About.jsx";
// import Abc from "./abc.jsx";
//import Not from "./NotChange.jsx";
//import Parent from './ContextExamples/Parent';
//import Sample from './ContextExample2/Sample'
//import Red from './ReducerHook/reducer';
//import { GetApi } from './ReducerHook/ApiCall/GetEx';
//import { AxiosGet } from './ReducerHook/ApiCall/AxiosGet';
// import {AxiosPost} from './ReducerHook/ApiCall/AxiosPost';
// import Counter2 from './BasicExamples/Counter2';
// import Game from './Tic-Tac-Toe/Square';
// import {Crud} from './ApiCall/CrudUsingApi';
import { Employee } from './ApiCall/Employee';
function App() {
  return (
  <>
     {/* <GetApi/> */}
    {/* <Sample/> */}
    {/* <Red/> */}
    {/* <AxiosGet/> */}
    {/* <AxiosPost/> */}
     {/* <Counter2/> */}
      {/* <Game/> */}
     {/* <Crud/> */}
   {/* <Not/>*/}
<Employee/>
    {/* <BrowserRouter>
     <Abc/> 
       <Routes> 
        <Route exact path="/" element={<Home/>}/>
        <Route path="/login" element={<Login/>}/>
        <Route path="/about" element={<About/>}/>
      </Routes>
    </BrowserRouter> */}
    </> 
  );
}

export default App;
