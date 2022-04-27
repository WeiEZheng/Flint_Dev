import { IUser } from 'app/shared/model/user.model';
import { IStatements } from 'app/shared/model/statements.model';
import { AccountType } from 'app/shared/model/enumerations/account-type.model';

export interface IBankAccount {
  id?: number;
  accountNumber?: string | null;
  accountName?: string;
  balance?: number;
  accountType?: AccountType | null;
  user?: IUser | null;
  statements?: IStatements[] | null;
}

export const defaultValue: Readonly<IBankAccount> = {};
