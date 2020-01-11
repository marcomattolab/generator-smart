import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {RouterModule, Routes} from '@angular/router';
import {IonicModule} from '@ionic/angular';
import {TranslateModule} from '@ngx-translate/core';
import {UserRouteAccessService} from 'src/app/services/auth/user-route-access.service';
import {EntitiesPage} from './entities.page';
import {SharedModule} from '../../shared/shared.module';

const routes: Routes = [
  {
    path: '',
    component: EntitiesPage,
    data: {
      authorities: ['ROLE_USER']
    },
    canActivate: [UserRouteAccessService]
  }
  , {
    path: 'trasferta',
    loadChildren: './trasferta/trasferta.module#TrasfertaPageModule'
  }
  , {
    path: 'commessa',
    loadChildren: './commessa/commessa.module#CommessaPageModule'
  }
  , {
    path: 'spesa',
    loadChildren: './spesa/spesa.module#SpesaPageModule'
  }
  // , {
  //   path: 'persona',
  //   loadChildren: './persona/persona.module#PersonaPageModule'
  // }
  , {
    path: 'feedback',
    loadChildren: './feedback/feedback.module#FeedbackPageModule'
  }
  , {
    path: 'struttura',
    loadChildren: './struttura/struttura.module#StrutturaPageModule'
  }
  , {
    path: 'giustificativo',
    loadChildren: './giustificativo/giustificativo.module#GiustificativoPageModule'
  }
  , {
    path: 'luogo',
    loadChildren: './luogo/luogo.module#LuogoPageModule'
  }
  , {
    path: 'configuration-data',
    loadChildren: './configuration-data/configuration-data.module#ConfigurationDataPageModule'
  }
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

@NgModule({
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    RouterModule.forChild(routes),
    TranslateModule,
    SharedModule
  ],
  declarations: [EntitiesPage]
})
export class EntitiesPageModule {
}
