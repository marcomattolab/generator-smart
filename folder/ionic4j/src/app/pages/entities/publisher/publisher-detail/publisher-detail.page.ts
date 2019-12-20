import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {LoadingController, NavController, ToastController} from '@ionic/angular';
import {PublisherService} from '../publisher.service';
import {Subscription} from 'rxjs';
import {Publisher} from '../publisher.model';
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app-detail',
  templateUrl: './publisher-detail.page.html',
  styleUrls: ['./publisher-detail.page.scss'],
})
export class PublisherDetailPage implements OnInit, OnDestroy {
  private loadSubscription: Subscription;
  private deleteSubscription: Subscription;
  private publisher: Publisher;
  private genericErrorString = '';

  constructor(
    private navCtrl: NavController,
    private translateService: TranslateService,
    private route: ActivatedRoute,
    private toastController: ToastController,
    private loadingCtrl: LoadingController,
    private router: Router,
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

      this.loadingCtrl.create({keyboardClose: false})
        .then(loadingEl => {
          loadingEl.present();

          this.loadSubscription = this.publisherService.get(paramMap.get('publisherId')).subscribe(
            publisher => {
              this.publisher = publisher;
              loadingEl.dismiss();
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
    });
  }

  ngOnDestroy(): void {
    this.loadSubscription.unsubscribe();

    if (this.deleteSubscription) {
      this.deleteSubscription.unsubscribe();
    }
  }

  onEdit(publisher: Publisher) {
    this.router.navigate(['/', 'tabs', 'entities', 'publisher', 'edit', publisher.id]);
  }

  onDelete(publisher: Publisher) {
    this.loadingCtrl.create({keyboardClose: false})
      .then(loadingEl => {
        loadingEl.present();

        this.deleteSubscription = this.publisherService.delete(publisher.id).subscribe(
          () => {
            this.navCtrl.navigateBack('/tabs/entities/publisher');
            loadingEl.dismiss();
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
}
