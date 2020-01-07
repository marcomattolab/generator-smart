import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { JhiDataUtils } from 'ng-jhipster';
import { Camera, CameraOptions } from '@ionic-native/camera/ngx';
import { FormBuilder, Validators } from '@angular/forms';
import { NavController, Platform, ToastController } from '@ionic/angular';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Feedback } from './feedback.model';
import { FeedbackService } from './feedback.service';
import { Struttura, StrutturaService } from '../struttura';

@Component({
    selector: 'page-feedback-update',
    templateUrl: 'feedback-update.html'
})
export class FeedbackUpdatePage implements OnInit {

    feedback: Feedback;
    strutturas: Struttura[];
    @ViewChild('fileInput', {static: true}) fileInput;
    cameraOptions: CameraOptions;
    dataValutazioneDp: any;
    isSaving = false;
    isNew = true;
    isReadyToSave: boolean;

    form = this.formBuilder.group({
        id: [],
        categoria: [null, [Validators.required]],
        descrizione: [null, [Validators.required]],
        valutazione: [null, [Validators.required]],
        dataValutazione: [null, []],
        allegato: [null, []],
        allegatoContentType: [null, []],
        createdBy: [null, []],
        aiNote: [null, []],
          strutturaId: [null, []],
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
        private strutturaService: StrutturaService,
        private feedbackService: FeedbackService
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
        this.strutturaService.query()
            .subscribe(data => { this.strutturas = data.body; }, (error) => this.onError(error));
        this.activatedRoute.data.subscribe((response) => {
            this.updateForm(response.data);
            this.feedback = response.data;
            this.isNew = this.feedback.id === null || this.feedback.id === undefined;
        });
    }

    updateForm(feedback: Feedback) {
        this.form.patchValue({
            id: feedback.id,
            categoria: feedback.categoria,
            descrizione: feedback.descrizione,
            valutazione: feedback.valutazione,
            dataValutazione: feedback.dataValutazione,
            allegato: feedback.allegato,
            allegatoContentType: feedback.allegatoContentType,
            createdBy: feedback.createdBy,
            aiNote: feedback.aiNote,
            strutturaId: feedback.strutturaId,
        });
    }

    save() {
        this.isSaving = true;
        const feedback = this.createFromForm();
        if (!this.isNew) {
            this.subscribeToSaveResponse(this.feedbackService.update(feedback));
        } else {
            this.subscribeToSaveResponse(this.feedbackService.create(feedback));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<Feedback>>) {
        result.subscribe((res: HttpResponse<Feedback>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onError(res.error));
    }

    async onSaveSuccess(response) {
        let action = 'updated';
        if (response.status === 201) {
          action = 'created';
        }
        this.isSaving = false;
        const toast = await this.toastCtrl.create({message: `Feedback ${action} successfully.`, duration: 2000, position: 'middle'});
        toast.present();
        this.navController.navigateBack('/tabs/entities/feedback');
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

    private createFromForm(): Feedback {
        return {
            ...new Feedback(),
            id: this.form.get(['id']).value,
            categoria: this.form.get(['categoria']).value,
            descrizione: this.form.get(['descrizione']).value,
            valutazione: this.form.get(['valutazione']).value,
            dataValutazione: this.form.get(['dataValutazione']).value,
            allegato: this.form.get(['allegato']).value,
            allegatoContentType: this.form.get(['allegatoContentType']).value,
            createdBy: this.form.get(['createdBy']).value,
            aiNote: this.form.get(['aiNote']).value,
            strutturaId: this.form.get(['strutturaId']).value,
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
                this.feedback[fieldName] = data;
                this.feedback[fieldName + 'ContentType'] = 'image/jpeg';
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
        this.dataUtils.clearInputImage(this.feedback, this.elementRef, field, fieldContentType, idInput);
        this.form.patchValue( {[field]: ''} );
    }
    compareStruttura(first: Struttura, second: Struttura): boolean {
        return first && second ? first.id === second.id : first === second;
    }

    trackStrutturaById(index: number, item: Struttura) {
        return item.id;
    }
}
