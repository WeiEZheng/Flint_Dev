import React, { Component } from 'react';
import NavigationBar from './NavigationBar';



class Home extends Component {
  render() {
    return (
      <div>
        <NavigationBar/>
        <p> Hello Moto</p>
      </div>

    );
  }
}

export default Home;
