/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { MyappjhTestModule } from '../../../test.module';
import { NotificheUpdateComponent } from 'app/entities/notifiche/notifiche-update.component';
import { NotificheService } from 'app/entities/notifiche/notifiche.service';
import { Notifiche } from 'app/shared/model/notifiche.model';

describe('Component Tests', () => {
    describe('Notifiche Management Update Component', () => {
        let comp: NotificheUpdateComponent;
        let fixture: ComponentFixture<NotificheUpdateComponent>;
        let service: NotificheService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyappjhTestModule],
                declarations: [NotificheUpdateComponent]
            })
                .overrideTemplate(NotificheUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(NotificheUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NotificheService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Notifiche(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.notifiche = entity;
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
                    const entity = new Notifiche();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.notifiche = entity;
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
