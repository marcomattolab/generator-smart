import { Component } from '@angular/core';
import { NavController, ToastController, Platform, IonItemSliding } from '@ionic/angular';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { JhiDataUtils } from 'ng-jhipster';
import { Giustificativo } from './giustificativo.model';
import { GiustificativoService } from './giustificativo.service';

@Component({
    selector: 'page-giustificativo',
    templateUrl: 'giustificativo.html'
})
export class GiustificativoPage {
    giustificativos: Giustificativo[];

    // todo: add pagination

    constructor(
        private dataUtils: JhiDataUtils,
        private navController: NavController,
        private giustificativoService: GiustificativoService,
        private toastCtrl: ToastController,
        public plt: Platform
    ) {
        this.giustificativos = [];
    }

    ionViewWillEnter() {
        this.loadAll();
    }

    async loadAll(refresher?) {
        this.giustificativoService.query().pipe(
            filter((res: HttpResponse<Giustificativo[]>) => res.ok),
            map((res: HttpResponse<Giustificativo[]>) => res.body)
        )
        .subscribe(
            (response: Giustificativo[]) => {
                this.giustificativos = response;
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

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    new() {
        this.navController.navigateForward('/tabs/entities/giustificativo/new');
    }

    edit(item: IonItemSliding, giustificativo: Giustificativo) {
        this.navController.navigateForward('/tabs/entities/giustificativo/' + giustificativo.id + '/edit');
        item.close();
    }

    async delete(giustificativo) {
        this.giustificativoService.delete(giustificativo.id).subscribe(async () => {
            const toast = await this.toastCtrl.create(
                {message: 'Giustificativo deleted successfully.', duration: 3000, position: 'middle'});
            toast.present();
            this.loadAll();
        }, (error) => console.error(error));
    }

    view(giustificativo: Giustificativo) {
        this.navController.navigateForward('/tabs/entities/giustificativo/' + giustificativo.id + '/view');
    }
}
