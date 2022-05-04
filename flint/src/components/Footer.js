import React from 'react';

const Footer = () => {
  return (
    <div style={{background:'rgb(255, 255, 255, .3)', position: 'absolute', bottom: '0', width:'100%'}}>
      <footer className="text-center" style={{background:'rgb(0, 0, 0, .85)'}}>
        <span style={{color:'white',fontWeight:'bold', fontSize:'18px'}}>Copyright 2022 Flint Banking</span>

      </footer>
    </div>
  );
};
export default Footer;
