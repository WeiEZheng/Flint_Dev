import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './transactions.reducer';

export const TransactionsDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const transactionsEntity = useAppSelector(state => state.transactions.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="transactionsDetailsHeading">
          <Translate contentKey="flintApp.transactions.detail.title">Transactions</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{transactionsEntity.id}</dd>
          <dt>
            <span id="dateOfTransaction">
              <Translate contentKey="flintApp.transactions.dateOfTransaction">Date Of Transaction</Translate>
            </span>
          </dt>
          <dd>
            {transactionsEntity.dateOfTransaction ? (
              <TextFormat value={transactionsEntity.dateOfTransaction} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="typeOfTransaction">
              <Translate contentKey="flintApp.transactions.typeOfTransaction">Type Of Transaction</Translate>
            </span>
          </dt>
          <dd>{transactionsEntity.typeOfTransaction}</dd>
          <dt>
            <span id="transactionAmount">
              <Translate contentKey="flintApp.transactions.transactionAmount">Transaction Amount</Translate>
            </span>
          </dt>
          <dd>{transactionsEntity.transactionAmount}</dd>
          <dt>
            <span id="toAccountNumber">
              <Translate contentKey="flintApp.transactions.toAccountNumber">To Account Number</Translate>
            </span>
          </dt>
          <dd>{transactionsEntity.toAccountNumber}</dd>
          <dt>
            <span id="fromAccountNumber">
              <Translate contentKey="flintApp.transactions.fromAccountNumber">From Account Number</Translate>
            </span>
          </dt>
          <dd>{transactionsEntity.fromAccountNumber}</dd>
          <dt>
            <Translate contentKey="flintApp.transactions.statements">Statements</Translate>
          </dt>
          <dd>{transactionsEntity.statements ? transactionsEntity.statements.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/transactions" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/transactions/${transactionsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default TransactionsDetail;
