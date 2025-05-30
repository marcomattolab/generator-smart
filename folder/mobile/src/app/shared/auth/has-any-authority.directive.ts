import { Directive, Input, TemplateRef, ViewContainerRef } from '@angular/core';
import {AccountService} from '../../services/auth/account.service';

/**
 * @whatItDoes Conditionally includes an HTML element if current user has any
 * of the authorities passed as the `expression`.
 *
 * @howToUse
 * ```
 *     <some-element *jhiHasAnyAuthority="'ROLE_ADMIN'">...</some-element>
 *
 *     <some-element *jhiHasAnyAuthority="['ROLE_ADMIN', 'ROLE_USER']">...</some-element>
 * ```
 */
@Directive({
  selector: '[appHasAnyAuthority]'
})
export class HasAnyAuthorityDirective {
  private authorities: string[];

  constructor(private accountService: AccountService, private templateRef: TemplateRef<any>, private viewContainerRef: ViewContainerRef) {}

  @Input()
  set appHasAnyAuthority(value: string | string[]) {
    this.authorities = typeof value === 'string' ? [value] : value;
    this.updateView();
    // Get notified each time authentication state changes.
    this.accountService.getAuthenticationState().subscribe(identity => this.updateView(identity));
  }

  private updateView(identity = null): void {
    const hasAnyAuthorityPromis = this.accountService.hasAnyAuthority(this.authorities);
    this.viewContainerRef.clear();
    hasAnyAuthorityPromis.then(hasAnyAuthority => {
      if (hasAnyAuthority) {
        this.viewContainerRef.createEmbeddedView(this.templateRef);
      }
    }).catch(reason => this.viewContainerRef.clear());
  }
}
