import React, { Component } from 'react';
import { Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, NavLink, NavbarText, DropdownItem, DropdownToggle, DropdownMenu, UncontrolledDropdown} from 'reactstrap';
import brandLogo from "./images/brandLogo.png";


class NavigationBar extends Component {


render(){
return (
  <div>
    <Navbar
      color="dark"
      dark
      expand="md"
      dark
    >
      <NavbarBrand href="/">
        <img src={brandLogo} alt={"Flint Logo - A Flame"}/>
        FLINT
      </NavbarBrand>
      <NavbarToggler onClick={function noRefCheck(){}} />
      <Collapse navbar>
        <Nav
          className="me-auto"
          navbar
        >

          <UncontrolledDropdown
            inNavbar
            nav
          >
            <DropdownToggle
              caret
              nav
            >
              Menu
            </DropdownToggle>
            <DropdownMenu right>
              <DropdownItem href={"#"}>
                Transactions
              </DropdownItem>

              <DropdownItem href={"#"}>
                Bank Account
              </DropdownItem>
              <DropdownItem href={"#"}>
                Expense Tracker
              </DropdownItem>
              <DropdownItem divider />
              <DropdownItem href={"#"}>
                Sign out
              </DropdownItem>
            </DropdownMenu>
          </UncontrolledDropdown>
          <NavItem>
            <NavLink href="https://github.com/WEIPREVER/Flint_Dev">
              GitHub
            </NavLink>
          </NavItem>
        </Nav>
      </Collapse>
    </Navbar>
  </div>);
}} export default NavigationBar;

