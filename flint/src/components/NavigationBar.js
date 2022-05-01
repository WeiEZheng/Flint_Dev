import React from 'react';

class NavigationBar extends React.Component {


render(){
return (<nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                  <img src="https://i.imgur.com/oZUMusi.png" alt="Fire Logo"/>
                  <a class="navbar-brand" href="#">Flint</a>
                  <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                  </button>
                  <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                      <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Home</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="#">Accounts</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="#">Transactions</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link disabled">Expense Tracker</a>
                      </li>
                    </ul>
                  </div>
                </div>
              </nav>);
}} export default NavigationBar;

