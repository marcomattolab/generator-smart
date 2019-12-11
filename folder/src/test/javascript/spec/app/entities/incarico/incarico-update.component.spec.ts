/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { MyappjhTestModule } from '../../../test.module';
import { IncaricoUpdateComponent } from 'app/entities/incarico/incarico-update.component';
import { IncaricoService } from 'app/entities/incarico/incarico.service';
import { Incarico } from 'app/shared/model/incarico.model';

describe('Component Tests', () => {
    describe('Incarico Management Update Component', () => {
        let comp: IncaricoUpdateComponent;
        let fixture: ComponentFixture<IncaricoUpdateComponent>;
        let service: IncaricoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyappjhTestModule],
                declarations: [IncaricoUpdateComponent]
            })
                .overrideTemplate(IncaricoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(IncaricoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IncaricoService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Incarico(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.incarico = entity;
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
                    const entity = new Incarico();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.incarico = entity;
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
