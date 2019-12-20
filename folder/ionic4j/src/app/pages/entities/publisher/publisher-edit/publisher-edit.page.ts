import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {LoadingController, NavController, ToastController} from '@ionic/angular';
import {PublisherService} from '../publisher.service';
import {Subscription} from 'rxjs';
import {Publisher} from '../publisher.model';
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app-detail',
  templateUrl: './publisher-edit.page.html',
  styleUrls: ['./publisher-edit.page.scss'],
})
export class PublisherEditPage implements OnInit, OnDestroy {
  isLoading = false;
  private publisher: Publisher;
  private loadSubscription: Subscription;
  private saveSubscription: Subscription;
  private genericErrorString = '';

  public oldName = '';

  constructor(
    private navCtrl: NavController,
    private route: ActivatedRoute,
    private translateService: TranslateService,
    private toastController: ToastController,
    private loadingCtrl: LoadingController,
    private publisherService: PublisherService) {
  }

  ngOnInit() {
    this.translateService.get('LOGIN_ERROR').subscribe(value => {
      this.genericErrorString = value;
    });

    this.route.paramMap.subscribe(paramMap => {
      if (!paramMap.has('publisherId')) {
        this.navCtrl.navigateBack('/tabs/entities/publisher');
        return;
      }
      this.isLoading = true;
      this.loadSubscription = this.publisherService.get(paramMap.get('publisherId')).subscribe(
        publisher => {
          this.publisher = publisher;
          this.oldName = String(publisher.name);
          this.isLoading = false;
        },
        async error => {
          const toast = await this.toastController.create({
            message: error.statusText || this.genericErrorString,
            duration: 3000,
            position: 'top'
          });
          toast.present();

          this.isLoading = false;
        }
      );
    });
  }

  ngOnDestroy(): void {
    this.loadSubscription.unsubscribe();

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

        this.saveSubscription = this.publisherService.save(this.publisher).subscribe(
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
