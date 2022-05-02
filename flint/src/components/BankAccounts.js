import React, {useState} from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';
import Chart from './Chart';

class BankAccounts extends React.Component {

  state = {
    isLoading: true,
    bankAccounts: []

  }

  async componentDidMount(){
    const response = await fetch('api/bankaccount')
    const body = await response.json();
    this.setState({bankAccounts: body, isLoading:false});
  }



  render() {
    const {bankAccounts, isLoading} = this.state;


    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
       <>
            <div className="container-fluid text-center">
                <div className="row content">
                  <div className="col-sm-2 sidenav">
                    <p><a href="create/bankaccount">Create Account</a></p>
                    <p><a href="#">Link</a></p>
                    <p><a href="#">Link</a></p>
                  </div>
                  <div className="col-sm-8 text-left">
                    <h1>Accounts</h1>
                <div>
                  <h2>Bank Accounts</h2>
                   <p></p>
                </div>
                    <hr></hr>
                    <h3>Test</h3>
                    <p>Lorem ipsum...</p>
                  </div>
                  <div className="col-sm-2 sidenav">
                    <div className="well">
                      <p>ADS</p>
                    </div>
                    <div className="well">
                      <p>ADS</p>
                    </div>
                  </div>
                </div>
              </div>

              <footer className="container-fluid text-center">
                <p>Copyright 2022 Flint Banking</p>
              </footer>
      </>
    );
  };
}

export default BankAccounts;
