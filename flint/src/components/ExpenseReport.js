import React, {Component} from 'react';
import Budget from './Budget';
import Remaining from './Remaining';
import AmountSpent from './AmountSpent';
import './ExpenseReport.css';
import { FormGroup } from 'reactstrap';
import { Link } from 'react-router-dom';


class ExpenseReport extends Component {
  constructor(props){
    super(props)
    this.state = {
      expenses:
        [
          {id:1, name: "Fishing", amount: "50.52", category:"Vacation", date: "11/11/11"},
          {id:2, name: "Ibiza w/ Anna Delvey 😵‍💫",amount: "40532.93", category:"Vacation", date: "12/22/11"},
          {id:3, name: "July Interest",amount: "340", category:"Student Loans", date: "11/21/11"}

        ]
    }
  }
  render(){
    return (
      <div className={'expenseForm'}>
        <div className={'container'}>
          <h1 className={'mt-3'} style={{ fontFamily: 'Helvetica Neue', fontWeight: 'bold', color: 'black' }}>
            {' '}
            Welcome to Your Expense Report:
          </h1>
          <div className={'row mt-3'}>
            <div className={'col-sm'}>
              <Budget />
            </div>
            <div className={'col-sm'}>
              <Remaining />
            </div>
            <div className={'col-sm'}>
              <AmountSpent />
            </div>
            <form className="row g-3">
              <FormGroup>
                <label for={'category'}>
                  <h3>Name:</h3>
                </label>
                <input
                  name={'name'}
                  id={'name'}
                  className="form-control form-control-lg mb-3"
                  type="text"
                  placeholder="Add Name of Expense..."
                  aria-label=".form-control-lg"
                  onChange
                />
              </FormGroup>

              <FormGroup>
                <label for={'category'}>
                  <h3>Category:</h3>
                </label>
                <select name={'category'} id={'category'} className="form-select form-select-lg mb-3" aria-label="Default select example">
                  <option selected>"Student Loans 🤡"</option>
                  <option value="1">Shopping 🤑</option>
                  <option value="2">Vacation 🛳</option>
                  <option value="3">Car Payment 🚗 </option>
                </select>
              </FormGroup>

              <FormGroup>
                <label for={'dateOfExpense'}>
                  <h3>Date:</h3>
                </label>

              </FormGroup>
              <FormGroup>
                <span className={'col-sm'}>
                  <button type="submit" className={'alert alert-primary'} style={{ marginRight: '16px', fontWeight: 'bold' }}>
                    Submit
                  </button>
                </span>
                <span className={'col-sm'}>
                  <Link to={'/'}>
                    <button type="cancel" className={'alert alert-warning'} style={{ fontWeight: 'bold' }}>
                      Cancel
                    </button>
                  </Link>
                </span>
              </FormGroup>
            </form>
            <div className={'container'} >
              <table className={'table'}>
                <thead>
                <tr className={"tableHeader"}>
                  <th>Name:</th>
                  <th>Amount:</th>
                  <th>Category:</th>
                  <th> Date:</th>
                </tr>
                </thead>
                <tbody>
                {
                  this.state.expenses.map (
                    expense =>
                      <tr>
                        <td className={"tableData"}>{expense.name}</td>
                        <td className={"tableData"}>${expense.amount}</td>
                        <td className={"tableData"}>{expense.category}</td>
                        <td className={"tableData"}>{expense.date}</td>
                      </tr>
                  )
                }
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
;
export default ExpenseReport;

