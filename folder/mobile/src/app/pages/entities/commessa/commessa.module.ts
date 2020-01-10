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

import { CommessaPage } from './commessa';
import { CommessaUpdatePage } from './commessa-update';
import { Commessa, CommessaService, CommessaDetailPage } from '.';

@Injectable({ providedIn: 'root' })
export class CommessaResolve implements Resolve<Commessa> {
  constructor(private service: CommessaService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Commessa> {
    const id = route.params.id ? route.params.id : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Commessa>) => response.ok),
        map((commessa: HttpResponse<Commessa>) => commessa.body)
      );
    }
    return of(new Commessa());
  }
}

const routes: Routes = [
    {
      path: '',
      component: CommessaPage,
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    },
    {
      path: 'new',
      component: CommessaUpdatePage,
      resolve: {
        data: CommessaResolve
      },
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    },
    {
      path: ':id/view',
      component: CommessaDetailPage,
      resolve: {
        data: CommessaResolve
      },
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    },
    {
      path: ':id/edit',
      component: CommessaUpdatePage,
      resolve: {
        data: CommessaResolve
      },
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    }
  ];


@NgModule({
    declarations: [
        CommessaPage,
        CommessaUpdatePage,
        CommessaDetailPage
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
export class CommessaPageModule {
}
