import { Component, OnInit } from '@angular/core';
import { JhiDataUtils } from 'ng-jhipster';
import { Trasferta } from './trasferta.model';
import { TrasfertaService } from './trasferta.service';
import { NavController, AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'page-trasferta-detail',
    templateUrl: 'trasferta-detail.html'
})
export class TrasfertaDetailPage implements OnInit {
    trasferta: Trasferta;

    constructor(
        private dataUtils: JhiDataUtils,
        private navController: NavController,
        private trasfertaService: TrasfertaService,
        private activatedRoute: ActivatedRoute,
        private alertController: AlertController
    ) { }

    ngOnInit(): void {
        this.activatedRoute.data.subscribe((response) => {
            this.trasferta = response.data;
        });
    }

    open(item: Trasferta) {
        this.navController.navigateForward('/tabs/entities/trasferta/' + item.id + '/edit');
    }

    async deleteModal(item: Trasferta) {
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
                        this.trasfertaService.delete(item.id).subscribe(() => {
                            this.navController.navigateForward('/tabs/entities/trasferta');
                        });
                    }
                }
            ]
        });
        await alert.present();
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

}
