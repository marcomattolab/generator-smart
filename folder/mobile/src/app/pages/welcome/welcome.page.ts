import {Component, OnInit} from '@angular/core';
import {ToastController} from '@ionic/angular';
import {ApiService} from '../../services/api/api.service';

@Component({
  selector: 'app-welcome',
  templateUrl: 'welcome.page.html',
  styleUrls: ['welcome.page.scss']
})
export class WelcomePage implements OnInit {
  private taps = 0;

  private static getInfos(): string {
    return `api url: ${ApiService.API_URL} ;
      `;
  }

  constructor(public toastController: ToastController) {
  }

  ngOnInit() {
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
}
