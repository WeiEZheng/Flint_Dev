import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './statements.reducer';

export const StatementsDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const statementsEntity = useAppSelector(state => state.statements.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="statementsDetailsHeading">
          <Translate contentKey="flintApp.statements.detail.title">Statements</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{statementsEntity.id}</dd>
          <dt>
            <span id="startDate">
              <Translate contentKey="flintApp.statements.startDate">Start Date</Translate>
            </span>
          </dt>
          <dd>
            {statementsEntity.startDate ? (
              <TextFormat value={statementsEntity.startDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="endDate">
              <Translate contentKey="flintApp.statements.endDate">End Date</Translate>
            </span>
          </dt>
          <dd>
            {statementsEntity.endDate ? <TextFormat value={statementsEntity.endDate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <Translate contentKey="flintApp.statements.bankAccount">Bank Account</Translate>
          </dt>
          <dd>{statementsEntity.bankAccount ? statementsEntity.bankAccount.accountNumber : ''}</dd>
        </dl>
        <Button tag={Link} to="/statements" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/statements/${statementsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default StatementsDetail;
