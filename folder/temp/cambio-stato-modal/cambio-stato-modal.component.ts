import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {Incarico, StatoIncarico} from 'app/shared/model/incarico.model';
import {IncaricoService} from 'app/entities/incarico';
import {JhiAlertService} from 'ng-jhipster';

@Component({
    selector: 'jhi-cambio-stato-modal',
    templateUrl: './cambio-stato-modal.component.html'
})
export class CambioStatoModalComponent implements OnInit {

    @Input() statiIncarico: Array<StatoIncarico>;
    @Input() statoIniziale: StatoIncarico;
    @Input() incarico: Incarico;
    selectedState: StatoIncarico;
    private incaricoBkp: Incarico;

    constructor(
        public activeModal: NgbActiveModal,
        private incaricoService: IncaricoService,
        private alertService: JhiAlertService) {
    }

    ngOnInit() {
        this.selectedState = this.statoIniziale;
        this.incaricoBkp = Object.assign({}, this.incarico);
    }

    cancel() {
        this.activeModal.dismiss();
    }

    save() {
        this.incaricoBkp.stato = this.selectedState;

        this.incaricoService.changeStatus(this.incaricoBkp).subscribe(res => {
            // this.incarico = res;
            this.activeModal.close(res);
        }, error => {
            this.onError(error);
        });
        // this.activeModal.close(this.selectedState);
    }

    private onError(error) {
        const errorServerAggregate = error.error && error.error.detail ?
            error.error.detail : error.message;

        const errorSplit = errorServerAggregate.split(',');
        const errorKey = errorSplit[0];

        const params = {};

        if (errorKey === 'error.editIncarico.cambioStatoNonValido') {
            params['statoIniziale'] = errorSplit[1];

            params['statoFinale'] = errorSplit[2];

            params['idIncarico'] = errorSplit[3];

            params['utente'] = errorSplit[4];
        }

        this.alertService.error(errorKey, params, null);
        // this.activeModal.dismiss();
    }
}
