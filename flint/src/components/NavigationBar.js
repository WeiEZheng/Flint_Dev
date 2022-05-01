import React, { Component } from 'react';
import brandLogo from "./images/brandLogo.png";


class NavigationBar extends Component {


render(){
return (
  <nav className="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
    <div className="container-fluid">
      <img src={brandLogo} alt="Fire Logo"/>
      <a className="navbar-brand" href="#">Flint</a>
      <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
              aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navbarNav">
        <ul className="navbar-nav">
          <li className="nav-item">
            <a className="nav-link active" aria-current="page" href="#">Home</a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="#">Accounts</a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="#">Transactions</a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="/expenses">Expense Tracker</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  );
}

}

export default NavigationBar;

