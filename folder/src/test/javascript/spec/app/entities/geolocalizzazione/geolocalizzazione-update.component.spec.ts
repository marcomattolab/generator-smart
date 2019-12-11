/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { MyappjhTestModule } from '../../../test.module';
import { GeolocalizzazioneUpdateComponent } from 'app/entities/geolocalizzazione/geolocalizzazione-update.component';
import { GeolocalizzazioneService } from 'app/entities/geolocalizzazione/geolocalizzazione.service';
import { Geolocalizzazione } from 'app/shared/model/geolocalizzazione.model';

describe('Component Tests', () => {
    describe('Geolocalizzazione Management Update Component', () => {
        let comp: GeolocalizzazioneUpdateComponent;
        let fixture: ComponentFixture<GeolocalizzazioneUpdateComponent>;
        let service: GeolocalizzazioneService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyappjhTestModule],
                declarations: [GeolocalizzazioneUpdateComponent]
            })
                .overrideTemplate(GeolocalizzazioneUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GeolocalizzazioneUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GeolocalizzazioneService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Geolocalizzazione(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.geolocalizzazione = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Geolocalizzazione();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.geolocalizzazione = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
