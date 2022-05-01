import React, {Component} from "react";
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import BankAccount from "/Users/vera/dev/FlintWebApp/flint/src/components/BankAccounts";
import Home from "/Users/vera/dev/FlintWebApp/flint/src/components/Home.js";
import './App.css';
import NavigationBar from "./components/NavigationBar";
import Category from "./Category";



function App(){
  return(
    <BrowserRouter>
      <NavigationBar/>
      <Routes>
        <Route path='/' element={<Home/>}/>
        <Route path='/bankaccount' element={<BankAccount />}/>
        <Route path='/category' element={<Category />}/>

      </Routes>
    </BrowserRouter>

  );
}

export default App;
