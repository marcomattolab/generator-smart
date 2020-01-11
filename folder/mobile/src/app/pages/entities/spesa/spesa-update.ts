import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {NavController, Platform, ToastController} from '@ionic/angular';
import {HttpResponse, HttpErrorResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {Spesa} from './spesa.model';
import {SpesaService} from './spesa.service';
import {Trasferta, TrasfertaService} from '../trasferta';
import {Struttura, StrutturaService} from '../struttura';
import * as moment from 'moment';
import {getMomentDateNoTZ} from '../../../shared/util/moment-util';

@Component({
  selector: 'page-spesa-update',
  templateUrl: 'spesa-update.html'
})
export class SpesaUpdatePage implements OnInit {

  spesa: Spesa;
  trasfertas: Trasferta[];
  strutturas: Struttura[];
  dataSpesaDp: any;
  approvedDateDp: any;
  isSaving = false;
  isNew = true;
  isReadyToSave: boolean;

  form = this.formBuilder.group({
    id: [],
    categoria: [null, []],
    sottocategoria: [null, []],
    importoSpesa: [null, []],
    dataSpesa: [null, []],
    stato: [null, []],
    approvedDate: [null, []],
    trasferta: [null, []],
    structure: [null, []],
  });

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected navController: NavController,
    protected formBuilder: FormBuilder,
    protected platform: Platform,
    protected toastCtrl: ToastController,
    private trasfertaService: TrasfertaService,
    private strutturaService: StrutturaService,
    private spesaService: SpesaService
  ) {

    // Watch the form for changes, and
    this.form.valueChanges.subscribe((v) => {
      this.isReadyToSave = this.form.valid;
    });

  }

  ngOnInit() {
    this.trasfertaService.query()
      .subscribe(data => {
        this.trasfertas = data.body;
      }, (error) => this.onError(error));
    this.strutturaService.query()
      .subscribe(data => {
        this.strutturas = data.body;
      }, (error) => this.onError(error));
    this.activatedRoute.data.subscribe((response) => {
      this.updateForm(response.data);
      this.spesa = response.data;
      this.isNew = this.spesa.id === null || this.spesa.id === undefined;
    });
  }

  updateForm(spesa: Spesa) {
    this.form.patchValue({
      id: spesa.id,
      categoria: spesa.categoria,
      sottocategoria: spesa.sottocategoria,
      importoSpesa: spesa.importoSpesa,
      dataSpesa: spesa.dataSpesa,
      stato: spesa.stato,
      approvedDate: spesa.approvedDate,
      trasferta: spesa.trasfertaId,
      structure: spesa.structureId,
    });
  }

  save() {
    this.isSaving = true;
    const spesa = this.createFromForm();
    if (!this.isNew) {
      this.subscribeToSaveResponse(this.spesaService.update(spesa));
    } else {
      this.subscribeToSaveResponse(this.spesaService.create(spesa));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<Spesa>>) {
    result.subscribe((res: HttpResponse<Spesa>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onError(res.error));
  }

  async onSaveSuccess(response) {
    let action = 'updated';
    if (response.status === 201) {
      action = 'created';
    }
    this.isSaving = false;
    const toast = await this.toastCtrl.create({message: `Spesa ${action} successfully.`, duration: 2000, position: 'middle'});
    toast.present();
    this.navController.navigateBack('/tabs/entities/spesa');
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

  private createFromForm(): Spesa {
    const dataSpesa = this.form.get(['dataSpesa']).value;
    const dataSpesaValue = dataSpesa ? getMomentDateNoTZ(dataSpesa) : null;

    const approvedDate = this.form.get(['approvedDate']).value;
    const approvedDateValue = approvedDate ? getMomentDateNoTZ(approvedDate) : null;

    return {
      ...new Spesa(),
      id: this.form.get(['id']).value,
      categoria: this.form.get(['categoria']).value,
      sottocategoria: this.form.get(['sottocategoria']).value,
      importoSpesa: this.form.get(['importoSpesa']).value,
      dataSpesa: dataSpesaValue,
      stato: this.form.get(['stato']).value,
      approvedDate: approvedDateValue,
      trasfertaId: this.form.get(['trasferta']).value,
      structureId: this.form.get(['structure']).value,
    };
  }

  compareTrasferta(first: Trasferta, second: Trasferta): boolean {
    return first && second ? first.id === second.id : first === second;
  }

  compareStruttura(first: Struttura, second: Struttura): boolean {
    return first && second ? first.id === second.id : first === second;
  }
}
