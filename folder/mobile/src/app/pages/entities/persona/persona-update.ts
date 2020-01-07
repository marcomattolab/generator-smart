import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { NavController, Platform, ToastController } from '@ionic/angular';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Persona } from './persona.model';
import { PersonaService } from './persona.service';

@Component({
    selector: 'page-persona-update',
    templateUrl: 'persona-update.html'
})
export class PersonaUpdatePage implements OnInit {

    persona: Persona;
    isSaving = false;
    isNew = true;
    isReadyToSave: boolean;

    form = this.formBuilder.group({
        id: [],
        nome: [null, [Validators.required]],
        cognome: [null, [Validators.required]],
        dataNascita: [null, [Validators.required]],
        codiceFiscale: [null, []],
        cid: [null, []],
        sede: [null, []],
        profilo: [null, []],
        email: [null, []],
        telefono: [null, []],
    });

    constructor(
        protected activatedRoute: ActivatedRoute,
        protected navController: NavController,
        protected formBuilder: FormBuilder,
        protected platform: Platform,
        protected toastCtrl: ToastController,
        private personaService: PersonaService
    ) {

        // Watch the form for changes, and
        this.form.valueChanges.subscribe((v) => {
            this.isReadyToSave = this.form.valid;
        });

    }

    ngOnInit() {
        this.activatedRoute.data.subscribe((response) => {
            this.updateForm(response.data);
            this.persona = response.data;
            this.isNew = this.persona.id === null || this.persona.id === undefined;
        });
    }

    updateForm(persona: Persona) {
        this.form.patchValue({
            id: persona.id,
            nome: persona.nome,
            cognome: persona.cognome,
            dataNascita: persona.dataNascita,
            codiceFiscale: persona.codiceFiscale,
            cid: persona.cid,
            sede: persona.sede,
            profilo: persona.profilo,
            email: persona.email,
            telefono: persona.telefono,
        });
    }

    save() {
        this.isSaving = true;
        const persona = this.createFromForm();
        if (!this.isNew) {
            this.subscribeToSaveResponse(this.personaService.update(persona));
        } else {
            this.subscribeToSaveResponse(this.personaService.create(persona));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<Persona>>) {
        result.subscribe((res: HttpResponse<Persona>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onError(res.error));
    }

    async onSaveSuccess(response) {
        let action = 'updated';
        if (response.status === 201) {
          action = 'created';
        }
        this.isSaving = false;
        const toast = await this.toastCtrl.create({message: `Persona ${action} successfully.`, duration: 2000, position: 'middle'});
        toast.present();
        this.navController.navigateBack('/tabs/entities/persona');
    }

    previousState() {
        window.history.back();
    }

    async onError(error) {
        this.isSaving = false;
        console.error(error);
        const toast = await this.toastCtrl.create({message: 'Failed to load data', duration: 2000, position: 'middle'});
        toast.present();
    }

    private createFromForm(): Persona {
        return {
            ...new Persona(),
            id: this.form.get(['id']).value,
            nome: this.form.get(['nome']).value,
            cognome: this.form.get(['cognome']).value,
            dataNascita: this.form.get(['dataNascita']).value,
            codiceFiscale: this.form.get(['codiceFiscale']).value,
            cid: this.form.get(['cid']).value,
            sede: this.form.get(['sede']).value,
            profilo: this.form.get(['profilo']).value,
            email: this.form.get(['email']).value,
            telefono: this.form.get(['telefono']).value,
        };
    }

}
