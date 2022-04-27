import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './bank-account.reducer';

export const BankAccountDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const bankAccountEntity = useAppSelector(state => state.bankAccount.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="bankAccountDetailsHeading">
          <Translate contentKey="flintApp.bankAccount.detail.title">BankAccount</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{bankAccountEntity.id}</dd>
          <dt>
            <span id="accountNumber">
              <Translate contentKey="flintApp.bankAccount.accountNumber">Account Number</Translate>
            </span>
          </dt>
          <dd>{bankAccountEntity.accountNumber}</dd>
          <dt>
            <span id="accountName">
              <Translate contentKey="flintApp.bankAccount.accountName">Account Name</Translate>
            </span>
          </dt>
          <dd>{bankAccountEntity.accountName}</dd>
          <dt>
            <span id="balance">
              <Translate contentKey="flintApp.bankAccount.balance">Balance</Translate>
            </span>
          </dt>
          <dd>{bankAccountEntity.balance}</dd>
          <dt>
            <span id="accountType">
              <Translate contentKey="flintApp.bankAccount.accountType">Account Type</Translate>
            </span>
          </dt>
          <dd>{bankAccountEntity.accountType}</dd>
          <dt>
            <Translate contentKey="flintApp.bankAccount.user">User</Translate>
          </dt>
          <dd>{bankAccountEntity.user ? bankAccountEntity.user.login : ''}</dd>
        </dl>
        <Button tag={Link} to="/bank-account" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/bank-account/${bankAccountEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default BankAccountDetail;
