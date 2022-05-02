import { createAsyncThunk, isFulfilled, isPending, isRejected } from '@reduxjs/toolkit';
import axios from 'axios';
import { Transaction } from './transactions.model';

const apiUrl = 'api/transactions';

export const getEntities = createAsyncThunk('transactions/fetch_transaction_list', async () => {
    const requestUrl = `${apiUrl}`;
    return axios.get<Transaction[]>(requestUrl);
});

export const getEntity = createAsyncThunk(
  'transactions/fetch_transaction',
  async (id: string | number) => {
    const requestUrl = `${apiUrl}/${id}`;
    return axios.get<Transaction>(requestUrl);
  }
);

export const createEntity = createAsyncThunk(
  'transactions/create_transaction',
  async (transactions: Transaction, thunkAPI) => {
    const result = await axios.post<Transaction>(apiUrl, transactions);
    thunkAPI.dispatch(getEntities());
    return result;
  }
);

export const deleteEntity = createAsyncThunk(
  'transactions/delete_transaction',
  async (id: string | number, thunkAPI) => {
    const requestUrl = `${apiUrl}/${id}`;
    const result = await axios.delete<Transaction>(requestUrl);
    thunkAPI.dispatch(getEntities());
    return result;
  }
);