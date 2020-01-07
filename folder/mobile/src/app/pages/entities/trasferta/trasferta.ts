import { Component } from '@angular/core';
import { NavController, ToastController, Platform, IonItemSliding } from '@ionic/angular';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { JhiDataUtils } from 'ng-jhipster';
import { Trasferta } from './trasferta.model';
import { TrasfertaService } from './trasferta.service';

@Component({
    selector: 'page-trasferta',
    templateUrl: 'trasferta.html'
})
export class TrasfertaPage {
    trasfertas: Trasferta[];

    // todo: add pagination

    constructor(
        private dataUtils: JhiDataUtils,
        private navController: NavController,
        private trasfertaService: TrasfertaService,
        private toastCtrl: ToastController,
        public plt: Platform
    ) {
        this.trasfertas = [];
    }

    ionViewWillEnter() {
        this.loadAll();
    }

    async loadAll(refresher?) {
        this.trasfertaService.query().pipe(
            filter((res: HttpResponse<Trasferta[]>) => res.ok),
            map((res: HttpResponse<Trasferta[]>) => res.body)
        )
        .subscribe(
            (response: Trasferta[]) => {
                this.trasfertas = response;
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

    trackId(index: number, item: Trasferta) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    new() {
        this.navController.navigateForward('/tabs/entities/trasferta/new');
    }

    edit(item: IonItemSliding, trasferta: Trasferta) {
        this.navController.navigateForward('/tabs/entities/trasferta/' + trasferta.id + '/edit');
        item.close();
    }

    async delete(trasferta) {
        this.trasfertaService.delete(trasferta.id).subscribe(async () => {
            const toast = await this.toastCtrl.create(
                {message: 'Trasferta deleted successfully.', duration: 3000, position: 'middle'});
            toast.present();
            this.loadAll();
        }, (error) => console.error(error));
    }

    view(trasferta: Trasferta) {
        this.navController.navigateForward('/tabs/entities/trasferta/' + trasferta.id + '/view');
    }
}
