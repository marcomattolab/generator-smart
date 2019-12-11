/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { MyappjhTestModule } from '../../../test.module';
import { ImmobileUpdateComponent } from 'app/entities/immobile/immobile-update.component';
import { ImmobileService } from 'app/entities/immobile/immobile.service';
import { Immobile } from 'app/shared/model/immobile.model';

describe('Component Tests', () => {
    describe('Immobile Management Update Component', () => {
        let comp: ImmobileUpdateComponent;
        let fixture: ComponentFixture<ImmobileUpdateComponent>;
        let service: ImmobileService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyappjhTestModule],
                declarations: [ImmobileUpdateComponent]
            })
                .overrideTemplate(ImmobileUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ImmobileUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImmobileService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Immobile(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.immobile = entity;
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
                    const entity = new Immobile();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.immobile = entity;
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
