import React, { Component } from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import BankAccounts from './components/AccountComp/BankAccounts';
import Home from './components/Home.js';
import Transactions from './components/Transactions/Transactions';
import NavigationBar from './components/NavigationBar';
import 'bootstrap/dist/css/bootstrap.min.css';
import Footer from './components/Footer';
import ExpenseReport from './components/ExpenseReport';
import Deposit from './components/AccountComp/Deposit';
import Transfer from './components/AccountComp/Transfer';
import CreateAccount from './components/AccountComp/CreateAccount';
import Withdraw from './components/AccountComp/Withdraw';
import TransactionDetail from './components/Transactions/Transaction.Details';
import Login from "./components/Login/Login";
import {Link} from "react-router-dom";
import homeLogo from './components/images/test.png';


const App = () => {
  return (
    <div className="container-fluid text-center" style={{position: 'relative', minHeight: '100vh'}}>
      <div style={{display:'flex', justifyContent:'center', alignItems:'center', height:'100vh'}}>
        <div className={'container'}>
      <Link to={"login"} ><img className={"container-fluid text-center"} src={homeLogo} alt={"The Flint logo: A flame"}/></Link>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/bankaccount" element={<BankAccounts />} />
          {/* <Route path="/expenseReport" element={<ExpenseReport />} /> */}
          <Route path="/transactions" element={<Transactions />} />
//          <Route path="/transactions/:id" element={<TransactionDetail/>} />
          <Route path="/deposit" element={<Deposit />} />
          <Route path="/newaccount" element={<CreateAccount />} />
          <Route path="/withdraw" element={<Withdraw />} />
          <Route path="/transfer" element={<Transfer />} />
        </Routes>
      <Footer />
        </div>
    </div>
  );
};
export default App;
