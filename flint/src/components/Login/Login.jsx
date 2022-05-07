import React, { Component } from 'react';
import "./Login.css";

class Login extends Component {
  constructor(props){
    super(props)
    this.state = {
      username: 'veer',
      password: '',
      showFailMessage: false,
      showSuccessMessage: false
    }

  }

  handleChange = (event) =>{
    this.setState({
      [event.target.name]:event.target.value
    })
  }
  loginClicked = () => {
    console.log(this.state.user)
    console.log(this.state.password)
    if(this.state.username==='veer' && this.state.password==='admin') {
      console.log("successful")
    } else {
      this.setState({showSuccessMessage:false})
      this.setState({hasLoginFailed:true})
    }

console.log(this.state)

  }


  render(){
    return(
      <div className={"container"}>
        <div className={"login"} >
          {this.state.hasLoginFailed && <div className="alert alert-warning">Invalid Credentials</div>}
          {this.state.showSuccessMessage && <div>Login Successful</div>}
          User Name: <input type="text" name="username" value={this.state.username} onChange={this.handleChange}/>
          Password: <input type="password" name="password" value={this.state.password} onChange={this.handleChange}/>
          <button className={"loginButton"} onClick={this.loginClicked}> Login </button>
        </div>
      </div>
    )
  }
}



export default Login
