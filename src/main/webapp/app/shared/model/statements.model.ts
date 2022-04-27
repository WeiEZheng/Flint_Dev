import dayjs from 'dayjs';
import { IBankAccount } from 'app/shared/model/bank-account.model';
import { ITransactions } from 'app/shared/model/transactions.model';

export interface IStatements {
  id?: number;
  startDate?: string;
  endDate?: string;
  bankAccount?: IBankAccount | null;
  transactions?: ITransactions[] | null;
}

export const defaultValue: Readonly<IStatements> = {};
