import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import NavigationBar from './components/NavigationBar';
import Footer from './components/Footer';

import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './components/Home';


function App() {
  return (
    <div className="App">
    <NavigationBar/>
    </div>
  );
}

export default App;
