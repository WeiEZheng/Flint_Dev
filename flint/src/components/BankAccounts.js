import React from 'react';
import React, {Component} from 'react';
import {Link} from 'react-router-dom';

class BankAccounts extends React.Component {

constructor(props) {
super(props);
this.state = {bankAccounts: [], isLoading: true};
this.remove = this.remove.bind(this);
}

   componentDidMount() {
    this.setState({isLoading: true});

    fetch('/api/bankaccount')

    .then(response => response.json())
    .then(data => this.setState({bankAccounts: data, isLoading: false}));
  }

  render() {
    const {bankAccounts, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
      <div>

            <h2>Bank Accounts</h2>
            {bankAccounts.map(bankAccount =>
              <div key={bankAccount.id}>
                {bankAccount.name}
              </div>
            )}

      </div>
    );
  }
}

export default BankAccounts;
