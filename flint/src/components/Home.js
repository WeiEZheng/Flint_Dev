import React, { Component } from 'react';
import NavigationBar from './NavigationBar';



class Home extends Component {
  render() {
    return (
<div style={{backgroundImage: 'linear-gradient(#ff8a00, #e52e71)'}}>
      <div className="container-fluid text-center">
        <div style={{display:'flex', justifyContent:'center', alignItems:'center', height:'100vh'}}>
          <div className={'container'}>
            <h1 className={'mt-3'} style={{fontFamily:'Helvetica Neue', fontWeight: 'bold', color:'rgb(0, 0, 0, .70)'}}> Welcome to Flint!</h1>

      </div>
  </div>

      </div>
  <footer className="container-fluid text-center" style={{background:'rgb(255, 255, 255, .3)'}}>
    <span style={{fontWeight:'bold', fontSize:'18px'}}>Copyright 2022 Flint Banking</span>
  </footer>
</div>

  );
  }
}

export default Home;
