import { Component, OnInit } from '@angular/core';
import { ConfigurationData } from './configuration-data.model';
import { ConfigurationDataService } from './configuration-data.service';
import { NavController, AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'page-configuration-data-detail',
    templateUrl: 'configuration-data-detail.html'
})
export class ConfigurationDataDetailPage implements OnInit {
    configurationData: ConfigurationData;

    constructor(
        private navController: NavController,
        private configurationDataService: ConfigurationDataService,
        private activatedRoute: ActivatedRoute,
        private alertController: AlertController
    ) { }

    ngOnInit(): void {
        this.activatedRoute.data.subscribe((response) => {
            this.configurationData = response.data;
        });
    }

    open(item: ConfigurationData) {
        this.navController.navigateForward('/tabs/entities/configuration-data/' + item.id + '/edit');
    }

    async deleteModal(item: ConfigurationData) {
        const alert = await this.alertController.create({
            header: 'Confirm the deletion?',
            buttons: [
                {
                    text: 'Cancel',
                    role: 'cancel',
                    cssClass: 'secondary'
                }, {
                    text: 'Delete',
                    handler: () => {
                        this.configurationDataService.delete(item.id).subscribe(() => {
                            this.navController.navigateForward('/tabs/entities/configuration-data');
                        });
                    }
                }
            ]
        });
        await alert.present();
    }


}
