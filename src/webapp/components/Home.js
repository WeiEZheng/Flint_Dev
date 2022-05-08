import React, { Component } from 'react';
import homeLogo from './images/test.png';
import Login from "./Login/Login";
import {Route, BrowserRouter as Router, Routes, Link} from 'react-router-dom';
import Welcome from "./Welcome";
import Footer from "./Footer";
import NavigationBar from "./NavigationBar";

class Home extends Component {
  render() {
    return (
      <div style={{ backgroundImage: 'linear-gradient(#ff8a00, #e52e71)', position: 'relative', minHeight: '100vh' }}>
      <div className="container-fluid text-center" style={{position: 'relative', minHeight: '100vh'}}>
        <div style={{display:'flex', justifyContent:'center', alignItems:'center', height:'100vh'}}>
          <div className={'container'}>
            <Link to={""} ><img className={"container-fluid text-center"} src={homeLogo} alt={"The Flint logo: A flame"}/></Link>
          </div>
      </div>
      </div>
      <Footer />
      </div>
  );

  }
}

export default Home;
