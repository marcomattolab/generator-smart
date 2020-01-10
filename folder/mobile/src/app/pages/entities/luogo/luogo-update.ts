import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { NavController, Platform, ToastController } from '@ionic/angular';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Luogo } from './luogo.model';
import { LuogoService } from './luogo.service';

@Component({
    selector: 'page-luogo-update',
    templateUrl: 'luogo-update.html'
})
export class LuogoUpdatePage implements OnInit {

    luogo: Luogo;
    isSaving = false;
    isNew = true;
    isReadyToSave: boolean;

    form = this.formBuilder.group({
        id: [],
        indirizzo: [null, [Validators.required]],
        citta: [null, [Validators.required]],
        cap: [null, [Validators.required]],
        nazione: [null, [Validators.required]],
        latitudine: [null, []],
        longitudine: [null, []],
    });

    constructor(
        protected activatedRoute: ActivatedRoute,
        protected navController: NavController,
        protected formBuilder: FormBuilder,
        protected platform: Platform,
        protected toastCtrl: ToastController,
        private luogoService: LuogoService
    ) {

        // Watch the form for changes, and
        this.form.valueChanges.subscribe((v) => {
            this.isReadyToSave = this.form.valid;
        });

    }

    ngOnInit() {
        this.activatedRoute.data.subscribe((response) => {
            this.updateForm(response.data);
            this.luogo = response.data;
            this.isNew = this.luogo.id === null || this.luogo.id === undefined;
        });
    }

    updateForm(luogo: Luogo) {
        this.form.patchValue({
            id: luogo.id,
            indirizzo: luogo.indirizzo,
            citta: luogo.citta,
            cap: luogo.cap,
            nazione: luogo.nazione,
            latitudine: luogo.latitudine,
            longitudine: luogo.longitudine,
        });
    }

    save() {
        this.isSaving = true;
        const luogo = this.createFromForm();
        if (!this.isNew) {
            this.subscribeToSaveResponse(this.luogoService.update(luogo));
        } else {
            this.subscribeToSaveResponse(this.luogoService.create(luogo));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<Luogo>>) {
        result.subscribe((res: HttpResponse<Luogo>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onError(res.error));
    }

    async onSaveSuccess(response) {
        let action = 'updated';
        if (response.status === 201) {
          action = 'created';
        }
        this.isSaving = false;
        const toast = await this.toastCtrl.create({message: `Luogo ${action} successfully.`, duration: 2000, position: 'middle'});
        toast.present();
        this.navController.navigateBack('/tabs/entities/luogo');
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

    private createFromForm(): Luogo {
        return {
            ...new Luogo(),
            id: this.form.get(['id']).value,
            indirizzo: this.form.get(['indirizzo']).value,
            citta: this.form.get(['citta']).value,
            cap: this.form.get(['cap']).value,
            nazione: this.form.get(['nazione']).value,
            latitudine: this.form.get(['latitudine']).value,
            longitudine: this.form.get(['longitudine']).value,
        };
    }

}
