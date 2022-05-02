import { ReducersMapObject, combineReducers } from '@reduxjs/toolkit';

import transactionsReducer from '../components/Transactions/transactions.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const rootReducer: ReducersMapObject = {
  transactionsReducer,
};

export default rootReducer;