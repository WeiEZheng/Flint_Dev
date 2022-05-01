import React from 'react';
import {Link} from 'react-router-dom';
import { Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, NavLink } from 'reactstrap';



class NavigationBar extends React.Component {


render(){
return (  <div>
                <Navbar color="faded" light>
                  <NavbarBrand href="/" className="mr-auto">reactstrap</NavbarBrand>
                  <NavbarToggler onClick={this.toggleNavbar} className="mr-2" />
                  <Collapse isOpen={!this.state.collapsed} navbar>
                    <Nav navbar>
                      <NavItem>
                        <NavLink href="/">Home</NavLink>
                      </NavItem>
                      <NavItem>
                        <NavLink href="/api/bankaccount">Accounts</NavLink>
                      </NavItem>
                    </Nav>
                  </Collapse>
                </Navbar>
              </div>);
}} export default NavigationBar;

