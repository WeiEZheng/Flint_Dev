import React from 'react';
import { Button, TableRow, Table } from 'semantic-ui-react';
import { Link, useLocation } from 'react-router-dom';
import { format } from 'date-fns';

class TransactionDetail extends React.Component {
  state = {
    isLoading: true,
    transactions: [],
  };

  async componentDidMount() {
    const response = await fetch('/api/transactions/33');
    const body = await response.json();
    this.setState({ transactions: body, isLoading: false });

  }

  render() {
    const { transactions, isLoading } = this.state;
    if (isLoading) {
      return <p>Loading...</p>;
    }
    
    return (
      <div className="table-responsive">
      {transactions ? (
        <></>
      ) : (
        !isLoading && <div>No Transaction found</div>
      )}
    </div>
    )
}}

export default TransactionDetail;