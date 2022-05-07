import React, { Component} from 'react';
import './ExpenseTable.css';

class ExpenseTable extends Component {
  constructor(props){
    super(props)
    this.state = {
      expenses:
        [
          {id:1, name: "Fishing", category:"Vacation", date: 11/11/11},
          {id:2, name: "Ibiza", category:"Vacation", date: 12/22/11},
          {id:3, name: "July Interest", category:"Student Loans", date: 11/21/11}

        ]
    }
  }
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
