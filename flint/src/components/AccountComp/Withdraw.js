import React from 'react';
import {Link} from 'react-router-dom';

class Withdraw extends React.Component {


render(){
return(
<>
<div className="container-fluid text-center">
                <div className="row content">
                  <div className="col-sm-2 sidenav">
                    <Link to="/bankaccount">
                  <button className= 'btn-sm btn-danger'style={{margin:5}}>Back</button>
                    </Link>
                  </div>
                  <div className="col-sm-8 text-left">
                    <h1>Accounts</h1>
                <div>
                  <h6>Withdraw</h6>
                <div class="input-group mb-3">
                  <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-default">Account Number</span>
                  </div>
                  <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"></input>
                </div>
                <div class="input-group mb-3">
                     <div class="input-group-prepend">
                     <span class="input-group-text" id="inputGroup-sizing-default">Amount</span>
                     </div>
                    <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"></input>
                   </div>
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
)
}
}
export default Withdraw;
