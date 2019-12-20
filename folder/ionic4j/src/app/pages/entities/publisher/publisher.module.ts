import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {RouterModule, Routes} from '@angular/router';
import {IonicModule} from '@ionic/angular';
import {PublisherPage} from './publisher.page';
import {TranslateModule} from '@ngx-translate/core';

const routes: Routes = [
  {
    path: '',
    component: PublisherPage
  }, {
    path: 'new',
    loadChildren: './publisher-new/publisher-new.module#PublisherNewModule'
  }, {
    path: 'edit/:publisherId',
    loadChildren: './publisher-edit/publisher-edit.module#PublisherEditModule'
  }, {
    path: ':publisherId',
    loadChildren: './publisher-detail/publisher-detail.module#PublisherDetailModule'
  }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes),
    TranslateModule
  ],
  declarations: [PublisherPage]
})
export class PublisherPageModule {
}
