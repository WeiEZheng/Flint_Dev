import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/bank-account">
        <Translate contentKey="global.menu.entities.bankAccount" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/statements">
        <Translate contentKey="global.menu.entities.statements" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/transactions">
        <Translate contentKey="global.menu.entities.transactions" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu as React.ComponentType<any>;
