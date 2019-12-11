/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MyappjhTestModule } from '../../../test.module';
import { IncaricoDeleteDialogComponent } from 'app/entities/incarico/incarico-delete-dialog.component';
import { IncaricoService } from 'app/entities/incarico/incarico.service';

describe('Component Tests', () => {
    describe('Incarico Management Delete Component', () => {
        let comp: IncaricoDeleteDialogComponent;
        let fixture: ComponentFixture<IncaricoDeleteDialogComponent>;
        let service: IncaricoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyappjhTestModule],
                declarations: [IncaricoDeleteDialogComponent]
            })
                .overrideTemplate(IncaricoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(IncaricoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IncaricoService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
