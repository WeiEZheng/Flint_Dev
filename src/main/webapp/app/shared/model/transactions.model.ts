import dayjs from 'dayjs';
import { IStatements } from 'app/shared/model/statements.model';
import { TransactionType } from 'app/shared/model/enumerations/transaction-type.model';

export interface ITransactions {
  id?: number;
  dateOfTransaction?: string;
  typeOfTransaction?: TransactionType;
  transactionAmount?: number | null;
  toAccountNumber?: string | null;
  fromAccountNumber?: string;
  statements?: IStatements | null;
}

export const defaultValue: Readonly<ITransactions> = {};
