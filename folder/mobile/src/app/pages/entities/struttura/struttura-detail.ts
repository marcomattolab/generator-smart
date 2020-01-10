import { Component, OnInit } from '@angular/core';
import { Struttura } from './struttura.model';
import { StrutturaService } from './struttura.service';
import { NavController, AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'page-struttura-detail',
    templateUrl: 'struttura-detail.html'
})
export class StrutturaDetailPage implements OnInit {
    struttura: Struttura;

    constructor(
        private navController: NavController,
        private strutturaService: StrutturaService,
        private activatedRoute: ActivatedRoute,
        private alertController: AlertController
    ) { }

    ngOnInit(): void {
        this.activatedRoute.data.subscribe((response) => {
            this.struttura = response.data;
        });
    }

    open(item: Struttura) {
        this.navController.navigateForward('/tabs/entities/struttura/' + item.id + '/edit');
    }

    async deleteModal(item: Struttura) {
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
                        this.strutturaService.delete(item.id).subscribe(() => {
                            this.navController.navigateForward('/tabs/entities/struttura');
                        });
                    }
                }
            ]
        });
        await alert.present();
    }


}
