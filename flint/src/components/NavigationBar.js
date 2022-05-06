import React, { Component } from 'react';
import brandLogo from './images/brandLogo.png';
import {Link} from 'react-router-dom'
class NavigationBar extends Component {


render(){
return (
  <nav className="navbar navbar-expand-lg navbar-dark" style={{color:'white', background:'rgb(0, 0, 0, .85)', position: 'relative', top:'0', width:'100%'}}>
    <div className="container-fluid" >
    <Link to={"/"}>  <img src={brandLogo}  alt="Fire Logo"/></Link>
      <a className="navbar-brand" style={{fontFamily:'Helvetica Neue', fontWeight:'bold', color:'white'}} href="/">FLINT</a>
      <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
              aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navbarNav">
        <ul className="navbar-nav">
          <li className="nav-item">
            <a className="nav-link active" aria-current="page" href="/">Home</a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="/bankaccount">Accounts</a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="/transactions">Transactions</a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="/expenseReport">Expense Report</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  );
}

}

export default NavigationBar;
