import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import App from './App';
import "./index.css";
import Home from "./components/Home";
import BankAccounts from "./components/AccountComp/BankAccounts";
import ExpenseReport from "./components/ExpenseReport";
import Transactions from "./components/Transactions";
import Deposit from "./components/AccountComp/Deposit";
import CreateAccount from "./components/AccountComp/CreateAccount";
import Withdraw from "./components/AccountComp/Withdraw";
import Transfer from "./components/AccountComp/Transfer";
import NavigationBar from "./components/NavigationBar";
import Footer from "./components/Footer";


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(

  <React.StrictMode>
    <BrowserRouter>
      <div style={{ backgroundImage: 'linear-gradient(#ff8a00, #e52e71)', position: 'relative', minHeight: '100vh' }}>
        <NavigationBar />



      <Routes>
        <Route path="/" element={<App />} />
        <Route path="/home" element={<Home />} />
        <Route path="/bankaccount" element={<BankAccounts />} />
        <Route path="/expenseReport" element={<ExpenseReport />} />
        <Route path="/transactions" element={<Transactions />} />
        <Route path="/deposit" element={<Deposit />} />
        <Route path="/newaccount" element={<CreateAccount />} />
        <Route path="/withdraw" element={<Withdraw />} />
        <Route path="/transfer" element={<Transfer />} />
      </Routes>
        <Footer />
      </div>
    </BrowserRouter>
  </React.StrictMode>
);


