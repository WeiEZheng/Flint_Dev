class AuthenticationService {
  registerSuccessfulLogin(username,password){
    console.log("login registered")
    sessionStorage.setItem('authenticatedUser', username)
  }
  logout(){
    sessionStorage.removeItem('authenticatedUser')
  }
}

export default new AuthenticationService();
