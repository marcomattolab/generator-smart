import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { NavController, Platform, ToastController } from '@ionic/angular';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ConfigurationData } from './configuration-data.model';
import { ConfigurationDataService } from './configuration-data.service';

@Component({
    selector: 'page-configuration-data-update',
    templateUrl: 'configuration-data-update.html'
})
export class ConfigurationDataUpdatePage implements OnInit {

    configurationData: ConfigurationData;
    isSaving = false;
    isNew = true;
    isReadyToSave: boolean;

    form = this.formBuilder.group({
        id: [],
        key: [null, [Validators.required]],
        type: [null, [Validators.required]],
        value: [null, [Validators.required]],
        isActive: ['false', []],
    });

    constructor(
        protected activatedRoute: ActivatedRoute,
        protected navController: NavController,
        protected formBuilder: FormBuilder,
        protected platform: Platform,
        protected toastCtrl: ToastController,
        private configurationDataService: ConfigurationDataService
    ) {

        // Watch the form for changes, and
        this.form.valueChanges.subscribe((v) => {
            this.isReadyToSave = this.form.valid;
        });

    }

    ngOnInit() {
        this.activatedRoute.data.subscribe((response) => {
            this.updateForm(response.data);
            this.configurationData = response.data;
            this.isNew = this.configurationData.id === null || this.configurationData.id === undefined;
        });
    }

    updateForm(configurationData: ConfigurationData) {
        this.form.patchValue({
            id: configurationData.id,
            key: configurationData.key,
            type: configurationData.type,
            value: configurationData.value,
            isActive: configurationData.isActive,
        });
    }

    save() {
        this.isSaving = true;
        const configurationData = this.createFromForm();
        if (!this.isNew) {
            this.subscribeToSaveResponse(this.configurationDataService.update(configurationData));
        } else {
            this.subscribeToSaveResponse(this.configurationDataService.create(configurationData));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ConfigurationData>>) {
        result.subscribe((res: HttpResponse<ConfigurationData>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onError(res.error));
    }

    async onSaveSuccess(response) {
        let action = 'updated';
        if (response.status === 201) {
          action = 'created';
        }
        this.isSaving = false;
        const toast = await this.toastCtrl.create({message: `ConfigurationData ${action} successfully.`, duration: 2000, position: 'middle'});
        toast.present();
        this.navController.navigateBack('/tabs/entities/configuration-data');
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

    private createFromForm(): ConfigurationData {
        return {
            ...new ConfigurationData(),
            id: this.form.get(['id']).value,
            key: this.form.get(['key']).value,
            type: this.form.get(['type']).value,
            value: this.form.get(['value']).value,
            isActive: this.form.get(['isActive']).value,
        };
    }

}
