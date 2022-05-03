import React from 'react';

class Transfer extends React.Component {


render(){
return(
<>
<div className="container-fluid text-center">
                <div className="row content">
                  <div className="col-sm-2 sidenav">

                  </div>
                  <div className="col-sm-8 text-left">
                    <h1>Transfer Funds</h1>
                <div>
                  <h6>Transfer</h6>
                <div class="input-group mb-3">
                  <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-default">From Account</span>
                  </div>
                  <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"></input>
                </div>
                <div class="input-group mb-3">
                                     <div class="input-group-prepend">
                                     <span class="input-group-text" id="inputGroup-sizing-default">To Account</span>
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
export default Transfer;
