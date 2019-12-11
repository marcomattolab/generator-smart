/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { MyappjhTestModule } from '../../../test.module';
import { AcquirenteUpdateComponent } from 'app/entities/acquirente/acquirente-update.component';
import { AcquirenteService } from 'app/entities/acquirente/acquirente.service';
import { Acquirente } from 'app/shared/model/acquirente.model';

describe('Component Tests', () => {
    describe('Acquirente Management Update Component', () => {
        let comp: AcquirenteUpdateComponent;
        let fixture: ComponentFixture<AcquirenteUpdateComponent>;
        let service: AcquirenteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyappjhTestModule],
                declarations: [AcquirenteUpdateComponent]
            })
                .overrideTemplate(AcquirenteUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AcquirenteUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AcquirenteService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Acquirente(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.acquirente = entity;
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
                    const entity = new Acquirente();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.acquirente = entity;
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
