import React from 'react';
import Budget from './Budget';
import Remaining from './Remaining';
import AmountSpent from './AmountSpent';
import { FormGroup } from 'reactstrap';
import { Link } from 'react-router-dom';

const ExpenseReport = () => {
  return (
    <div style={{ backgroundImage: 'linear-gradient(#ff8a00, #e52e71)' }}>
      <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
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
                <select name={'category'} id={'category'} class="form-select form-select-lg mb-3" aria-label="Default select example">
                  <option selected>"Student Loans 🤡"</option>
                  <option value="1">Shopping 🤑</option>
                  <option value="2">Vacation 🛳</option>
                  <option value="3">Car Payment 🚗 </option>
                </select>
              </FormGroup>

              <FormGroup>
                <label for={'dateOfExpense'}>
                  <h3>Date of Expense:</h3>
                </label>
                <DatePicker selected={this.state.date} onChange={{this.handleChange}}/>
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
          </div>
        </div>
      </div>
    </div>
  );
};
export default ExpenseReport;

// import React, {Component} from 'react';
//
// class ExpenseReport extends Component{
//
//   state = {
//     isLoading: true,
//     Categories: []
//   }
//
//   async componentDidMount(){
//     const response = await fetch('api/categories')
//     const body = await response.json();
//     this.setState({Categories: body, isLoading:false});
//   }
//   render(){
//     const{Categories, isLoading} = this.state;
//     if(isLoading)
//       return(<div>Loading...</div> );
//
//     return (
//
//       <div>
//         {/*Every category should return a div*/}
//         <h2>Categories</h2>
//         {
//           Categories.map(category =>
//             <div id={category.id}>
//               {category.name}
//             </div>
//           )
//         }
//       </div>
//
//     )
//   }
// }
// export default ExpenseReport;
