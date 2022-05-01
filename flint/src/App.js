import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import NavigationBar from './components/NavigationBar';
import Footer from './components/Footer';
import BankAccounts from './components/BankAccounts';

import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './components/Home';


function App() {
  return (
    <div className="App">
    <NavigationBar/>

    <Router>
    <Routes>
    <Route path='/' exact={true} component={Home}/>
    <Route path='/bankaccount' exact={true} component={BankAccounts}/>
    </Routes>
    </Router>

    </div>
  );
}

export default App;
