import { Component } from '@angular/core';
import { NavController, ToastController, Platform, IonItemSliding } from '@ionic/angular';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { ConfigurationData } from './configuration-data.model';
import { ConfigurationDataService } from './configuration-data.service';

@Component({
    selector: 'page-configuration-data',
    templateUrl: 'configuration-data.html'
})
export class ConfigurationDataPage {
    configurationData: ConfigurationData[];

    // todo: add pagination

    constructor(
        private navController: NavController,
        private configurationDataService: ConfigurationDataService,
        private toastCtrl: ToastController,
        public plt: Platform
    ) {
        this.configurationData = [];
    }

    ionViewWillEnter() {
        this.loadAll();
    }

    async loadAll(refresher?) {
        this.configurationDataService.query().pipe(
            filter((res: HttpResponse<ConfigurationData[]>) => res.ok),
            map((res: HttpResponse<ConfigurationData[]>) => res.body)
        )
        .subscribe(
            (response: ConfigurationData[]) => {
                this.configurationData = response;
                if (typeof(refresher) !== 'undefined') {
                    setTimeout(() => {
                        refresher.target.complete();
                    }, 750);
                }
            },
            async (error) => {
                console.error(error);
                const toast = await this.toastCtrl.create({message: 'Failed to load data', duration: 2000, position: 'middle'});
                toast.present();
            });
    }

    new() {
        this.navController.navigateForward('/tabs/entities/configuration-data/new');
    }

    edit(item: IonItemSliding, configurationData: ConfigurationData) {
        this.navController.navigateForward('/tabs/entities/configuration-data/' + configurationData.id + '/edit');
        item.close();
    }

    async delete(configurationData) {
        this.configurationDataService.delete(configurationData.id).subscribe(async () => {
            const toast = await this.toastCtrl.create(
                {message: 'ConfigurationData deleted successfully.', duration: 3000, position: 'middle'});
            toast.present();
            this.loadAll();
        }, (error) => console.error(error));
    }

    view(configurationData: ConfigurationData) {
        this.navController.navigateForward('/tabs/entities/configuration-data/' + configurationData.id + '/view');
    }
}
