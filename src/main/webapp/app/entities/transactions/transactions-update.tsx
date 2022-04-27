import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IStatements } from 'app/shared/model/statements.model';
import { getEntities as getStatements } from 'app/entities/statements/statements.reducer';
import { ITransactions } from 'app/shared/model/transactions.model';
import { TransactionType } from 'app/shared/model/enumerations/transaction-type.model';
import { getEntity, updateEntity, createEntity, reset } from './transactions.reducer';

export const TransactionsUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const statements = useAppSelector(state => state.statements.entities);
  const transactionsEntity = useAppSelector(state => state.transactions.entity);
  const loading = useAppSelector(state => state.transactions.loading);
  const updating = useAppSelector(state => state.transactions.updating);
  const updateSuccess = useAppSelector(state => state.transactions.updateSuccess);
  const transactionTypeValues = Object.keys(TransactionType);
  const handleClose = () => {
    props.history.push('/transactions' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getStatements({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.dateOfTransaction = convertDateTimeToServer(values.dateOfTransaction);

    const entity = {
      ...transactionsEntity,
      ...values,
      statements: statements.find(it => it.id.toString() === values.statements.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          dateOfTransaction: displayDefaultDateTime(),
        }
      : {
          typeOfTransaction: 'DEBIT',
          ...transactionsEntity,
          dateOfTransaction: convertDateTimeFromServer(transactionsEntity.dateOfTransaction),
          statements: transactionsEntity?.statements?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="flintApp.transactions.home.createOrEditLabel" data-cy="TransactionsCreateUpdateHeading">
            <Translate contentKey="flintApp.transactions.home.createOrEditLabel">Create or edit a Transactions</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="transactions-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('flintApp.transactions.dateOfTransaction')}
                id="transactions-dateOfTransaction"
                name="dateOfTransaction"
                data-cy="dateOfTransaction"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('flintApp.transactions.typeOfTransaction')}
                id="transactions-typeOfTransaction"
                name="typeOfTransaction"
                data-cy="typeOfTransaction"
                type="select"
              >
                {transactionTypeValues.map(transactionType => (
                  <option value={transactionType} key={transactionType}>
                    {translate('flintApp.TransactionType.' + transactionType)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('flintApp.transactions.transactionAmount')}
                id="transactions-transactionAmount"
                name="transactionAmount"
                data-cy="transactionAmount"
                type="text"
                validate={{
                  min: { value: 0, message: translate('entity.validation.min', { min: 0 }) },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('flintApp.transactions.toAccountNumber')}
                id="transactions-toAccountNumber"
                name="toAccountNumber"
                data-cy="toAccountNumber"
                type="text"
              />
              <ValidatedField
                label={translate('flintApp.transactions.fromAccountNumber')}
                id="transactions-fromAccountNumber"
                name="fromAccountNumber"
                data-cy="fromAccountNumber"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                id="transactions-statements"
                name="statements"
                data-cy="statements"
                label={translate('flintApp.transactions.statements')}
                type="select"
              >
                <option value="" key="0" />
                {statements
                  ? statements.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/transactions" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default TransactionsUpdate;
