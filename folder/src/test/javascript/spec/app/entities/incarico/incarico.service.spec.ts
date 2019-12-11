/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IncaricoService } from 'app/entities/incarico/incarico.service';
import { IIncarico, Incarico, TipoNegoziazione, StatoIncarico, Ruolo, BooleanStatus } from 'app/shared/model/incarico.model';

describe('Service Tests', () => {
    describe('Incarico Service', () => {
        let injector: TestBed;
        let service: IncaricoService;
        let httpMock: HttpTestingController;
        let elemDefault: IIncarico;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(IncaricoService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Incarico(
                0,
                'AAAAAAA',
                TipoNegoziazione.COMPRAVENDITA,
                StatoIncarico.BOZZA,
                'AAAAAAA',
                'AAAAAAA',
                currentDate,
                'AAAAAAA',
                'AAAAAAA',
                Ruolo.VENDITORE,
                BooleanStatus.SI,
                BooleanStatus.SI,
                false,
                currentDate,
                currentDate,
                'AAAAAAA',
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        dataContatto: currentDate.format(DATE_TIME_FORMAT),
                        dataCreazione: currentDate.format(DATE_TIME_FORMAT),
                        dataModifica: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Incarico', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        dataContatto: currentDate.format(DATE_TIME_FORMAT),
                        dataCreazione: currentDate.format(DATE_TIME_FORMAT),
                        dataModifica: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dataContatto: currentDate,
                        dataCreazione: currentDate,
                        dataModifica: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Incarico(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Incarico', async () => {
                const returnedFromService = Object.assign(
                    {
                        riferimento: 'BBBBBB',
                        tipo: 'BBBBBB',
                        stato: 'BBBBBB',
                        agente: 'BBBBBB',
                        agenteDiContatto: 'BBBBBB',
                        dataContatto: currentDate.format(DATE_TIME_FORMAT),
                        noteTrattativa: 'BBBBBB',
                        datiFiscali: 'BBBBBB',
                        ruolo: 'BBBBBB',
                        alertFiscali: 'BBBBBB',
                        alertCortesia: 'BBBBBB',
                        oscuraIncarico: true,
                        dataCreazione: currentDate.format(DATE_TIME_FORMAT),
                        dataModifica: currentDate.format(DATE_TIME_FORMAT),
                        utenteCreazione: 'BBBBBB',
                        utenteModifica: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        dataContatto: currentDate,
                        dataCreazione: currentDate,
                        dataModifica: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Incarico', async () => {
                const returnedFromService = Object.assign(
                    {
                        riferimento: 'BBBBBB',
                        tipo: 'BBBBBB',
                        stato: 'BBBBBB',
                        agente: 'BBBBBB',
                        agenteDiContatto: 'BBBBBB',
                        dataContatto: currentDate.format(DATE_TIME_FORMAT),
                        noteTrattativa: 'BBBBBB',
                        datiFiscali: 'BBBBBB',
                        ruolo: 'BBBBBB',
                        alertFiscali: 'BBBBBB',
                        alertCortesia: 'BBBBBB',
                        oscuraIncarico: true,
                        dataCreazione: currentDate.format(DATE_TIME_FORMAT),
                        dataModifica: currentDate.format(DATE_TIME_FORMAT),
                        utenteCreazione: 'BBBBBB',
                        utenteModifica: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dataContatto: currentDate,
                        dataCreazione: currentDate,
                        dataModifica: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Incarico', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
