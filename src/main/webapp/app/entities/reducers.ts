import bankAccount from 'app/entities/bank-account/bank-account.reducer';
import statements from 'app/entities/statements/statements.reducer';
import transactions from 'app/entities/transactions/transactions.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  bankAccount,
  statements,
  transactions,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
