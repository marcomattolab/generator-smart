import {Component, OnInit} from '@angular/core';
import {JhiDataUtils} from 'ng-jhipster';
import {FormBuilder, Validators} from '@angular/forms';
import {NavController, Platform, ToastController} from '@ionic/angular';
import {HttpResponse, HttpErrorResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {Trasferta} from './trasferta.model';
import {TrasfertaService} from './trasferta.service';
import {Luogo, LuogoService} from '../luogo';
// import { Persona, PersonaService } from '../persona';
import {Commessa, CommessaService} from '../commessa';
import {User} from '../../../services/user/user.model';
import {UserService} from '../../../services/user/user.service';
import * as moment from 'moment';

@Component({
  selector: 'page-trasferta-update',
  templateUrl: 'trasferta-update.html'
})
export class TrasfertaUpdatePage implements OnInit {

  trasferta: Trasferta;
  luogos: Luogo[];
  personas: User[];
  commessas: Commessa[];
  dataInizioDp: any;
  dataFineDp: any;
  isSaving = false;
  isNew = true;
  isReadyToSave = true;

  form = this.formBuilder.group({
    id: [],
    codice: [null, [Validators.required]],
    descrizione: [null, []],
    dataInizio: [null, [Validators.required]],
    dataFine: [null, []],
    statoTrasferta: [null, [Validators.required]],
    note: [null, []],
    sede: [null, []],
    dipendente: [null, []],
    responsabile: [null, []],
    rifCommessa: [null, []],
    commessa: [null, []],
  });

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected navController: NavController,
    protected formBuilder: FormBuilder,
    protected platform: Platform,
    protected toastCtrl: ToastController,
    private dataUtils: JhiDataUtils,
    private luogoService: LuogoService,
    private personaService: UserService,
    private commessaService: CommessaService,
    private trasfertaService: TrasfertaService
  ) {

    // Watch the form for changes, and
    this.form.valueChanges.subscribe((v) => {
      this.isReadyToSave = this.form.valid;
    });

  }

  ngOnInit() {
    this.luogoService
      .query({filter: 'trasferta-is-null'})
      .subscribe(data => {
        if (!this.trasferta.sedeId) {
          this.luogos = data;
        } else {
          this.luogoService
            .find(this.trasferta.sedeId)
            .subscribe((subData: Luogo) => {
              this.luogos = [subData].concat(subData);
            }, (error) => this.onError(error));
        }
      }, (error) => this.onError(error));

    // TODO inserire parametri per la ricerca in funzione del ruolo
    this.personaService.findAll()
      .subscribe(data => {
        this.personas = data;
      }, (error) => this.onError(error));

    this.commessaService.query()
      .subscribe(data => {
        this.commessas = data.body;
      }, (error) => this.onError(error));

    this.activatedRoute.data.subscribe((response) => {
      this.updateForm(response.data);
      this.trasferta = response.data;
      this.isNew = this.trasferta.id === null || this.trasferta.id === undefined;
    });
  }

  updateForm(trasferta: Trasferta) {
    this.form.patchValue({
      id: trasferta.id,
      codice: trasferta.codice,
      descrizione: trasferta.descrizione,
      dataInizio: trasferta.dataInizio,
      dataFine: trasferta.dataFine,
      statoTrasferta: trasferta.statoTrasferta,
      note: trasferta.note,
      sede: trasferta.sedeId,
      dipendente: trasferta.dipendenteId,
      responsabile: trasferta.responsabileId,
      rifCommessa: trasferta.rifCommessaId,
      commessa: trasferta.commessaId,
    });
  }

  save() {
    this.isSaving = true;
    const trasferta = this.createFromForm();
    if (!this.isNew) {
      this.subscribeToSaveResponse(this.trasfertaService.update(trasferta));
    } else {
      this.subscribeToSaveResponse(this.trasfertaService.create(trasferta));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<Trasferta>>) {
    result.subscribe((res: HttpResponse<Trasferta>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onError(res.error));
  }

  async onSaveSuccess(response) {
    let action = 'updated';
    if (response.status === 201) {
      action = 'created';
    }
    this.isSaving = false;
    const toast = await this.toastCtrl.create({message: `Trasferta ${action} successfully.`, duration: 2000, position: 'middle'});
    toast.present();
    this.navController.navigateBack('/tabs/entities/trasferta');
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

  private createFromForm(): Trasferta {
    const dataInizioValue = moment(this.form.get(['dataInizio']).value);
    const dataFine = this.form.get(['dataFine']).value;
    const dataFineValue = dataFine ? moment(dataFine) : null;
    return {
      ...new Trasferta(),
      id: this.form.get(['id']).value,
      codice: this.form.get(['codice']).value,
      descrizione: this.form.get(['descrizione']).value,
      dataInizio: dataInizioValue,
      dataFine: dataFineValue,
      statoTrasferta: this.form.get(['statoTrasferta']).value,
      note: this.form.get(['note']).value,
      sedeId: this.form.get(['sede']).value,
      dipendenteId: this.form.get(['dipendente']).value,
      responsabileId: this.form.get(['responsabile']).value,
      rifCommessaId: this.form.get(['rifCommessa']).value,
      commessaId: this.form.get(['commessa']).value,
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

  compareLuogo(first: Luogo, second: Luogo): boolean {
    return first && second ? first.id === second.id : first === second;
  }

  trackLuogoById(index: number, item: Luogo) {
    return item.id;
  }

  // comparePersona(first: Persona, second: Persona): boolean {
  //   return first && second ? first.id === second.id : first === second;
  // }
  //
  // trackPersonaById(index: number, item: Persona) {
  //   return item.id;
  // }

  compareCommessa(first: Commessa, second: Commessa): boolean {
    return first && second ? first.id === second.id : first === second;
  }

  trackCommessaById(index: number, item: Commessa) {
    return item.id;
  }
}
