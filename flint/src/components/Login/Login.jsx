import React, { Component } from 'react';
import "./Login.css";
class Login extends Component {
  render(){
    return(
      <div className={"login"}>
        <span className={"inputBox"}> Username: <input type={"text"}  className={"username"} /></span>
      <span className={"inputBox"}> Password: <input type={"password"}  className={"password"} /> </span>
        <button className={"loginButton"}> Login </button>
      </div>
    )
  }
}
export default Login
