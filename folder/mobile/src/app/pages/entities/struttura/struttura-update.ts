import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {NavController, Platform, ToastController} from '@ionic/angular';
import {HttpResponse, HttpErrorResponse} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {Struttura} from './struttura.model';
import {StrutturaService} from './struttura.service';
import {Luogo, LuogoService} from '../luogo';

@Component({
  selector: 'page-struttura-update',
  templateUrl: 'struttura-update.html'
})
export class StrutturaUpdatePage implements OnInit {

  struttura: Struttura;
  luogos: Luogo[];
  isSaving = false;
  isNew = true;
  isReadyToSave: boolean;

  form = this.formBuilder.group({
    id: [],
    categoria: [null, [Validators.required]],
    codice: [null, [Validators.required]],
    nome: [null, [Validators.required]],
    telefono: [null, []],
    email: [null, []],
    mediaValutazione: [null, []],
    numeroValutazioni: [null, []],
    isActive: ['false', []],
    sede: [null, []]
  });

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected navController: NavController,
    protected formBuilder: FormBuilder,
    protected platform: Platform,
    protected toastCtrl: ToastController,
    private luogoService: LuogoService,
    private strutturaService: StrutturaService
  ) {

    // Watch the form for changes, and
    this.form.valueChanges.subscribe((v) => {
      this.isReadyToSave = this.form.valid;
    });

  }

  ngOnInit() {
    this.luogoService.query({filter: 'struttura-is-null'}).subscribe(
      data => {
        if (!this.struttura.sedeId) {
          this.luogos = data;
        } else {
          this.luogoService.find(this.struttura.sedeId).subscribe((subData: Luogo) => {
            this.luogos = [subData].concat(subData);
          }, (error) => this.onError(error));
        }
      },
      error => this.onError(error));
    this.activatedRoute.data.subscribe((response) => {
      this.updateForm(response.data);
      this.struttura = response.data;
      this.isNew = this.struttura.id === null || this.struttura.id === undefined;
    });
  }

  updateForm(struttura: Struttura) {
    this.form.patchValue({
      id: struttura.id,
      categoria: struttura.categoria,
      codice: struttura.codice,
      nome: struttura.nome,
      telefono: struttura.telefono,
      email: struttura.email,
      mediaValutazione: struttura.mediaValutazione,
      numeroValutazioni: struttura.numeroValutazioni,
      isActive: struttura.isActive,
      sede: struttura.sedeId,
    });
  }

  save() {
    this.isSaving = true;
    const struttura = this.createFromForm();
    if (!this.isNew) {
      this.subscribeToSaveResponse(this.strutturaService.update(struttura));
    } else {
      this.subscribeToSaveResponse(this.strutturaService.create(struttura));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<Struttura>>) {
    result.subscribe((res: HttpResponse<Struttura>) => this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onError(res.error));
  }

  async onSaveSuccess(response) {
    let action = 'updated';
    if (response.status === 201) {
      action = 'created';
    }
    this.isSaving = false;
    const toast = await this.toastCtrl.create({message: `Struttura ${action} successfully.`, duration: 2000, position: 'middle'});
    toast.present();
    this.navController.navigateBack('/tabs/entities/struttura');
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

  private createFromForm(): Struttura {
    return {
      ...new Struttura(),
      id: this.form.get(['id']).value,
      categoria: this.form.get(['categoria']).value,
      codice: this.form.get(['codice']).value,
      nome: this.form.get(['nome']).value,
      telefono: this.form.get(['telefono']).value,
      email: this.form.get(['email']).value,
      mediaValutazione: this.form.get(['mediaValutazione']).value,
      numeroValutazioni: this.form.get(['numeroValutazioni']).value,
      isActive: this.form.get(['isActive']).value,
      sedeId: this.form.get(['sede']).value,
    };
  }

  compareLuogo(first: Luogo, second: Luogo): boolean {
    return first && second ? first.id === second.id : first === second;
  }

  trackLuogoById(index: number, item: Luogo) {
    return item.id;
  }
}
