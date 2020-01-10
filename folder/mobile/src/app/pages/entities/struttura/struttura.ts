import { Component } from '@angular/core';
import { NavController, ToastController, Platform, IonItemSliding } from '@ionic/angular';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { Struttura } from './struttura.model';
import { StrutturaService } from './struttura.service';

@Component({
    selector: 'page-struttura',
    templateUrl: 'struttura.html'
})
export class StrutturaPage {
    strutturas: Struttura[];

    // todo: add pagination

    constructor(
        private navController: NavController,
        private strutturaService: StrutturaService,
        private toastCtrl: ToastController,
        public plt: Platform
    ) {
        this.strutturas = [];
    }

    ionViewWillEnter() {
        this.loadAll();
    }

    async loadAll(refresher?) {
        this.strutturaService.query().pipe(
            filter((res: HttpResponse<Struttura[]>) => res.ok),
            map((res: HttpResponse<Struttura[]>) => res.body)
        )
        .subscribe(
            (response: Struttura[]) => {
                this.strutturas = response;
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

    trackId(index: number, item: Struttura) {
        return item.id;
    }

    new() {
        this.navController.navigateForward('/tabs/entities/struttura/new');
    }

    edit(item: IonItemSliding, struttura: Struttura) {
        this.navController.navigateForward('/tabs/entities/struttura/' + struttura.id + '/edit');
        item.close();
    }

    async delete(struttura) {
        this.strutturaService.delete(struttura.id).subscribe(async () => {
            const toast = await this.toastCtrl.create(
                {message: 'Struttura deleted successfully.', duration: 3000, position: 'middle'});
            toast.present();
            this.loadAll();
        }, (error) => console.error(error));
    }

    view(struttura: Struttura) {
        this.navController.navigateForward('/tabs/entities/struttura/' + struttura.id + '/view');
    }
}
