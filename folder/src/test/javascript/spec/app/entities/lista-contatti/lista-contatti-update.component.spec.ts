/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { MyappjhTestModule } from '../../../test.module';
import { ListaContattiUpdateComponent } from 'app/entities/lista-contatti/lista-contatti-update.component';
import { ListaContattiService } from 'app/entities/lista-contatti/lista-contatti.service';
import { ListaContatti } from 'app/shared/model/lista-contatti.model';

describe('Component Tests', () => {
    describe('ListaContatti Management Update Component', () => {
        let comp: ListaContattiUpdateComponent;
        let fixture: ComponentFixture<ListaContattiUpdateComponent>;
        let service: ListaContattiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyappjhTestModule],
                declarations: [ListaContattiUpdateComponent]
            })
                .overrideTemplate(ListaContattiUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ListaContattiUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ListaContattiService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ListaContatti(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.listaContatti = entity;
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
                    const entity = new ListaContatti();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.listaContatti = entity;
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
