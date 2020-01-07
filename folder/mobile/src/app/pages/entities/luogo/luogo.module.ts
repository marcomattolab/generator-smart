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

import { LuogoPage } from './luogo';
import { LuogoUpdatePage } from './luogo-update';
import { Luogo, LuogoService, LuogoDetailPage } from '.';

@Injectable({ providedIn: 'root' })
export class LuogoResolve implements Resolve<Luogo> {
  constructor(private service: LuogoService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Luogo> {
    const id = route.params.id ? route.params.id : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Luogo>) => response.ok),
        map((luogo: HttpResponse<Luogo>) => luogo.body)
      );
    }
    return of(new Luogo());
  }
}

const routes: Routes = [
    {
      path: '',
      component: LuogoPage,
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    },
    {
      path: 'new',
      component: LuogoUpdatePage,
      resolve: {
        data: LuogoResolve
      },
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    },
    {
      path: ':id/view',
      component: LuogoDetailPage,
      resolve: {
        data: LuogoResolve
      },
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    },
    {
      path: ':id/edit',
      component: LuogoUpdatePage,
      resolve: {
        data: LuogoResolve
      },
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    }
  ];


@NgModule({
    declarations: [
        LuogoPage,
        LuogoUpdatePage,
        LuogoDetailPage
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
export class LuogoPageModule {
}
