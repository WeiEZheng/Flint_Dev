import React, { Component } from 'react';
import homeLogo from './images/test.png';
import Footer from './Footer';
import Login from "./Login/Login";

class Home extends Component {
  render() {
    return (

      <div className="container-fluid text-center" style={{position: 'relative', minHeight: '100vh'}}>
        <div style={{display:'flex', justifyContent:'center', alignItems:'center', height:'100vh'}}>
          <div className={'container'}>
            <img className={"container-fluid text-center"} src={homeLogo} alt={"The Flint logo: A flame"}/>
            <Login/>

          </div>
      </div>
      </div>

  );

  }
}

export default Home;
