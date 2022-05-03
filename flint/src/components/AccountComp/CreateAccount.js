import React from 'react';
import { Link } from 'react-router-dom';

class CreateAccount extends React.Component {
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
              <h1>Create an Account</h1>
              <div>
                <h6>Enter Details</h6>
                <form className="form-inline">
                  <div className="input-group mb-3">
                    <div className="input-group-prepend">
                      <span className="input-group-text" id="inputGroup-sizing-default">
                        Name
                      </span>
                    </div>
                    <input
                      type="text"
                      className="form-control"
                      aria-label="Sizing example input"
                      aria-describedby="inputGroup-sizing-default"
                    ></input>
                  </div>
                  <div className="input-group mb-3">
                    <div className="input-group-prepend">
                      <span className="input-group-text" id="inputGroup-sizing-default">
                        Initial Funds
                      </span>
                    </div>
                    <input
                      type="text"
                      className="form-control"
                      aria-label="Sizing example input"
                      aria-describedby="inputGroup-sizing-default"
                    ></input>
                  </div>
                  <div className="input-group mb-3">
                    <div className="input-group-prepend">
                      <span className="input-group-text" id="inputGroup-sizing-default">
                        Account Type
                      </span>
                    </div>
                    <input
                      type="text"
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
export default CreateAccount;
