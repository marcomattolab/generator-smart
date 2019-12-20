import {Component, OnDestroy, OnInit} from '@angular/core';
import {Publisher} from './publisher.model';
import {PublisherService} from './publisher.service';
import {Subscription} from 'rxjs';
import {IonItemSliding, LoadingController, NavController, ToastController} from '@ionic/angular';
import {Router} from '@angular/router';
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app-publisher',
  templateUrl: './publisher.page.html',
  styleUrls: ['./publisher.page.scss'],
})
export class PublisherPage implements OnInit, OnDestroy {
  publishers: Array<Publisher> = [];
  private getAllSubscription: Subscription;
  private deleteSubscription: Subscription;
  private genericErrorString = '';

  constructor(
    private publisherService: PublisherService,
    private loadingCtrl: LoadingController,
    private translateService: TranslateService,
    private navCtrl: NavController,
    private toastController: ToastController,
    private router: Router) {
  }

  ngOnInit() {
    this.translateService.get('LOGIN_ERROR').subscribe(value => {
      this.genericErrorString = value;
    });
  }

  ionViewDidEnter() {
    this.loadPublishers();
  }

  private loadPublishers() {
    this.loadingCtrl.create({keyboardClose: false}).then(loadingEl => {
      loadingEl.present();

      this.getAllSubscription = this.publisherService.getAll().subscribe(publishers => {
        loadingEl.dismiss();
        this.publishers = publishers;
      }, error => {
        loadingEl.dismiss();
      });
    });
  }

  ngOnDestroy(): void {
    this.getAllSubscription.unsubscribe();
    if (this.deleteSubscription) {
      this.deleteSubscription.unsubscribe();
    }
  }

  view(publisher: Publisher) {
    console.log('view', publisher);
  }

  onEdit(publisher: Publisher, slidingEl: IonItemSliding) {
    slidingEl.close();
    this.router.navigate(['/', 'tabs', 'entities', 'publisher', 'edit', publisher.id]);
  }

  onDelete(publisher: Publisher, slidingEl: IonItemSliding) {
    slidingEl.close();
    this.loadingCtrl.create({keyboardClose: false})
      .then(loadingEl => {
        loadingEl.present();

        this.deleteSubscription = this.publisherService.delete(publisher.id).subscribe(
          () => {
            this.translateService.get('PUBLISHER.DELETE_SUCCESS', {name: publisher.name})
              .subscribe(async (successMessage: string) => {
                this.loadPublishers();
                loadingEl.dismiss();

                const toast = await this.toastController.create({
                  message: successMessage,
                  duration: 3000,
                  position: 'top'
                });
                toast.present();
            });
          },
          async error => {
            loadingEl.dismiss();

            const toast = await this.toastController.create({
              message: error.statusText || this.genericErrorString,
              duration: 3000,
              position: 'top'
            });
            toast.present();
          }
        );
      });
  }

  // newEntity() {
  //
  // }
}
