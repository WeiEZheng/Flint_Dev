import React, {Component} from 'react';
import Budget from './Budget';
import Remaining from './Remaining';
import AmountSpent from './AmountSpent';
import './ExpenseReport.css';
import { FormGroup } from 'reactstrap';
import { Link } from 'react-router-dom';
import ExpenseReportService from "../api/ExpenseReportService";


class ExpenseReport extends Component {
  constructor(props){
    super(props)
    this.state = {
      grabBudgets: []
    }

    this.retrieveBudgets = this.retrieveBudgets.bind(this)
    this.handleSuccessfulResponse = this.handleSuccessfulResponse.bind(this)
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
                <label htmlFor={'category'}>
                  <h3>Name:</h3>
                </label>
                <input
                  name={'name'}
                  id={'name'}
                  className="form-control form-control-lg mb-3"
                  type="text"
                  placeholder="Add Name of Expense..."
                  aria-label=".form-control-lg"
                />
              </FormGroup>

              <FormGroup>
                <label htmlFor={'category'}>
                  <h3>Category:</h3>
                </label>
                <select name={'category'} id={'category'} className="form-select form-select-lg mb-3" aria-label="Default select example">
                  <option defaultValue>"Student Loans 🤡"</option>
                  <option value="1">Shopping 🤑</option>
                  <option value="2">Vacation 🛳</option>
                  <option value="3">Car Payment 🚗 </option>
                </select>
              </FormGroup>

              <FormGroup>
                <label htmlFor={'dateOfExpense'}>
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
              <div className={'container'}>
                <button className={'btn btn-success'}onClick={this.retrieveBudgets}>Generate my Report</button>
              </div>
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
                  this.state.grabBudgets.map (
                    expense =>
                      <tr key = {expense.id}>
                        <td className={"tableData"}>{expense.nameOfExpense}</td>
                        <td className={"tableData"}>{expense.amountSpent}</td>
                        <td className={"tableData"}>{expense.categoryId}</td>
                        <td className={"tableData"}>{expense.dateOfExpense}</td>
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

  retrieveBudgets(){
    ExpenseReportService.executeExpenseReportService()
      .then(response => this.handleSuccessfulResponse(response))
      .catch(response => console.log(response.status))

  }
  handleSuccessfulResponse(response){
    this.setState({grabBudgets:response.data})
  }
}
;
export default ExpenseReport;

