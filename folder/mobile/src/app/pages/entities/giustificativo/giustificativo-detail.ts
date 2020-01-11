import { Component, OnInit } from '@angular/core';
import { JhiDataUtils } from 'ng-jhipster';
import { Giustificativo } from './giustificativo.model';
import { GiustificativoService } from './giustificativo.service';
import { NavController, AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'page-giustificativo-detail',
    templateUrl: 'giustificativo-detail.html'
})
export class GiustificativoDetailPage implements OnInit {
    giustificativo: Giustificativo;

    constructor(
        private dataUtils: JhiDataUtils,
        private navController: NavController,
        private giustificativoService: GiustificativoService,
        private activatedRoute: ActivatedRoute,
        private alertController: AlertController
    ) { }

    ngOnInit(): void {
        this.activatedRoute.data.subscribe((response) => {
            this.giustificativo = response.data;
        });
    }

    open(item: Giustificativo) {
        this.navController.navigateForward('/tabs/entities/giustificativo/' + item.id + '/edit');
    }

    async deleteModal(item: Giustificativo) {
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
                        this.giustificativoService.delete(item.id).subscribe(() => {
                            this.navController.navigateForward('/tabs/entities/giustificativo');
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
