import { Component } from '@angular/core';
import { NavController, ToastController, Platform, IonItemSliding } from '@ionic/angular';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { JhiDataUtils } from 'ng-jhipster';
import { Commessa } from './commessa.model';
import { CommessaService } from './commessa.service';

@Component({
    selector: 'page-commessa',
    templateUrl: 'commessa.html'
})
export class CommessaPage {
    commessas: Commessa[];

    // todo: add pagination

    constructor(
        private dataUtils: JhiDataUtils,
        private navController: NavController,
        private commessaService: CommessaService,
        private toastCtrl: ToastController,
        public plt: Platform
    ) {
        this.commessas = [];
    }

    ionViewWillEnter() {
        this.loadAll();
    }

    async loadAll(refresher?) {
        this.commessaService.query().pipe(
            filter((res: HttpResponse<Commessa[]>) => res.ok),
            map((res: HttpResponse<Commessa[]>) => res.body)
        )
        .subscribe(
            (response: Commessa[]) => {
                this.commessas = response;
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

    trackId(index: number, item: Commessa) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    new() {
        this.navController.navigateForward('/tabs/entities/commessa/new');
    }

    edit(item: IonItemSliding, commessa: Commessa) {
        this.navController.navigateForward('/tabs/entities/commessa/' + commessa.id + '/edit');
        item.close();
    }

    async delete(commessa) {
        this.commessaService.delete(commessa.id).subscribe(async () => {
            const toast = await this.toastCtrl.create(
                {message: 'Commessa deleted successfully.', duration: 3000, position: 'middle'});
            toast.present();
            this.loadAll();
        }, (error) => console.error(error));
    }

    view(commessa: Commessa) {
        this.navController.navigateForward('/tabs/entities/commessa/' + commessa.id + '/view');
    }
}
