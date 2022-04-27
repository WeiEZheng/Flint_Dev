import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { IBankAccount } from 'app/shared/model/bank-account.model';
import { AccountType } from 'app/shared/model/enumerations/account-type.model';
import { getEntity, updateEntity, createEntity, reset } from './bank-account.reducer';

export const BankAccountUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const users = useAppSelector(state => state.userManagement.users);
  const bankAccountEntity = useAppSelector(state => state.bankAccount.entity);
  const loading = useAppSelector(state => state.bankAccount.loading);
  const updating = useAppSelector(state => state.bankAccount.updating);
  const updateSuccess = useAppSelector(state => state.bankAccount.updateSuccess);
  const accountTypeValues = Object.keys(AccountType);
  const handleClose = () => {
    props.history.push('/bank-account');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...bankAccountEntity,
      ...values,
      user: users.find(it => it.id.toString() === values.user.toString()),
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
          accountType: 'CHECKING',
          ...bankAccountEntity,
          user: bankAccountEntity?.user?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="flintApp.bankAccount.home.createOrEditLabel" data-cy="BankAccountCreateUpdateHeading">
            <Translate contentKey="flintApp.bankAccount.home.createOrEditLabel">Create or edit a BankAccount</Translate>
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
                  id="bank-account-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('flintApp.bankAccount.accountNumber')}
                id="bank-account-accountNumber"
                name="accountNumber"
                data-cy="accountNumber"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('flintApp.bankAccount.accountName')}
                id="bank-account-accountName"
                name="accountName"
                data-cy="accountName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('flintApp.bankAccount.balance')}
                id="bank-account-balance"
                name="balance"
                data-cy="balance"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('flintApp.bankAccount.accountType')}
                id="bank-account-accountType"
                name="accountType"
                data-cy="accountType"
                type="select"
              >
                {accountTypeValues.map(accountType => (
                  <option value={accountType} key={accountType}>
                    {translate('flintApp.AccountType.' + accountType)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                id="bank-account-user"
                name="user"
                data-cy="user"
                label={translate('flintApp.bankAccount.user')}
                type="select"
              >
                <option value="" key="0" />
                {users
                  ? users.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.login}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/bank-account" replace color="info">
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

export default BankAccountUpdate;
