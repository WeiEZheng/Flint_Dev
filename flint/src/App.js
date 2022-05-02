import React, {Component} from "react";
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import BankAccount from "./components/BankAccounts";
import Home from "./components/Home.js";
//import Expenses from "./components/Expenses";
import Transactions from "./components/Transactions";
import './App.css';
import NavigationBar from "./components/NavigationBar";
import Category from "./Category";



function App(){
  return(
  <div>
  <NavigationBar/>
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Home/>}/>
        <Route path='/bankaccount' element={<BankAccount />}/>
        <Route path='/category' element={<Category />}/>
        <Route path='/transactions' element={<Transactions />}/>
      </Routes>
    </BrowserRouter>
    </div>
  );
}

export default App;
