import {Component, OnDestroy, OnInit} from '@angular/core';
import {NavController, Platform, ToastController} from '@ionic/angular';
import {ApiService} from '../../services/api/api.service';
import {Subscription} from 'rxjs';
import {TranslateService} from '@ngx-translate/core';
import {environment} from '../../../environments/environment';
import {AuthServerProvider} from '../../services/auth/auth-jwt.service';
import {AccountService} from '../../services/auth/account.service';
import {LoginService} from '../../services/login/login.service';

@Component({
  selector: 'app-welcome',
  templateUrl: 'welcome.page.html',
  styleUrls: ['welcome.page.scss']
})
export class WelcomePage implements OnInit {
  private taps = 0;
  private backButtonSubscription: Subscription;
  private exitAlertIsShown = false;
  private exitQuestion = '';
  private accountServiceSubscription: Subscription;

  pageLoaded = false;

  private static getInfos(): string {
    return `api url: ${ApiService.API_URL} ;
      `;
  }

  constructor(
    private translateService: TranslateService,
    private platform: Platform,
    private authServerProvider: AuthServerProvider,
    private navController: NavController,
    private accountService: AccountService,
    private loginService: LoginService,
    private toastController: ToastController
  ) {
  }

  ngOnInit() {
    this.translateService.get('EXIT_ASK').subscribe(value => {
      this.exitQuestion = value;
    });
  }

  ionViewDidEnter() {
    this.pageLoaded = false;

    if (this.authServerProvider.getToken()) {
      this.navController.navigateRoot('/tabs');
    } else {
      setTimeout(() => this.pageLoaded = true);
    }

    this.accountServiceSubscription = this.accountService.getAuthenticationState().subscribe(
      (identity) => {
        if (!identity) {
          this.pageLoaded = true;
        }
      }, error => {
        console.error(error);
      });

    this.backButtonSubscription = this.platform.backButton.subscribe(async () => {
      if (this.exitAlertIsShown) {
        const app = 'app';
        navigator[app].exitApp();
      } else {
        this.exitAlertIsShown = true;

        const exitAlertTimeout = 3000;

        const toast = await this.toastController.create({
          message: this.exitQuestion,
          duration: exitAlertTimeout,
          position: 'bottom'
        });
        toast.present();

        setTimeout(() => {
          this.exitAlertIsShown = false;
        }, exitAlertTimeout);
      }
    });
  }

  ionViewWillLeave() {
    this.accountServiceSubscription.unsubscribe();
    this.backButtonSubscription.unsubscribe();
  }

  async multipleTapToShowInfos() {
    setTimeout(() => {
      this.taps = 0;
    }, 2000);

    this.taps++;

    if (this.taps === 5) {
      const toast = await this.toastController.create({
        message: WelcomePage.getInfos(),
        duration: 2000,
        position: 'top'
      });
      toast.present();
    }
  }

  isDevelopmentMode() {
    return !environment.production;
  }
}
