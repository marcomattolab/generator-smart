import { Component, OnInit } from '@angular/core';
import { Spesa } from './spesa.model';
import { SpesaService } from './spesa.service';
import { NavController, AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'page-spesa-detail',
    templateUrl: 'spesa-detail.html'
})
export class SpesaDetailPage implements OnInit {
    spesa: Spesa;

    constructor(
        private navController: NavController,
        private spesaService: SpesaService,
        private activatedRoute: ActivatedRoute,
        private alertController: AlertController
    ) { }

    ngOnInit(): void {
        this.activatedRoute.data.subscribe((response) => {
            this.spesa = response.data;
        });
    }

    open(item: Spesa) {
        this.navController.navigateForward('/tabs/entities/spesa/' + item.id + '/edit');
    }

    async deleteModal(item: Spesa) {
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
                        this.spesaService.delete(item.id).subscribe(() => {
                            this.navController.navigateForward('/tabs/entities/spesa');
                        });
                    }
                }
            ]
        });
        await alert.present();
    }


}
