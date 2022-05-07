import React, { Component } from 'react'
import "./Login.css";

class Login extends Component {

  constructor(props){
    super(props)
    this.state = {
      username:"",
      password:"",
      showFailMessage: false,
      showSuccessMessage: false
    }
    this.handleUsernameChange = this.handleUsernameChange.bind(this)
    this.handlePasswordChange = this.handlePasswordChange.bind(this)
    this.loginClicked = this.loginClicked.bind(this)
  }

  handleUsernameChange (event) {
    this.setState({
      username:event.target.value
    })
  }
  handlePasswordChange (event) {
    this.setState({
      password:event.target.value
    })
  }
  loginClicked () {
    if(this.state.username==='veer' && this.state.password==='admin') {
      this.props.navigate(`/welcome/${this.state.username}`)
    } else {
      this.setState({showSuccessMessage:false})
      this.setState({hasLoginFailed:true})
    }

console.log(this.state)

  }

  render() {
    return (
      <div className="container-fluid text-center" style={{position: 'relative', minHeight: '100vh'}}>
        <div style={{display:'flex', justifyContent:'center', alignItems:'center', height:'100vh'}}>
          <div className={'container'}>
      <form className={"formBg"}>
        {this.state.hasLoginFailed && <div className="alert alert-warning">Invalid Credentials</div>}

        <h3>Sign In</h3>
        <div className="mb-3">
          <label>Username</label>
          <input
            onChange={this.handleUsernameChange}
            type="text"
            className="form-control"
            placeholder="Enter username"
            defaultValue=""
            autoComplete ='off'

          />
        </div>
        <div className="mb-3">
          <label>Password</label>
          <input
            onChange={this.handlePasswordChange}
            type="password"
            className="form-control"
            placeholder="Enter password"
            defaultValue=""
            autoComplete ='off'
          />
        </div>
        <div className="mb-3">
        </div>
        <div className="d-grid">
          <button className={"loginButton"} onClick={this.loginClicked}>
            Login
          </button>
        </div>
      </form>
          </div>
        </div>
      </div>
    )
  }
}
export default Login




// import React, { Component } from 'react';
// import "./Login.css";
//
// class Login extends Component {
//   constructor(props){
//     super(props)
//     this.state = {
//       username: 'veer',
//       password: '',
//       showFailMessage: false,
//       showSuccessMessage: false
//     }
//
//   }
//
//   handleChange = (event) =>{
//     this.setState({
//       [event.target.name]:event.target.value
//     })
//   }
//   loginClicked = () => {
//     console.log(this.state.user)
//     console.log(this.state.password)
//     if(this.state.username==='veer' && this.state.password==='admin') {
//       console.log("successful")
//     } else {
//       this.setState({showSuccessMessage:false})
//       this.setState({hasLoginFailed:true})
//     }
//
// console.log(this.state)
//
//   }
//
//
//   render(){
//     return(
//
//         <div className={"login"} >
//           <div className={'container'}>
//           {this.state.hasLoginFailed && <div className="alert alert-warning">Invalid Credentials</div>}
//           {this.state.showSuccessMessage && <div>Login Successful</div>}
//           <span className={'fieldName'}>User Name:</span>  <input type="text" name="username" value={this.state.username} onChange={this.handleChange}/>
//            <span className={'fieldName'}>Password:</span> <input type="password" name="password" value={this.state.password} onChange={this.handleChange}/>
//           <button className={"loginButton"} onClick={this.loginClicked}> Login </button>
//         </div>
//         </div>
//
//     )
//   }
// }
//
//
//
// export default Login
