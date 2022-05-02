import { createAsyncThunk, isFulfilled, isPending, isRejected } from '@reduxjs/toolkit';
import axios from 'axios';
import { IQueryParams, createEntitySlice, serializeAxiosError, EntityState } from '../../config/reducer.utils';
import { defaultValue, Transaction } from './transactions.model';

const apiUrl = 'api/transactions';

const initialState: EntityState<Transaction> = {
  loading: false,
  errorMessage: null,
  entities: [],
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export const getEntities = createAsyncThunk('transactions/fetch_transaction_list', 
  async ({ page, size, sort }: IQueryParams) => {
    const requestUrl = `${apiUrl}`;
    return axios.get<Transaction[]>(requestUrl);
});

export const getEntity = createAsyncThunk(
  'transactions/fetch_transaction',
  async (id: string | number) => {
    const requestUrl = `${apiUrl}/${id}`;
    return axios.get<Transaction>(requestUrl);
  },
  { serializeError: serializeAxiosError }
);

export const createEntity = createAsyncThunk(
  'transactions/create_transaction',
  async (transactions: Transaction, thunkAPI) => {
    const result = await axios.post<Transaction>(apiUrl, transactions);
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError }
);

export const deleteEntity = createAsyncThunk(
  'transactions/delete_transaction',
  async (id: string | number, thunkAPI) => {
    const requestUrl = `${apiUrl}/${id}`;
    const result = await axios.delete<Transaction>(requestUrl);
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError }
);

export const TransactionSlice = createEntitySlice({
  name: 'transaction',
  initialState,
  extraReducers(builder) {
    builder
      .addCase(getEntity.fulfilled, (state, action) => {
        state.loading = false;
        state.entity = action.payload.data;
      })
      .addCase(deleteEntity.fulfilled, state => {
        state.updating = false;
        state.updateSuccess = true;
        state.entity = {};
      })
      .addMatcher(isFulfilled(getEntities), (state, action) => {
        const { data } = action.payload;

        return {
          ...state,
          loading: false,
          entities: data,
        };
      })
      .addMatcher(isFulfilled(createEntity), (state, action) => {
        state.updating = false;
        state.loading = false;
        state.updateSuccess = true;
        state.entity = action.payload.data;
      })
      .addMatcher(isPending(getEntities, getEntity), state => {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.loading = true;
      })
      .addMatcher(isPending(createEntity, deleteEntity), state => {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.updating = true;
      });
  },
});

export const { reset } = TransactionSlice.actions;

// Reducer
export default TransactionSlice.reducer;