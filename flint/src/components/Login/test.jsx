import React, {Component} from "@types/react";

class Login extends Component {
  constructor(props){
    super(props)
    this.state = {
      username: 'in28minutes',
      password: '',
      hasLoginFailed: false,
      showSuccessMessage: false
    }
    this.handleChange = this.handleChange.bind(this)
    this.loginClicked = this.loginClicked.bind(this)
  }

  handleChange(event){
    this.setState({
      [event.target.name]:event.target.value
    })
  }

  loginClicked() {
    if(this.state.username==='in28minutes' && this.state.password==='dummy'){
      console.log("SUCCESS")
    }
    else {
      this.setState({showSuccessMessage:false})
      this.setState({hasLoginFailed:true})
    }
    console.log(this.state)
  }

  render() {
    return (
      <div>
        {this.state.hasLoginFailed && <div className="alert alert-warning">Invalid Credentials</div>}
        {this.state.showSuccessMessage && <div>Login Successful</div>}
        <div className="TodoApp">
          User Name: <input type="text" name="username" value={this.state.username} onChange={this.handleChange}/>
          Password: <input type="password" name="password" value={this.state.password} onChange={this.handleChange}/>
          <button className="btn btn-success" onClick={this.loginClicked}>Login</button>
        </div>
      </div>
    )
  }
}

export default Login
t
