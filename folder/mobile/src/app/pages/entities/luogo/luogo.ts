import { Component } from '@angular/core';
import { NavController, ToastController, Platform, IonItemSliding } from '@ionic/angular';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { Luogo } from './luogo.model';
import { LuogoService } from './luogo.service';

@Component({
    selector: 'page-luogo',
    templateUrl: 'luogo.html'
})
export class LuogoPage {
    luogos: Luogo[];

    // todo: add pagination

    constructor(
        private navController: NavController,
        private luogoService: LuogoService,
        private toastCtrl: ToastController,
        public plt: Platform
    ) {
        this.luogos = [];
    }

    ionViewWillEnter() {
        this.loadAll();
    }

    async loadAll(refresher?) {
        this.luogoService.query().subscribe(
            (response: Luogo[]) => {
                this.luogos = response;
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
        this.navController.navigateForward('/tabs/entities/luogo/new');
    }

    edit(item: IonItemSliding, luogo: Luogo) {
        this.navController.navigateForward('/tabs/entities/luogo/' + luogo.id + '/edit');
        item.close();
    }

    async delete(luogo) {
        this.luogoService.delete(luogo.id).subscribe(async () => {
            const toast = await this.toastCtrl.create(
                {message: 'Luogo deleted successfully.', duration: 3000, position: 'middle'});
            toast.present();
            this.loadAll();
        }, (error) => console.error(error));
    }

    view(luogo: Luogo) {
        this.navController.navigateForward('/tabs/entities/luogo/' + luogo.id + '/view');
    }
}
