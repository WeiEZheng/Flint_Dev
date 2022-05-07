import React, { Component } from 'react';
import homeLogo from './images/test.png';
import {Link} from 'react-router-dom';
import './Welcome.css';

class Welcome extends Component {
  render() {
    return (

      <div className="container-fluid text-center" style={{position: 'relative', minHeight: '100vh'}}>
        <div style={{display:'flex', justifyContent:'center', alignItems:'center', height:'100vh'}}>
          <div className={'container'}>
            <Link to={""} ><img className={"container-fluid text-center"} src={homeLogo} alt={"The Flint logo: A flame"}/></Link>
            <span className={'welcomeText'}> WELCOME to FLINT </span>
          </div>

        </div>
      </div>

    );

  }
}

export default Welcome;
