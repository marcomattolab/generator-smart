import { NgModule, Injectable } from '@angular/core';
import { TranslateModule } from '@ngx-translate/core';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule, Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { UserRouteAccessService } from '../../../services/auth/user-route-access.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { filter, map } from 'rxjs/operators';

import { ConfigurationDataPage } from './configuration-data';
import { ConfigurationDataUpdatePage } from './configuration-data-update';
import { ConfigurationData, ConfigurationDataService, ConfigurationDataDetailPage } from '.';

@Injectable({ providedIn: 'root' })
export class ConfigurationDataResolve implements Resolve<ConfigurationData> {
  constructor(private service: ConfigurationDataService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ConfigurationData> {
    const id = route.params.id ? route.params.id : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ConfigurationData>) => response.ok),
        map((configurationData: HttpResponse<ConfigurationData>) => configurationData.body)
      );
    }
    return of(new ConfigurationData());
  }
}

const routes: Routes = [
    {
      path: '',
      component: ConfigurationDataPage,
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    },
    {
      path: 'new',
      component: ConfigurationDataUpdatePage,
      resolve: {
        data: ConfigurationDataResolve
      },
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    },
    {
      path: ':id/view',
      component: ConfigurationDataDetailPage,
      resolve: {
        data: ConfigurationDataResolve
      },
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    },
    {
      path: ':id/edit',
      component: ConfigurationDataUpdatePage,
      resolve: {
        data: ConfigurationDataResolve
      },
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    }
  ];


@NgModule({
    declarations: [
        ConfigurationDataPage,
        ConfigurationDataUpdatePage,
        ConfigurationDataDetailPage
    ],
    imports: [
        IonicModule,
        FormsModule,
        ReactiveFormsModule,
        CommonModule,
        TranslateModule,
        RouterModule.forChild(routes)
    ]
})
export class ConfigurationDataPageModule {
}
