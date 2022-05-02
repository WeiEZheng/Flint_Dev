import React, { Component } from 'react';
import homeLogo from "./images/test.png";
import Footer from "./Footer";



class Home extends Component {
  render() {
    return (
      <div>
<div style={{backgroundImage: 'linear-gradient(#ff8a00, #e52e71)'}}>
      <div className="container-fluid text-center">
        <div style={{display:'flex', justifyContent:'center', alignItems:'center', height:'100vh'}}>
          <div className={'container'}>
            <img className={"container-fluid text-center"} src={homeLogo} alt={"The Flint logo: A flame"}/>
            {/*<h1 className={'mt-3'} style={{fontFamily:'Helvetica Neue', fontWeight: 'bold', color:'rgb(0, 0, 0, .70)'}}> Welcome to Flint!</h1>*/}

      </div>
  </div>
      </div>
</div>

        <Footer/>
      </div>

  );
  }
}

export default Home;
