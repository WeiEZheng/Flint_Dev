import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IBankAccount } from 'app/shared/model/bank-account.model';
import { getEntities as getBankAccounts } from 'app/entities/bank-account/bank-account.reducer';
import { IStatements } from 'app/shared/model/statements.model';
import { getEntity, updateEntity, createEntity, reset } from './statements.reducer';

export const StatementsUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const bankAccounts = useAppSelector(state => state.bankAccount.entities);
  const statementsEntity = useAppSelector(state => state.statements.entity);
  const loading = useAppSelector(state => state.statements.loading);
  const updating = useAppSelector(state => state.statements.updating);
  const updateSuccess = useAppSelector(state => state.statements.updateSuccess);
  const handleClose = () => {
    props.history.push('/statements' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getBankAccounts({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...statementsEntity,
      ...values,
      bankAccount: bankAccounts.find(it => it.id.toString() === values.bankAccount.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...statementsEntity,
          bankAccount: statementsEntity?.bankAccount?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="flintApp.statements.home.createOrEditLabel" data-cy="StatementsCreateUpdateHeading">
            <Translate contentKey="flintApp.statements.home.createOrEditLabel">Create or edit a Statements</Translate>
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
                  id="statements-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('flintApp.statements.startDate')}
                id="statements-startDate"
                name="startDate"
                data-cy="startDate"
                type="date"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('flintApp.statements.endDate')}
                id="statements-endDate"
                name="endDate"
                data-cy="endDate"
                type="date"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                id="statements-bankAccount"
                name="bankAccount"
                data-cy="bankAccount"
                label={translate('flintApp.statements.bankAccount')}
                type="select"
              >
                <option value="" key="0" />
                {bankAccounts
                  ? bankAccounts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.accountNumber}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/statements" replace color="info">
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

export default StatementsUpdate;
