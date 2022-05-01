import React, {Component} from "react";
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import BankAccount from "./components/BankAccounts";
import Home from "./components/Home.js";
import './App.css';
import NavigationBar from "./components/NavigationBar";
import Category from "./Category";



function App(){
  return(
  <>

  <NavigationBar/>

  <div className="container-fluid text-center">
    <div className="row content">
      <div className="col-sm-2 sidenav">
        <p><a href="#">Link</a></p>
        <p><a href="#">Link</a></p>
        <p><a href="#">Link</a></p>
      </div>
      <div className="col-sm-8 text-left">
        <h1>Welcome to Flint Banking</h1>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
        <hr></hr>
        <h3>Test</h3>
        <p>Lorem ipsum...</p>
      </div>
      <div className="col-sm-2 sidenav">
        <div className="well">
          <p>ADS</p>
        </div>
        <div className="well">
          <p>ADS</p>
        </div>
      </div>
    </div>
  </div>

  <footer className="container-fluid text-center">
    <p>Copyright 2022 Flint Banking</p>
  </footer>



    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Home/>}/>
        <Route path='/bankaccount' element={<BankAccount />}/>
        <Route path='/category' element={<Category />}/>
      </Routes>
    </BrowserRouter>
</>
  );
}

export default App;
