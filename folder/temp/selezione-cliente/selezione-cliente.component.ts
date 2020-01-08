import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {merge, Observable, Subject} from 'rxjs';
import {debounceTime, distinctUntilChanged, filter, map} from 'rxjs/operators';
import {NgbTypeahead, NgbTypeaheadSelectItemEvent} from '@ng-bootstrap/ng-bootstrap';
import {Cliente} from 'app/shared/model/cliente.model';
import {TagEnum} from 'app/shared/model/tag.model';
import {Router} from '@angular/router';
import {getFormattedNomeCognomeAsString} from 'app/entities/cliente/cliente-util';

@Component({
    selector: 'jhi-selezione-cliente',
    templateUrl: './selezione-cliente.component.html',
    styleUrls: ['./selezione-cliente.component.scss']
})
export class SelezioneClienteComponent implements OnInit {

    @ViewChild('typeahead')
    typeahead: NgbTypeahead;
    focus$ = new Subject<string>();
    click$ = new Subject<string>();

    @Input() label: string;
    @Input() buttonLabel: string;
    @Input() incaricoId: string;
    @Input() completeClienteList: Cliente[];
    @Input() incaricoClienteList: Cliente[];
    @Input() tag: TagEnum;

    @Output() clienteListChanges = new EventEmitter();

    inputControlId = '';

    constructor(
        private router: Router
    ) {
    }

    ngOnInit() {
        const randomNumber = Math.random() * 1000;
        const randomInteger = Math.floor(randomNumber);
        this.inputControlId = `selezione-cliente-${randomInteger}`;
    }

    search = (text$: Observable<string>) => {
        const debouncedText$ = text$.pipe(debounceTime(200), distinctUntilChanged());

        const clicksWithClosedPopup$ = this.click$.pipe(
            filter(() => !this.typeahead.isPopupOpen()));

        const inputFocus$ = this.focus$;

        return merge(debouncedText$, inputFocus$, clicksWithClosedPopup$).pipe(
            map(term => (term === '' ?
                this.getAvailableClienti() : this.getAvailableClienti()
                    .filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
        );
    };

    private getAvailableClienti() {
        const result = [];

        this.completeClienteList.forEach(cliente => {
            let toAdd = true;
            for (const incaricoCliente of this.incaricoClienteList) {
                if (incaricoCliente.id === cliente.id) {
                    toAdd = false;
                    break;
                }
            }

            if (toAdd) {
                result.push(getFormattedNomeCognomeAsString(cliente));
            }
        });

        return result;
    }

    onSelectCliente(ngbTypeaheadSelectItem: NgbTypeaheadSelectItemEvent) {
        ngbTypeaheadSelectItem.preventDefault();

        const clienteFound = this.completeClienteList.find(committente =>
            getFormattedNomeCognomeAsString(committente) === ngbTypeaheadSelectItem.item);

        if (clienteFound) {
            this.incaricoClienteList.push(clienteFound);
        }

        this.clienteListChanges.emit(clienteFound);

        this.typeahead.writeValue('');
    }

    rimuoviCliente(cliente: Cliente) {
        const indexOf = this.incaricoClienteList.indexOf(cliente);
        this.incaricoClienteList.splice(indexOf, 1);

        this.clienteListChanges.emit(cliente);
    }

    nuovoCliente() {
        const navigationExtras = {
            queryParams: {
                incaricoRef: this.incaricoId,
                clienteTag: this.tag
            }
        };
        this.router.navigate(['/cliente/new'], navigationExtras);
    }

    isClientiSelezionati(): boolean {
        return this.incaricoClienteList && this.incaricoClienteList.length > 0;
    }
}
