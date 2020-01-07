import { Component } from '@angular/core';
import { NavController } from '@ionic/angular';

@Component({
  selector: 'app-entities',
  templateUrl: 'entities.page.html',
  styleUrls: ['entities.page.scss']
})
export class EntitiesPage {
  entities: Array<any> = [
    {name: 'Trasferta', component: 'TrasfertaPage', route: 'trasferta'},
    {name: 'Spesa', component: 'SpesaPage', route: 'spesa'},
    {name: 'Feedback', component: 'FeedbackPage', route: 'feedback'},
    {name: 'Struttura', component: 'StrutturaPage', route: 'struttura'},
    {name: 'Giustificativo', component: 'GiustificativoPage', route: 'giustificativo'}
  ];

  allentities: Array<any> = [
    {name: 'Trasferta', component: 'TrasfertaPage', route: 'trasferta'},
    {name: 'Commessa', component: 'CommessaPage', route: 'commessa'},
    {name: 'Spesa', component: 'SpesaPage', route: 'spesa'},
    {name: 'Persona', component: 'PersonaPage', route: 'persona'},
    {name: 'Feedback', component: 'FeedbackPage', route: 'feedback'},
    {name: 'Struttura', component: 'StrutturaPage', route: 'struttura'},
    {name: 'Giustificativo', component: 'GiustificativoPage', route: 'giustificativo'},
    {name: 'Luogo', component: 'LuogoPage', route: 'luogo'},
    {name: 'ConfigurationData', component: 'ConfigurationDataPage', route: 'configuration-data'}
  ];

  constructor(public navController: NavController) {}

  openPage(page) {
    this.navController.navigateForward('/tabs/entities/' + page.route);
  }

}
