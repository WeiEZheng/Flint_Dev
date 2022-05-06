import React from 'react';
import { Link } from 'react-router-dom';

class Deposit extends React.Component {

  constructor(props) {
    super(props);
    this.state = {accountNumber: '', amount: ''}
  }

  depositFunds(event) {
    alert(this.state.accountNumber);
    event.preventDefault();
  }
  
  accountChange(event) {
    this.setState({
      [event.target.accountNumber]: event.target.value
    });
  }

  render() {
    return (
      <>
        <div className="container-fluid text-center">
          <div className="row content">
            <div className="col-sm-2 sidenav">
              <Link to="/bankaccount">
                <button className="btn-sm btn-danger" style={{ margin: 5 }}>
                  Back
                </button>
              </Link>
            </div>
            <div className="col-sm-8 text-left">
              <h1>Accounts</h1>
              <div>
                <h6>Deposit</h6>
                <form className="form-inline" onSubmit={this.depositFunds} id="depositForm">
                  <div className="input-group mb-3">
                    <div className="input-group-prepend">
                      <span className="input-group-text" id="inputGroup-sizing-default">
                        Account Number
                      </span>
                    </div>
                    <input
                      type="text" name="accountNumber"
                      className="form-control"
                      aria-label="Sizing example input"
                      aria-describedby="inputGroup-sizing-default"
                    ></input>
                  </div>
                  <div className="input-group mb-3">
                    <div className="input-group-prepend">
                      <span className="input-group-text" id="inputGroup-sizing-default">
                        Amount 
                      </span>
                    </div>
                    <input
                      type="text" name="amount"
                       //value={this.state.amount}
                      // onChange = { this.amountChanged}
                      className="form-control"
                      aria-label="Sizing example input"
                      aria-describedby="inputGroup-sizing-default"
                    ></input>
                  </div>
                  <button type="submit" className="btn btn-danger mb-2">
                    Submit
                  </button>
                </form>
              </div>
              <hr></hr>
              <h3>Banking text</h3>
              <p>Some kind of account text</p>
            </div>
            <div className="col-sm-2 sidenav">
              <div className="well">
                <p>Blogger</p>
              </div>
              <div className="well">
                <p>NYCE Tube</p>
              </div>
            </div>
          </div>
        </div>
      </>
    );
  }
}
export default Deposit;
