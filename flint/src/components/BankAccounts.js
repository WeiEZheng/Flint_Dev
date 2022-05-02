import React, {useState, useEffect} from 'react';
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
            <div style={{backgroundImage: 'linear-gradient(#ff8a00, #e52e71)'}} className="container-fluid text-center">
                <div className="row content">
                  <div className="col-sm-2 sidenav">
                  <p><button className='btn btn-danger'>Create Account</button></p>
                  <p><button className='btn btn-danger'>Transfer</button></p>
                  <p><button className='btn btn-danger'>Withdraw</button></p>
                  <p><button className='btn btn-danger'>Deposit</button></p>

                  </div>
                  <div className="col-sm-8 text-left">
                    <h1>Your Accounts</h1>
                <div style={{backgroundImage: 'linear-gradient(#ff8a00, #e52e71)'}}>
                  <h4>Balances</h4>
                      <Chart />
                <p></p>
                </div>
                    <hr></hr>
                    <h3>Put some banking text here.</h3>
                    <p>Some account related text....</p>
                  </div>
                  <div className="col-sm-2 sidenav">
                    <div className="well">
                      <p>Put an ad here?</p>
                    </div>
                    <div className="well">
                      <p>Put an add here?</p>
                    </div>
                  </div>
                </div>
              </div>
      </>
    );
  };
}

export default BankAccounts;
