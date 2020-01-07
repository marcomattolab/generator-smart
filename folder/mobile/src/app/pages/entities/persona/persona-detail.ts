import { Component, OnInit } from '@angular/core';
import { Persona } from './persona.model';
import { PersonaService } from './persona.service';
import { NavController, AlertController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'page-persona-detail',
    templateUrl: 'persona-detail.html'
})
export class PersonaDetailPage implements OnInit {
    persona: Persona;

    constructor(
        private navController: NavController,
        private personaService: PersonaService,
        private activatedRoute: ActivatedRoute,
        private alertController: AlertController
    ) { }

    ngOnInit(): void {
        this.activatedRoute.data.subscribe((response) => {
            this.persona = response.data;
        });
    }

    open(item: Persona) {
        this.navController.navigateForward('/tabs/entities/persona/' + item.id + '/edit');
    }

    async deleteModal(item: Persona) {
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
                        this.personaService.delete(item.id).subscribe(() => {
                            this.navController.navigateForward('/tabs/entities/persona');
                        });
                    }
                }
            ]
        });
        await alert.present();
    }


}
