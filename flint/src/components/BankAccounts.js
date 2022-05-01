import React from 'react';


class BankAccounts extends React.Component {

  state = {
    isLoading: true,
    bankAccounts: []
  }

  async componentDidMount(){
    const response = await fetch('api/bankaccount')
    const body = await response.json();
    this.setState({bankAccounts: body, isLoading:false});
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
            <ul>

            <li>    {bankAccount.accountName}</li>
            </ul>
              </div>
            )}

      </div>
    );
  };
}

export default BankAccounts;
