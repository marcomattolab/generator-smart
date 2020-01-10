import { NgModule, Injectable } from '@angular/core';
import { TranslateModule } from '@ngx-translate/core';
import { IonicModule } from '@ionic/angular';
import { Camera } from '@ionic-native/camera/ngx';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule, Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { UserRouteAccessService } from '../../../services/auth/user-route-access.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { filter, map } from 'rxjs/operators';

import { GiustificativoPage } from './giustificativo';
import { GiustificativoUpdatePage } from './giustificativo-update';
import { Giustificativo, GiustificativoService, GiustificativoDetailPage } from '.';

@Injectable({ providedIn: 'root' })
export class GiustificativoResolve implements Resolve<Giustificativo> {
  constructor(private service: GiustificativoService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Giustificativo> {
    const id = route.params.id ? route.params.id : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Giustificativo>) => response.ok),
        map((giustificativo: HttpResponse<Giustificativo>) => giustificativo.body)
      );
    }
    return of(new Giustificativo());
  }
}

const routes: Routes = [
    {
      path: '',
      component: GiustificativoPage,
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    },
    {
      path: 'new',
      component: GiustificativoUpdatePage,
      resolve: {
        data: GiustificativoResolve
      },
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    },
    {
      path: ':id/view',
      component: GiustificativoDetailPage,
      resolve: {
        data: GiustificativoResolve
      },
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    },
    {
      path: ':id/edit',
      component: GiustificativoUpdatePage,
      resolve: {
        data: GiustificativoResolve
      },
      data: {
        authorities: ['ROLE_USER']
      },
      canActivate: [UserRouteAccessService]
    }
  ];


@NgModule({
    declarations: [
        GiustificativoPage,
        GiustificativoUpdatePage,
        GiustificativoDetailPage
    ],
    imports: [
        IonicModule,
        FormsModule,
        ReactiveFormsModule,
        CommonModule,
        TranslateModule,
        RouterModule.forChild(routes)
    ],
    providers: [Camera]
})
export class GiustificativoPageModule {
}
