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

import { SpesaPage } from './spesa';
import { SpesaUpdatePage } from './spesa-update';
import { Spesa, SpesaService, SpesaDetailPage } from '.';

@Injectable({ providedIn: 'root' })
export class SpesaResolve implements Resolve<Spesa> {
  constructor(private service: SpesaService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Spesa> {
    const id = route.params.id ? route.params.id : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Spesa>) => response.ok),
        map((spesa: HttpResponse<Spesa>) => spesa.body)
      );
    }
    return of(new Spesa());
  }
}

const routes: Routes = [
    {
      path: '',
      component: SpesaPage,
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    },
    {
      path: 'new',
      component: SpesaUpdatePage,
      resolve: {
        data: SpesaResolve
      },
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    },
    {
      path: ':id/view',
      component: SpesaDetailPage,
      resolve: {
        data: SpesaResolve
      },
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    },
    {
      path: ':id/edit',
      component: SpesaUpdatePage,
      resolve: {
        data: SpesaResolve
      },
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    }
  ];


@NgModule({
    declarations: [
        SpesaPage,
        SpesaUpdatePage,
        SpesaDetailPage
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
export class SpesaPageModule {
}
