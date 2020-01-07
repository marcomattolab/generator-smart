import { Component, OnInit } from '@angular/core';
import { Luogo } from './luogo.model';
import { LuogoService } from './luogo.service';
import { NavController, AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'page-luogo-detail',
    templateUrl: 'luogo-detail.html'
})
export class LuogoDetailPage implements OnInit {
    luogo: Luogo;

    constructor(
        private navController: NavController,
        private luogoService: LuogoService,
        private activatedRoute: ActivatedRoute,
        private alertController: AlertController
    ) { }

    ngOnInit(): void {
        this.activatedRoute.data.subscribe((response) => {
            this.luogo = response.data;
        });
    }

    open(item: Luogo) {
        this.navController.navigateForward('/tabs/entities/luogo/' + item.id + '/edit');
    }

    async deleteModal(item: Luogo) {
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
                        this.luogoService.delete(item.id).subscribe(() => {
                            this.navController.navigateForward('/tabs/entities/luogo');
                        });
                    }
                }
            ]
        });
        await alert.present();
    }


}
