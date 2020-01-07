import { Component } from '@angular/core';
import { NavController, ToastController, Platform, IonItemSliding } from '@ionic/angular';
import { filter, map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { Persona } from './persona.model';
import { PersonaService } from './persona.service';

@Component({
    selector: 'page-persona',
    templateUrl: 'persona.html'
})
export class PersonaPage {
    personas: Persona[];

    // todo: add pagination

    constructor(
        private navController: NavController,
        private personaService: PersonaService,
        private toastCtrl: ToastController,
        public plt: Platform
    ) {
        this.personas = [];
    }

    ionViewWillEnter() {
        this.loadAll();
    }

    async loadAll(refresher?) {
        this.personaService.query().pipe(
            filter((res: HttpResponse<Persona[]>) => res.ok),
            map((res: HttpResponse<Persona[]>) => res.body)
        )
        .subscribe(
            (response: Persona[]) => {
                this.personas = response;
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

    trackId(index: number, item: Persona) {
        return item.id;
    }

    new() {
        this.navController.navigateForward('/tabs/entities/persona/new');
    }

    edit(item: IonItemSliding, persona: Persona) {
        this.navController.navigateForward('/tabs/entities/persona/' + persona.id + '/edit');
        item.close();
    }

    async delete(persona) {
        this.personaService.delete(persona.id).subscribe(async () => {
            const toast = await this.toastCtrl.create(
                {message: 'Persona deleted successfully.', duration: 3000, position: 'middle'});
            toast.present();
            this.loadAll();
        }, (error) => console.error(error));
    }

    view(persona: Persona) {
        this.navController.navigateForward('/tabs/entities/persona/' + persona.id + '/view');
    }
}
