import React from 'react';

class Transactions extends React.Component {
  state = {
    isLoading: true,
    transactions: [],
  };

  async componentDidMount() {
    const response = await fetch('api/transactions');
    const body = await response.json();
    this.setState({ transactions: body, isLoading: false });
  }

  render() {
    const { transactions, isLoading } = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
      <div>
        <h2>Transaction History</h2>
        {transactions.map(transaction => (
          <div key={transaction.id}>
            <ul>
              <li> {transaction.id}</li>
            </ul>
          </div>
        ))}
      </div>
    );
  }
}

export default Transactions;
