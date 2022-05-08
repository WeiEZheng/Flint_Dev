import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import App from './webapp/App';
import "./webapp/index.css";
import Home from "./webapp/components/Home";
import BankAccounts from "./webapp/components/AccountComp/BankAccounts";
import ExpenseReport from "./webapp/components/ExpenseReport";
import Transactions from "./webapp/components/Transactions/Transactions";
import Deposit from "./webapp/components/AccountComp/Deposit";
import CreateAccount from "./webapp/components/AccountComp/CreateAccount";
import Withdraw from "./webapp/components/AccountComp/Withdraw";
import Transfer from "./webapp/components/AccountComp/Transfer";
import NavigationBar from "./webapp/components/NavigationBar";

import Footer from "./webapp/components/Footer";
import Welcome from "./webapp/components/Welcome";
import withNavigation from "./webapp/components/WithNavigation";

import Login from "./webapp/components/Login/Login";
import Error from "./webapp/components/Error";
import withParams from "./webapp/components/WithParams";
import AuthenticationService from "./webapp/services/AuthenticationService";
import AuthenticatedRoute from "./webapp/components/AuthenticatedRoute";
import Logout from "./webapp/components/Logout";


const root = ReactDOM.createRoot(document.getElementById('root'));


let LoginWithNavigation = withNavigation(Login);
let WelcomeWithParams = withParams(Welcome);
root.render(
<React.StrictMode>
    <BrowserRouter>
      <div style={{ backgroundImage: 'linear-gradient(#ff8a00, #e52e71)', position: 'relative', minHeight: '100vh' }}>
        <NavigationBar />



        <Routes>
        <Route path="/" element={<App />} />
        <Route path="/home" element={<Home />} />
        <Route path="/bankaccount" element={<AuthenticatedRoute><BankAccounts /></AuthenticatedRoute>} />
        <Route path="/expenseReport" element={<AuthenticatedRoute><ExpenseReport /></AuthenticatedRoute>} />
        <Route path="/transactions" element={<AuthenticatedRoute><Transactions /></AuthenticatedRoute>} />
        <Route path="/deposit" element={<AuthenticatedRoute><Deposit /></AuthenticatedRoute>} />
        <Route path="/newaccount" element={<CreateAccount />} />
        <Route path="/withdraw" element={<AuthenticatedRoute><Withdraw /></AuthenticatedRoute>} />
        <Route path="/transfer" element={<AuthenticatedRoute><Transfer /></AuthenticatedRoute>} />
        <Route path="/logout"  element={<Logout/>} />
        <Route path="/welcome/:name" element={<AuthenticatedRoute><WelcomeWithParams /></AuthenticatedRoute>} />
        <Route path="/login" element={<LoginWithNavigation />} />
        <Route path="*" element={<Error />} />

        </Routes>
        <Footer />
      </div>
    </BrowserRouter>
  </React.StrictMode>
);


