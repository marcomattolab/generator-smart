/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MyappjhTestModule } from '../../../test.module';
import { GeolocalizzazioneDetailComponent } from 'app/entities/geolocalizzazione/geolocalizzazione-detail.component';
import { Geolocalizzazione } from 'app/shared/model/geolocalizzazione.model';

describe('Component Tests', () => {
    describe('Geolocalizzazione Management Detail Component', () => {
        let comp: GeolocalizzazioneDetailComponent;
        let fixture: ComponentFixture<GeolocalizzazioneDetailComponent>;
        const route = ({ data: of({ geolocalizzazione: new Geolocalizzazione(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyappjhTestModule],
                declarations: [GeolocalizzazioneDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(GeolocalizzazioneDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GeolocalizzazioneDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.geolocalizzazione).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
