import React from 'react';
import { Switch } from 'react-router-dom';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BankAccount from './bank-account';
import Statements from './statements';
import Transactions from './transactions';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default ({ match }) => {
  return (
    <div>
      <Switch>
        {/* prettier-ignore */}
        <ErrorBoundaryRoute path={`${match.url}bank-account`} component={BankAccount} />
        <ErrorBoundaryRoute path={`${match.url}statements`} component={Statements} />
        <ErrorBoundaryRoute path={`${match.url}transactions`} component={Transactions} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </Switch>
    </div>
  );
};
