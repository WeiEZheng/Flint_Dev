import React, {Component} from "react";
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import BankAccounts from "./components/BankAccounts";
import Home from "./components/Home.js";
import Transactions from "./components/Transactions";
import NavigationBar from "./components/NavigationBar";
import 'bootstrap/dist/css/bootstrap.min.css';
import Footer from "./components/Footer";
import ExpenseReport from "./components/ExpenseReport";
import Deposit from './components/Deposit';
import Transfer from './components/Transfer';
import CreateAccount from './components/CreateAccount';
import Withdraw from './components/Withdraw';

const App = () => {

  return (
    <div style={{backgroundImage: 'linear-gradient(#ff8a00, #e52e71)', position: 'relative', minHeight: '100vh'}}>
    <NavigationBar/>
        <BrowserRouter>
          <Routes>
            <Route path='/' element={<Home/>}/>
            <Route path='/bankaccount' element={<BankAccounts />}/>
            <Route path='/expenseReport' element={<ExpenseReport />}/>
            <Route path='/transactions' element={<Transactions />}/>
            <Route path='/deposit' element={<Deposit />}/>
            <Route path='/newaccount' element={<CreateAccount />}/>
            <Route path='/withdraw' element={<Withdraw />}/>
            <Route path='/transfer' element={<Transfer />}/>
          </Routes>
        </BrowserRouter>
    <Footer/>
  </div>
  )
}
export default App;
