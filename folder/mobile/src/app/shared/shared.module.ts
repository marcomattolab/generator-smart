import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {HasAnyAuthorityDirective} from './auth/has-any-authority.directive';
import {ImagePickerComponent} from './pickers/image-picker/image-picker.component';
import {LocationPickerComponent} from './pickers/location-picker/location-picker.component';
import {MapModalComponent} from './map-modal/map-modal.component';
import {IonicModule} from '@ionic/angular';
import {CommonModule} from '@angular/common';
import {TranslateModule} from '@ngx-translate/core';
import {AndroidPermissions} from '@ionic-native/android-permissions/ngx';

@NgModule({
  imports: [CommonModule, IonicModule, TranslateModule],
  declarations: [HasAnyAuthorityDirective, ImagePickerComponent, LocationPickerComponent, MapModalComponent],
  entryComponents: [MapModalComponent],
  exports: [HasAnyAuthorityDirective, ImagePickerComponent, LocationPickerComponent, MapModalComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [AndroidPermissions]

})
export class SharedModule {
  static forRoot() {
    return {
      ngModule: SharedModule
    };
  }
}
