import React, {Component} from "react";
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import BankAccount from "./components/BankAccounts";
import Home from "./components/Home.js";
import Transactions from "./components/Transactions";
import NavigationBar from "./components/NavigationBar";
import ExpenseReport from "./components/ExpenseReport";
import 'bootstrap/dist/css/bootstrap.min.css';
import Footer from "./components/Footer";

const App = () => {

  return (
  <div>
    <NavigationBar/>
        <BrowserRouter>
          <Routes>
            <Route path='/' element={<Home/>}/>
            <Route path='/bankaccount' element={<BankAccount />}/>
            <Route path='/expenseReport' element={<ExpenseReport />}/>
            <Route path='/transactions' element={<Transactions />}/>
          </Routes>
        </BrowserRouter>
    <div><Footer/></div>
  </div>
  )
}
export default App;
