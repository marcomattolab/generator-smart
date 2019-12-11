/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MyappjhTestModule } from '../../../test.module';
import { GeolocalizzazioneComponent } from 'app/entities/geolocalizzazione/geolocalizzazione.component';
import { GeolocalizzazioneService } from 'app/entities/geolocalizzazione/geolocalizzazione.service';
import { Geolocalizzazione } from 'app/shared/model/geolocalizzazione.model';

describe('Component Tests', () => {
    describe('Geolocalizzazione Management Component', () => {
        let comp: GeolocalizzazioneComponent;
        let fixture: ComponentFixture<GeolocalizzazioneComponent>;
        let service: GeolocalizzazioneService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyappjhTestModule],
                declarations: [GeolocalizzazioneComponent],
                providers: []
            })
                .overrideTemplate(GeolocalizzazioneComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GeolocalizzazioneComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GeolocalizzazioneService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Geolocalizzazione(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.geolocalizzaziones[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
