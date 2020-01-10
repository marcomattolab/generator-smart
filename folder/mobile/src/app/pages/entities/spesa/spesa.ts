import { Component } from '@angular/core';
import { NavController, ToastController, Platform, IonItemSliding } from '@ionic/angular';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { Spesa } from './spesa.model';
import { SpesaService } from './spesa.service';

@Component({
    selector: 'page-spesa',
    templateUrl: 'spesa.html'
})
export class SpesaPage {
    spesas: Spesa[];

    // todo: add pagination

    constructor(
        private navController: NavController,
        private spesaService: SpesaService,
        private toastCtrl: ToastController,
        public plt: Platform
    ) {
        this.spesas = [];
    }

    ionViewWillEnter() {
        this.loadAll();
    }

    async loadAll(refresher?) {
        this.spesaService.query().pipe(
            filter((res: HttpResponse<Spesa[]>) => res.ok),
            map((res: HttpResponse<Spesa[]>) => res.body)
        )
        .subscribe(
            (response: Spesa[]) => {
                this.spesas = response;
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

    trackId(index: number, item: Spesa) {
        return item.id;
    }

    new() {
        this.navController.navigateForward('/tabs/entities/spesa/new');
    }

    edit(item: IonItemSliding, spesa: Spesa) {
        this.navController.navigateForward('/tabs/entities/spesa/' + spesa.id + '/edit');
        item.close();
    }

    async delete(spesa) {
        this.spesaService.delete(spesa.id).subscribe(async () => {
            const toast = await this.toastCtrl.create(
                {message: 'Spesa deleted successfully.', duration: 3000, position: 'middle'});
            toast.present();
            this.loadAll();
        }, (error) => console.error(error));
    }

    view(spesa: Spesa) {
        this.navController.navigateForward('/tabs/entities/spesa/' + spesa.id + '/view');
    }
}
