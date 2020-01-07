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

import { StrutturaPage } from './struttura';
import { StrutturaUpdatePage } from './struttura-update';
import { Struttura, StrutturaService, StrutturaDetailPage } from '.';

@Injectable({ providedIn: 'root' })
export class StrutturaResolve implements Resolve<Struttura> {
  constructor(private service: StrutturaService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Struttura> {
    const id = route.params.id ? route.params.id : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Struttura>) => response.ok),
        map((struttura: HttpResponse<Struttura>) => struttura.body)
      );
    }
    return of(new Struttura());
  }
}

const routes: Routes = [
    {
      path: '',
      component: StrutturaPage,
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    },
    {
      path: 'new',
      component: StrutturaUpdatePage,
      resolve: {
        data: StrutturaResolve
      },
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    },
    {
      path: ':id/view',
      component: StrutturaDetailPage,
      resolve: {
        data: StrutturaResolve
      },
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    },
    {
      path: ':id/edit',
      component: StrutturaUpdatePage,
      resolve: {
        data: StrutturaResolve
      },
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    }
  ];


@NgModule({
    declarations: [
        StrutturaPage,
        StrutturaUpdatePage,
        StrutturaDetailPage
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
export class StrutturaPageModule {
}
