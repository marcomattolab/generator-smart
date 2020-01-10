import {Component, OnInit} from '@angular/core';
import {AccountService} from '../../services/auth/account.service';
import {Account} from '../../../model/account.model';

@Component({
  selector: 'app-account',
  templateUrl: 'account.page.html',
  styleUrls: ['account.page.scss']
})
export class AccountPage implements OnInit {
  account: Account;

  constructor(private accountService: AccountService) {
  }

  ngOnInit() {
    this.accountService.identity().then(account => {
      if (account !== null) {
        this.account = account;
      }
    });
  }
}
