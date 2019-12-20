import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {LoadingController, NavController, ToastController} from '@ionic/angular';
import {PublisherService} from '../publisher.service';
import {Subscription} from 'rxjs';
import {Publisher} from '../publisher.model';
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app-publisher-new',
  templateUrl: './publisher-new.page.html',
  styleUrls: ['./publisher-new.page.scss'],
})
export class PublisherNewPage implements OnInit, OnDestroy {
  isLoading = false;
  private publisher: Publisher;
  private saveSubscription: Subscription;
  private genericErrorString = '';

  constructor(
    private navCtrl: NavController,
    private route: ActivatedRoute,
    private loadingCtrl: LoadingController,
    private translateService: TranslateService,
    private toastController: ToastController,
    private publisherService: PublisherService) {
  }

  ngOnInit() {
    this.publisher = {name: '', id: null};
    // this.route.paramMap.subscribe(paramMap => {
    //   if (!paramMap.has('publisherId')) {
    //     this.navCtrl.navigateBack('/tabs/entities/publisher');
    //     return;
    //   }
    //   this.isLoading = true;
    //   this.loadSubscription = this.publisherService.get(paramMap.get('publisherId')).subscribe(
    //     publisher => {
    //       this.publisher = publisher;
    //       this.isLoading = false;
    //     },
    //     error => {
    //       this.isLoading = false;
    //     }
    //   );
    // });
  }

  ngOnDestroy(): void {

    if (this.saveSubscription) {
      this.saveSubscription.unsubscribe();
    }
  }

  onCancel() {
    this.navCtrl.back();
  }

  onSave() {
    this.loadingCtrl.create({keyboardClose: false})
      .then(loadingEl => {
        loadingEl.present();

        this.saveSubscription = this.publisherService.new(this.publisher).subscribe(
          publisher => {
            loadingEl.dismiss();
            this.navCtrl.navigateBack('/tabs/entities/publisher');
          },
          async error => {
            const toast = await this.toastController.create({
              message: error.statusText || this.genericErrorString,
              duration: 3000,
              position: 'top'
            });
            toast.present();
            loadingEl.dismiss();
          });
      });
  }
}
