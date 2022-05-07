import React, { Component } from 'react'
import AuthenticationService from "../services/AuthenticationService";
import { Navigate } from 'react-router-dom'

class AuthenticatedRoute extends Component{
  render(){
    if(AuthenticationService.isLoggedIn()){
      return {...this.props.children}
    } else {
      return <Navigate to="/login" />
    }

  }
}
export default AuthenticatedRoute
