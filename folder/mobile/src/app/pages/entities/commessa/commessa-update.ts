import { Component, OnInit } from '@angular/core';
import { JhiDataUtils } from 'ng-jhipster';
import { FormBuilder, Validators } from '@angular/forms';
import { NavController, Platform, ToastController } from '@ionic/angular';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Commessa } from './commessa.model';
import { CommessaService } from './commessa.service';

@Component({
    selector: 'page-commessa-update',
    templateUrl: 'commessa-update.html'
})
export class CommessaUpdatePage implements OnInit {

    commessa: Commessa;
    dataInizioDp: any;
    dataFineDp: any;
    isSaving = false;
    isNew = true;
    isReadyToSave: boolean;

    form = this.formBuilder.group({
        id: [],
        codiceCommessa: [null, [Validators.required]],
        nomeProgetto: [null, []],
        dataInizio: [null, [Validators.required]],
        dataFine: [null, []],
        note: [null, []],
        isActive: ['false', []],
    });

    constructor(
        protected activatedRoute: ActivatedRoute,
        protected navController: NavController,
        protected formBuilder: FormBuilder,
        protected platform: Platform,
        protected toastCtrl: ToastController,
        private dataUtils: JhiDataUtils,
        private commessaService: CommessaService
    ) {

        // Watch the form for changes, and
        this.form.valueChanges.subscribe((v) => {
            this.isReadyToSave = this.form.valid;
        });

    }

    ngOnInit() {
        this.activatedRoute.data.subscribe((response) => {
            this.updateForm(response.data);
            this.commessa = response.data;
            this.isNew = this.commessa.id === null || this.commessa.id === undefined;
        });
    }

    updateForm(commessa: Commessa) {
        this.form.patchValue({
            id: commessa.id,
            codiceCommessa: commessa.codiceCommessa,
            nomeProgetto: commessa.nomeProgetto,
            dataInizio: commessa.dataInizio,
            dataFine: commessa.dataFine,
            note: commessa.note,
            isActive: commessa.isActive,
        });
    }

    save() {
        this.isSaving = true;
        const commessa = this.createFromForm();
        if (!this.isNew) {
            this.subscribeToSaveResponse(this.commessaService.update(commessa));
        } else {
            this.subscribeToSaveResponse(this.commessaService.create(commessa));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<Commessa>>) {
        result.subscribe((res: HttpResponse<Commessa>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onError(res.error));
    }

    async onSaveSuccess(response) {
        let action = 'updated';
        if (response.status === 201) {
          action = 'created';
        }
        this.isSaving = false;
        const toast = await this.toastCtrl.create({message: `Commessa ${action} successfully.`, duration: 2000, position: 'middle'});
        toast.present();
        this.navController.navigateBack('/tabs/entities/commessa');
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

    private createFromForm(): Commessa {
        return {
            ...new Commessa(),
            id: this.form.get(['id']).value,
            codiceCommessa: this.form.get(['codiceCommessa']).value,
            nomeProgetto: this.form.get(['nomeProgetto']).value,
            dataInizio: this.form.get(['dataInizio']).value,
            dataFine: this.form.get(['dataFine']).value,
            note: this.form.get(['note']).value,
            isActive: this.form.get(['isActive']).value,
        };
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

}
