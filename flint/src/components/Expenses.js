import React from 'react';


class Expenses extends React.Component {

  state = {
    isLoading: true,
    expenses: []
  }

  async componentDidMount(){
    const response = await fetch('/expenses')
    const body = await response.json();
    this.setState({expenses: body, isLoading:false});
  }


  render() {
    const {expenses, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
      <div>
            <h2>Account Expenses</h2>
            {expenses.map(expense =>
              <div key={expense.id}>
            <ul>

            <li>    {expense.accountName}</li>
            </ul>
              </div>
            )}

      </div>
    );
  };
}

export default Expenses;
