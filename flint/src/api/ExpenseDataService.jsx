import axios from "axios";

class ExpenseDataService{
  retrieveExpensesByUser(user){
    return axios.get(`http://localhost:8080/users/${user}/budget_tool`)
  }
}

export default new ExpenseDataService()
