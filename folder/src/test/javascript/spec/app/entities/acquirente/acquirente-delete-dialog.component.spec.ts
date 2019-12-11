/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MyappjhTestModule } from '../../../test.module';
import { AcquirenteDeleteDialogComponent } from 'app/entities/acquirente/acquirente-delete-dialog.component';
import { AcquirenteService } from 'app/entities/acquirente/acquirente.service';

describe('Component Tests', () => {
    describe('Acquirente Management Delete Component', () => {
        let comp: AcquirenteDeleteDialogComponent;
        let fixture: ComponentFixture<AcquirenteDeleteDialogComponent>;
        let service: AcquirenteService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyappjhTestModule],
                declarations: [AcquirenteDeleteDialogComponent]
            })
                .overrideTemplate(AcquirenteDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AcquirenteDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AcquirenteService);
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
