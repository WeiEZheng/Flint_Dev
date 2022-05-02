import React, { useState, useEffect } from 'react';
import { Link} from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { useAppDispatch, useAppSelector } from '../../config/store';
import { getEntities } from './transactions.reducer';
import { Transaction } from './transactions.model';

export const Transactions = () => {
  const dispatch = useAppDispatch();

  const transactionList = useAppSelector(state => state.transaction.entities);
  const loading = useAppSelector(state => state.transaction.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      {transactionList.map((transaction, i) => ( 
        <tr key={`entity-${i}`} data-cy="entityTable">
           <td>
             {transaction.id}
          </td>
          </tr>
      ))}
  </div>
  );
};

export default Transactions;
