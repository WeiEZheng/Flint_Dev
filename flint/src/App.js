import React, {Component} from "react";
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import BankAccount from "./components/BankAccounts";
import Home from "./components/Home.js";
import Transactions from "./components/Transactions";
import NavigationBar from "./components/NavigationBar";
import 'bootstrap/dist/css/bootstrap.min.css';
import Footer from "./components/Footer";
import ExpenseReport from "./components/ExpenseReport";

const App = () => {

  return (
    <div style={{backgroundImage: 'linear-gradient(#ff8a00, #e52e71)', position: 'relative', minHeight: '100vh'}}>
    <NavigationBar/>
        <BrowserRouter>
          <Routes>
            <Route path='/' element={<Home/>}/>
            <Route path='/bankaccount' element={<BankAccount />}/>
            <Route path='/expenseReport' element={<ExpenseReport />}/>
            <Route path='/transactions' element={<Transactions />}/>
          </Routes>
        </BrowserRouter>
    <Footer/>
  </div>
  )
}
export default App;
