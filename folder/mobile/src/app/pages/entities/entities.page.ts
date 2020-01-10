import {Component} from '@angular/core';
import {NavController} from '@ionic/angular';
import {UserRole} from '../../services/user/user.model';

interface EntityItem {
  labelKey: string;
  component: string;
  route: string;
  authorizedRoles: Array<UserRole>;
  icon: string;
}

@Component({
  selector: 'app-entities',
  templateUrl: 'entities.page.html',
  styleUrls: ['entities.page.scss']
})
export class EntitiesPage {
  allEntities: Array<EntityItem> = [
    {
      labelKey: 'TRANSFER.TITLE',
      component: 'TrasfertaPage',
      route: 'trasferta',
      authorizedRoles: [UserRole.ROLE_ADMIN, UserRole.ROLE_PM, UserRole.ROLE_OFFICER, UserRole.ROLE_USER],
      icon: 'airplane'
    },
    {
      labelKey: 'JOB_ORDER.TITLE',
      component: 'CommessaPage',
      route: 'commessa',
      authorizedRoles: [UserRole.ROLE_ADMIN, UserRole.ROLE_OFFICER, UserRole.ROLE_PM],
      icon: 'document'
    },
    {
      labelKey: 'EXPENDITURE.TITLE',
      component: 'SpesaPage',
      route: 'spesa',
      authorizedRoles: [UserRole.ROLE_ADMIN, UserRole.ROLE_PM, UserRole.ROLE_OFFICER, UserRole.ROLE_USER],
      icon: 'card'
    },
    // {name: 'Persona', component: 'PersonaPage', route: 'persona',
    // authorizedRoles: ['ROLE_ADMIN,' 'ROLE_USER'', 'ROLE_OFFICER], 'ROLE_PM}
    {
      labelKey: 'FEEDBACK.TITLE',
      component: 'FeedbackPage',
      route: 'feedback',
      authorizedRoles: [UserRole.ROLE_ADMIN, UserRole.ROLE_PM, UserRole.ROLE_OFFICER, UserRole.ROLE_USER],
      icon: 'thumbs-up'
    },
    {
      labelKey: 'ACCOMMODATION.TITLE',
      component: 'StrutturaPage',
      route: 'struttura',
      authorizedRoles: [UserRole.ROLE_ADMIN, UserRole.ROLE_PM, UserRole.ROLE_OFFICER, UserRole.ROLE_USER],
      icon: 'business'
    },
    {
      labelKey: 'JUSTIFICATIVE.TITLE',
      component: 'GiustificativoPage',
      route: 'giustificativo',
      authorizedRoles: [UserRole.ROLE_ADMIN, UserRole.ROLE_PM, UserRole.ROLE_OFFICER, UserRole.ROLE_USER],
      icon: 'paper'
    },
    {
      labelKey: 'PLACE.TITLE',
      component: 'LuogoPage',
      route: 'luogo',
      authorizedRoles: [UserRole.ROLE_ADMIN, UserRole.ROLE_PM, UserRole.ROLE_OFFICER, UserRole.ROLE_USER],
      icon: 'pin'
    },
    {
      labelKey: 'CONFIGURATION_DATA.TITLE',
      component: 'ConfigurationDataPage',
      route: 'configuration-data',
      authorizedRoles: [UserRole.ROLE_ADMIN],
      icon: 'settings'
    }
  ];

  constructor(public navController: NavController) {
  }

  openPage(page) {
    this.navController.navigateForward('/tabs/entities/' + page.route);
  }

}
