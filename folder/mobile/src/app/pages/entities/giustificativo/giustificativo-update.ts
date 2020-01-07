import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { JhiDataUtils } from 'ng-jhipster';
import { Camera, CameraOptions } from '@ionic-native/camera/ngx';
import { FormBuilder, Validators } from '@angular/forms';
import { NavController, Platform, ToastController } from '@ionic/angular';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Giustificativo } from './giustificativo.model';
import { GiustificativoService } from './giustificativo.service';
import { Spesa, SpesaService } from '../spesa';

@Component({
    selector: 'page-giustificativo-update',
    templateUrl: 'giustificativo-update.html'
})
export class GiustificativoUpdatePage implements OnInit {

    giustificativo: Giustificativo;
    spesas: Spesa[];
    @ViewChild('fileInput', {static: true}) fileInput;
    cameraOptions: CameraOptions;
    isSaving = false;
    isNew = true;
    isReadyToSave: boolean;

    form = this.formBuilder.group({
        id: [],
        allegato: [null, [Validators.required]],
        allegatoContentType: [null, []],
          spesaId: [null, []],
    });

    constructor(
        protected activatedRoute: ActivatedRoute,
        protected navController: NavController,
        protected formBuilder: FormBuilder,
        protected platform: Platform,
        protected toastCtrl: ToastController,
        private dataUtils: JhiDataUtils,

        private elementRef: ElementRef,
        private camera: Camera,
        private spesaService: SpesaService,
        private giustificativoService: GiustificativoService
    ) {

        // Watch the form for changes, and
        this.form.valueChanges.subscribe((v) => {
            this.isReadyToSave = this.form.valid;
        });

        // Set the Camera options
        this.cameraOptions = {
            quality: 100,
            targetWidth: 900,
            targetHeight: 600,
            destinationType: this.camera.DestinationType.DATA_URL,
            encodingType: this.camera.EncodingType.JPEG,
            mediaType: this.camera.MediaType.PICTURE,
            saveToPhotoAlbum: false,
            allowEdit: true,
            sourceType: 1
        };
    }

    ngOnInit() {
        this.spesaService.query()
            .subscribe(data => { this.spesas = data.body; }, (error) => this.onError(error));
        this.activatedRoute.data.subscribe((response) => {
            this.updateForm(response.data);
            this.giustificativo = response.data;
            this.isNew = this.giustificativo.id === null || this.giustificativo.id === undefined;
        });
    }

    updateForm(giustificativo: Giustificativo) {
        this.form.patchValue({
            id: giustificativo.id,
            allegato: giustificativo.allegato,
            allegatoContentType: giustificativo.allegatoContentType,
            spesaId: giustificativo.spesaId,
        });
    }

    save() {
        this.isSaving = true;
        const giustificativo = this.createFromForm();
        if (!this.isNew) {
            this.subscribeToSaveResponse(this.giustificativoService.update(giustificativo));
        } else {
            this.subscribeToSaveResponse(this.giustificativoService.create(giustificativo));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<Giustificativo>>) {
        result.subscribe((res: HttpResponse<Giustificativo>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onError(res.error));
    }

    async onSaveSuccess(response) {
        let action = 'updated';
        if (response.status === 201) {
          action = 'created';
        }
        this.isSaving = false;
        const toast = await this.toastCtrl.create({message: `Giustificativo ${action} successfully.`, duration: 2000, position: 'middle'});
        toast.present();
        this.navController.navigateBack('/tabs/entities/giustificativo');
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

    private createFromForm(): Giustificativo {
        return {
            ...new Giustificativo(),
            id: this.form.get(['id']).value,
            allegato: this.form.get(['allegato']).value,
            allegatoContentType: this.form.get(['allegatoContentType']).value,
            spesaId: this.form.get(['spesaId']).value,
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
        this.processWebImage(event, field);
    }

    getPicture(fieldName) {
        if (Camera.installed()) {
            this.camera.getPicture(this.cameraOptions).then((data) => {
                this.giustificativo[fieldName] = data;
                this.giustificativo[fieldName + 'ContentType'] = 'image/jpeg';
                this.form.patchValue({ [fieldName]: data });
                this.form.patchValue({ [fieldName + 'ContentType']: 'image/jpeg' });
            }, (err) => {
                alert('Unable to take photo');
            });
        } else {
            this.fileInput.nativeElement.click();
        }
    }

    processWebImage(event, fieldName) {
        const reader = new FileReader();
        reader.onload = (readerEvent) => {

            let imageData = (readerEvent.target as any).result;
            const imageType = event.target.files[0].type;
            imageData = imageData.substring(imageData.indexOf(',') + 1);

            this.form.patchValue({ [fieldName]: imageData });
            this.form.patchValue({ [fieldName + 'ContentType']: imageType });
        };

        reader.readAsDataURL(event.target.files[0]);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.giustificativo, this.elementRef, field, fieldContentType, idInput);
        this.form.patchValue( {[field]: ''} );
    }
    compareSpesa(first: Spesa, second: Spesa): boolean {
        return first && second ? first.id === second.id : first === second;
    }

    trackSpesaById(index: number, item: Spesa) {
        return item.id;
    }
}
