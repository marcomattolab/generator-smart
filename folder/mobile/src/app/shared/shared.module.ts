import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import {HasAnyAuthorityDirective} from './auth/has-any-authority.directive';

@NgModule({
  imports: [],
  declarations: [HasAnyAuthorityDirective],
  entryComponents: [],
  exports: [HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SharedModule {
  static forRoot() {
    return {
      ngModule: SharedModule
    };
  }
}
