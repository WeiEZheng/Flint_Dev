export interface Transaction {
    id?: number;
    fromAccountNumber?: number;
    toAccountNumber?: number | null;
    transactionAmount?: number;
    typeOfTransaction?: string;
    dateOfTransaction?: string;
    categoryId?: number;
    userId?: number;
  }
  
  export const defaultValue: Readonly<Transaction> = {};