import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Statements from './statements';
import StatementsDetail from './statements-detail';
import StatementsUpdate from './statements-update';
import StatementsDeleteDialog from './statements-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={StatementsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={StatementsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={StatementsDetail} />
      <ErrorBoundaryRoute path={match.url} component={Statements} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={StatementsDeleteDialog} />
  </>
);

export default Routes;
