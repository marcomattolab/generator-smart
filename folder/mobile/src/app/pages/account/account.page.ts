import {Component, OnInit} from '@angular/core';
import {AccountService} from '../../services/auth/account.service';
import {Account} from '../../../model/account.model';
import {NavController, Platform} from '@ionic/angular';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-account',
  templateUrl: 'account.page.html',
  styleUrls: ['account.page.scss']
})
export class AccountPage implements OnInit {
  private backButtonSubscription: Subscription;
  account: Account;

  constructor(
     private accountService: AccountService,
     public navController: NavController,
     private platform: Platform) {
  }

  ngOnInit() {
    this.accountService.identity().then(account => {
      if (account !== null) {
        this.account = account;
      }
    });
  }

  ionViewDidEnter() {
    this.backButtonSubscription = this.platform.backButton.subscribe(async () => {
      this.navController.navigateRoot('/tabs/home');
    });
  }

  ionViewWillLeave() {
    this.backButtonSubscription.unsubscribe();
  }
}
