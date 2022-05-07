import React, { Component} from 'react';
import './ExpenseTable.css';

class ExpenseTable extends Component {
  render(){
    return(
      <div className={'container'} >
        <table className={'table'}>
          <thead>
          <tr className={"tableHeader"}>
            <th>Name:</th>
            <th>Category:</th>
            <th> Date:</th>
          </tr>
          </thead>
          <tbody>
          {/*{*/}
          {/*  this.state.expenses.map(*/}
          {/*    expense =>*/}
          {/*      <tr>*/}
          {/*        <td></td>*/}
          {/*        <td></td>*/}
          {/*        <td></td>*/}
          {/*        <td></td>*/}
          {/*      </tr>*/}
          {/*  )*/}
          {/*}*/}
          </tbody>
        </table>
      </div>
    )
  }
}

export default ExpenseTable
