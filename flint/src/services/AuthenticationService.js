class AuthenticationService {
  registerSuccessfulLogin(username,password){
    console.log("login registered")
    sessionStorage.setItem('authenticatedUser', username)
  }

}

export default new AuthenticationService();
