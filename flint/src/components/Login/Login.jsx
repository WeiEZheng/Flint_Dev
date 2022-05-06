import React, { Component } from 'react';
import "./Login.css";
class Login extends Component {
  constructor(props){
    super(props)

    this.state = {
      username : "",
      password : ""
    }
  }
handleChange = (event) => {
    console.log(event.target.name);
    this.setState({[event.target.name]:event.target.value})
  }
  render(){
    return(
      <form className={"login"} >
        <span className={"inputBox"}> Username: <input type={"text"}  className={"username"} defaultValue={this.state.username} onChange={this.handleChange} autoComplete={"on"} /></span>
      <span className={"inputBox"}> Password: <input type={"password"}  className={"password"} defaultValue={this.state.password} onChange={this.handleChange} autoComplete={"on"} /> </span>
        <button className={"loginButton"}> Login </button>
      </form>
    )
  }
}
export default Login
