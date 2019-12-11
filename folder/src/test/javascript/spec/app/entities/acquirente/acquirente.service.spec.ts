/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { AcquirenteService } from 'app/entities/acquirente/acquirente.service';
import { IAcquirente, Acquirente, BooleanStatus, TipoCliente } from 'app/shared/model/acquirente.model';

describe('Service Tests', () => {
    describe('Acquirente Service', () => {
        let injector: TestBed;
        let service: AcquirenteService;
        let httpMock: HttpTestingController;
        let elemDefault: IAcquirente;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(AcquirenteService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Acquirente(
                0,
                'AAAAAAA',
                'AAAAAAA',
                BooleanStatus.SI,
                currentDate,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                false,
                TipoCliente.CLIENTE,
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
                        dataNascita: currentDate.format(DATE_FORMAT),
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

            it('should create a Acquirente', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        dataNascita: currentDate.format(DATE_FORMAT),
                        dataCreazione: currentDate.format(DATE_TIME_FORMAT),
                        dataModifica: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dataNascita: currentDate,
                        dataCreazione: currentDate,
                        dataModifica: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Acquirente(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Acquirente', async () => {
                const returnedFromService = Object.assign(
                    {
                        nome: 'BBBBBB',
                        cognome: 'BBBBBB',
                        alertCompleanno: 'BBBBBB',
                        dataNascita: currentDate.format(DATE_FORMAT),
                        meseNascita: 'BBBBBB',
                        telefono: 'BBBBBB',
                        email: 'BBBBBB',
                        indirizzo: 'BBBBBB',
                        cap: 'BBBBBB',
                        regione: 'BBBBBB',
                        provincia: 'BBBBBB',
                        citta: 'BBBBBB',
                        note: 'BBBBBB',
                        abilitato: true,
                        tipoCliente: 'BBBBBB',
                        dataCreazione: currentDate.format(DATE_TIME_FORMAT),
                        dataModifica: currentDate.format(DATE_TIME_FORMAT),
                        utenteCreazione: 'BBBBBB',
                        utenteModifica: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        dataNascita: currentDate,
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

            it('should return a list of Acquirente', async () => {
                const returnedFromService = Object.assign(
                    {
                        nome: 'BBBBBB',
                        cognome: 'BBBBBB',
                        alertCompleanno: 'BBBBBB',
                        dataNascita: currentDate.format(DATE_FORMAT),
                        meseNascita: 'BBBBBB',
                        telefono: 'BBBBBB',
                        email: 'BBBBBB',
                        indirizzo: 'BBBBBB',
                        cap: 'BBBBBB',
                        regione: 'BBBBBB',
                        provincia: 'BBBBBB',
                        citta: 'BBBBBB',
                        note: 'BBBBBB',
                        abilitato: true,
                        tipoCliente: 'BBBBBB',
                        dataCreazione: currentDate.format(DATE_TIME_FORMAT),
                        dataModifica: currentDate.format(DATE_TIME_FORMAT),
                        utenteCreazione: 'BBBBBB',
                        utenteModifica: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dataNascita: currentDate,
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

            it('should delete a Acquirente', async () => {
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
