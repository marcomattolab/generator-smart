/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MyappjhTestModule } from '../../../test.module';
import { ImmobileDeleteDialogComponent } from 'app/entities/immobile/immobile-delete-dialog.component';
import { ImmobileService } from 'app/entities/immobile/immobile.service';

describe('Component Tests', () => {
    describe('Immobile Management Delete Component', () => {
        let comp: ImmobileDeleteDialogComponent;
        let fixture: ComponentFixture<ImmobileDeleteDialogComponent>;
        let service: ImmobileService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyappjhTestModule],
                declarations: [ImmobileDeleteDialogComponent]
            })
                .overrideTemplate(ImmobileDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ImmobileDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImmobileService);
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
