/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MyappjhTestModule } from '../../../test.module';
import { ClienteDeleteDialogComponent } from 'app/entities/cliente/cliente-delete-dialog.component';
import { ClienteService } from 'app/entities/cliente/cliente.service';

describe('Component Tests', () => {
    describe('Cliente Management Delete Component', () => {
        let comp: ClienteDeleteDialogComponent;
        let fixture: ComponentFixture<ClienteDeleteDialogComponent>;
        let service: ClienteService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyappjhTestModule],
                declarations: [ClienteDeleteDialogComponent]
            })
                .overrideTemplate(ClienteDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ClienteDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ClienteService);
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
