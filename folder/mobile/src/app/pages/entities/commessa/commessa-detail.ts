import { Component, OnInit } from '@angular/core';
import { JhiDataUtils } from 'ng-jhipster';
import { Commessa } from './commessa.model';
import { CommessaService } from './commessa.service';
import { NavController, AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'page-commessa-detail',
    templateUrl: 'commessa-detail.html'
})
export class CommessaDetailPage implements OnInit {
    commessa: Commessa;

    constructor(
        private dataUtils: JhiDataUtils,
        private navController: NavController,
        private commessaService: CommessaService,
        private activatedRoute: ActivatedRoute,
        private alertController: AlertController
    ) { }

    ngOnInit(): void {
        this.activatedRoute.data.subscribe((response) => {
            this.commessa = response.data;
        });
    }

    open(item: Commessa) {
        this.navController.navigateForward('/tabs/entities/commessa/' + item.id + '/edit');
    }

    async deleteModal(item: Commessa) {
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
                        this.commessaService.delete(item.id).subscribe(() => {
                            this.navController.navigateForward('/tabs/entities/commessa');
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
