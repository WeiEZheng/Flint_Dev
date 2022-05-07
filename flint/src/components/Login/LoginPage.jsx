import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import Login from "./Login";

class LoginPage extends Component {
  render() {
    return (

      <div className="container-fluid text-center" style={{position: 'relative', minHeight: '100vh'}}>
        <div style={{display:'flex', justifyContent:'center', alignItems:'center', height:'100vh'}}>
          <div className={'container'}>
            <Login />
          </div>
        </div>
      </div>

    );

  }
}

export default LoginPage;
