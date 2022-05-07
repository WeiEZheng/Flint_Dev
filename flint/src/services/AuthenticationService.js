class AuthenticationService {
  registerSuccessfulLogin(username,password){
    console.log("login registered")
    sessionStorage.setItem('authenticatedUser', username)
  }
  logout(){
    sessionStorage.removeItem('authenticatedUser')
  }
  isLoggedIn(){
    let user = sessionStorage.getItem("authenticatedUser")
    if(user === null) {
      return false;
    } else {
      return true;
    }
  }
}

export default new AuthenticationService();
